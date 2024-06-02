package com.example.happyhabits.core.data.repository

import com.example.happyhabits.core.data.network.ApiHelper
import com.example.happyhabits.core.domain.repository.ICoreRepository


class CoreRepositoryImpl (
    private val apiService: ApiHelper
): ICoreRepository {

    override suspend fun sendStatistics(senderId: String, groupId: String, type: String, statistics: Map<String, Any>, friendUsername: String): String {
        try {
            return apiService.sendStatistics(senderId, groupId, type, statistics, friendUsername)
        } catch (e: Exception) {
            throw e;
        }
    }
}