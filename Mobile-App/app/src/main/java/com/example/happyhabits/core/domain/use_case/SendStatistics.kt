package com.example.happyhabits.core.domain.use_case

import com.example.happyhabits.core.domain.repository.ICoreRepository
import com.example.happyhabits.feature_application.feature_chat.domain.model.FriendGroup

class SendStatistics (
    private val repository: ICoreRepository
) {
    suspend operator fun invoke(senderId: String, groupId: String, type: String, statistics: Map<String, Any>, friendUsername: String): String {
        try {
            return repository.sendStatistics(senderId, groupId, type, statistics, friendUsername)
        } catch (e: Exception) {
            throw e;
        }
    }
}