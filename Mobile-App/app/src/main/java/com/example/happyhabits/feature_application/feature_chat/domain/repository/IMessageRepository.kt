package com.example.happyhabits.feature_application.feature_chat.domain.repository

import com.example.happyhabits.feature_application.feature_chat.domain.model.FriendGroup
import com.example.happyhabits.feature_application.feature_chat.domain.model.Message

interface IMessageRepository {
    suspend fun getMessages(groupId: String): List<Message>

    suspend fun getFriendList(userId: String): List<FriendGroup>
}