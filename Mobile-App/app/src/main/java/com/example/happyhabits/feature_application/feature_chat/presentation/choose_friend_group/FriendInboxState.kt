package com.example.happyhabits.feature_application.feature_chat.presentation.choose_friend_group

import com.example.happyhabits.core.domain.model.Type
import com.example.happyhabits.feature_application.feature_chat.domain.model.FriendGroup

data class FriendInboxState(
    var conversation: List<FriendGroup> = emptyList(),
    val type: Type?= Type.CLIENT,
)
