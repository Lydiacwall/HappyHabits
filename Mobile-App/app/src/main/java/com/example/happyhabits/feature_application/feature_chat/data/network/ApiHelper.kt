package com.example.happyhabits.feature_application.feature_chat.data.network

import com.example.happyhabits.feature_application.feature_chat.domain.model.FriendGroup
import com.example.happyhabits.feature_application.feature_chat.domain.model.Message

class ApiHelper(private val apiService: ApiService) {
    suspend fun getConversation(groupId: String): List<Message> {
        try {
            val response = apiService.getConversation(groupId)
            return if (response.isSuccessful && response.body() != null) {
                return response.body()!!
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            throw e;
        }
    }

    suspend fun getFriendList(userId: String): List<FriendGroup> {
        try {
            val response = apiService.getFriendList(userId)
            return if (response.isSuccessful && response.body() != null) {
                return response.body()!!
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            throw e;
        }
    }
}