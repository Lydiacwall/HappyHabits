package com.example.happyhabits.core.data.network

import android.util.Log
import com.example.happyhabits.core.data.model.StatisticsForm
import com.example.happyhabits.feature_application.feature_food.data.model.SpecificFoodForm
import com.example.happyhabits.feature_application.feature_food.domain.model.SpecificFood

class ApiHelper(private val apiService: ApiService) {

    suspend fun sendStatistics(senderId: String, groupId: String, type: String, statistics: Map<String, Any>, friendUsername: String): String {
        try {
            val response = apiService.sendStatistics(StatisticsForm(senderId = senderId, groupId = groupId, type = type, statistics = statistics, friendUsername = friendUsername))
            return if (response.isSuccessful && response.body() != null)
            {
                return response.body()!!
            } else
            {
                "Unsuccessful Statistics Delivery"
            }
        } catch (e: Exception) {
            throw e;
        }
    }
}