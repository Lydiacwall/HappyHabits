package com.example.happyhabits.feature_application.feature_chat.presentation.friend_chat

import com.example.happyhabits.feature_application.feature_chat.domain.model.Message

data class FriendChatState (
    var conversation : List<Message> = emptyList(),
    var messageContent: String = "",
    var username: String = "",
    var friendname: String = "",
    var userId: String = ""
)