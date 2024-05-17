package com.example.happyhabits.feature_application.feature_chat.presentation.choose_friend_group

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.core.data.model.Manager
import com.example.happyhabits.feature_application.feature_chat.domain.use_case.FriendChatUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendInboxViewModel @Inject constructor(
    private val friendChatUseCases: FriendChatUseCases
): ViewModel() {
    private val _state = mutableStateOf((FriendInboxState()))
    val state: State<FriendInboxState> = _state

    init {
        viewModelScope.launch {
            val userId: String = Manager.currentUser?.id.toString()
            // TODO initialize all the groups
            val messages = friendChatUseCases.getFriendList(userId)
            _state.value = state.value.copy(conversation = messages)
        }
    }
}
