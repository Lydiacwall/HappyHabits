package com.example.happyhabits.feature_application.feature_chat.domain.use_case

import com.example.happyhabits.feature_application.feature_chat.domain.model.FriendGroup
import com.example.happyhabits.feature_application.feature_chat.domain.repository.IMessageRepository

class GetFriendList(
    private val repository: IMessageRepository
) {
    suspend operator fun invoke(userId: String): List<FriendGroup> {
        try {
            return repository.getFriendList(userId)
        } catch (e: Exception) {
            throw e;
        }
    }
}