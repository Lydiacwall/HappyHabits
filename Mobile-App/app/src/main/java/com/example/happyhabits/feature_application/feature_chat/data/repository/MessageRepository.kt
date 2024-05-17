package com.example.happyhabits.feature_application.feature_chat.data.repository

import com.example.happyhabits.feature_application.feature_chat.data.network.ApiHelper
import com.example.happyhabits.feature_application.feature_chat.domain.model.FriendGroup
import com.example.happyhabits.feature_application.feature_chat.domain.repository.IMessageRepository
import com.example.happyhabits.feature_application.feature_chat.domain.model.Message

class MessageRepository(
    private val apiService: ApiHelper
): IMessageRepository {
    override suspend fun getMessages(groupId: String): List<Message> {
        try {
            return apiService.getConversation(groupId)
        }
        catch (e: Exception) {
            throw e;
        }
    }

    override suspend fun getFriendList(userId: String): List<FriendGroup> {
        try {
            return apiService.getFriendList(userId)
        } catch (e: Exception) {
            throw e;
        }
    }
}