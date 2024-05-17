package com.example.happyhabits.feature_application.feature_chat.presentation.friend_chat

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.core.data.model.Manager
import com.example.happyhabits.feature_application.feature_chat.domain.model.Message
import com.example.happyhabits.feature_application.feature_chat.domain.use_case.FriendChatUseCases
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class FriendChatViewModel @Inject constructor(
    private val hubConnection: HubConnection,
    private val friendChatUseCases: FriendChatUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = mutableStateOf(FriendChatState())
    val state: State<FriendChatState> = _state;
    lateinit var groupId: String;
    lateinit var senderId: String;
    lateinit var messages : List<Message>

    init {
        viewModelScope.launch {
            val friendName: String = savedStateHandle.get<String>("friendUsername").toString()
            // Update the Id of the group
            groupId = savedStateHandle.get<String>("groupId").toString()
            // Update the Username of the user
            senderId = Manager.currentUser?.id.toString()
            // Retrieve the messages
            messages = friendChatUseCases.getConversationOfFriend(groupId)
            _state.value = Manager.currentUser?.let { _state.value.copy(username = it.firstName + " " + it.lastName, friendname = friendName, userId = senderId, conversation = messages) }!!
            hubConnection.start().blockingAwait()
            hubConnection.send("AddToGroup", groupId)

            receiveMessage()
        }
    }

    fun sendMessage(message: String) {
        viewModelScope.launch {
            if (hubConnection.connectionState == HubConnectionState.CONNECTED) {
                hubConnection.send("SendMessage", groupId, senderId, message)
            }
        }
    }

    private fun receiveMessage() {
        hubConnection.on("ReceiveMessage",
            { senderId: String, msg: String, time: String ->
                val newMessage = Message(senderId, time, msg)
                _state.value = _state.value.copy(
                    conversation = _state.value.conversation + newMessage
                )
            },
            String::class.java,
            String::class.java,
            String::class.java
        )
    }


    override fun onCleared() {
        if (hubConnection.connectionState == HubConnectionState.CONNECTED) {
            hubConnection.stop()
        }
    }
}
