package com.example.happyhabits.feature_application.feature_chat.domain.use_case

import com.example.happyhabits.feature_application.feature_chat.domain.repository.IMessageRepository
import com.example.happyhabits.feature_application.feature_chat.domain.model.Message

class GetConversationOfFriend (
    private val repository: IMessageRepository
) {
    suspend operator fun invoke(groupId: String): List<Message> {
        try {
            return repository.getMessages(groupId)
        } catch (e: Exception) {
            throw e;
        }
    }
}