package com.example.happyhabits.core.data.network

import com.example.happyhabits.core.data.model.StatisticsForm
import retrofit2.Response

interface ApiService {
    suspend fun sendStatistics(form: StatisticsForm): Response<String>
}