package com.example.happyhabits.core.data.network

import com.example.happyhabits.core.data.model.StatisticsForm
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("api/Statistics/SendStatistics")
    suspend fun sendStatistics(@Body form: StatisticsForm): Response<String>
}