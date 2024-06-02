package com.example.happyhabits.core.domain.repository


interface ICoreRepository {
    suspend fun sendStatistics(senderId: String, groupId: String, type: String, statistics: Map<String, Any>, friendUsername: String): String
}