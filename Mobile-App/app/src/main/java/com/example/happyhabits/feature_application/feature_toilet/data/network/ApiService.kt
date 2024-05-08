package com.example.happyhabits.feature_application.feature_toilet.data.network

import com.example.happyhabits.feature_application.feature_toilet.data.model.ToiletDto
import com.example.happyhabits.feature_application.feature_toilet.data.model.ToiletForm
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

interface ApiService {
    @POST("api/Toilet/AddHabit")
    suspend fun addToiletHabit(@Body toiletForm : ToiletForm): Response<String>
}
