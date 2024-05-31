package com.example.happyhabits.feature_application.feature_mood.data.network

import com.example.happyhabits.feature_application.feature_mood.data.model.MoodForm
import com.example.happyhabits.feature_application.feature_mood.data.model.MoodStatistics
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("api/Mood/AddHabit")
    suspend fun addMoodHabit(@Body moodForm: MoodForm): Response<String>

    @GET("api/Mood/GetStatistics")
    suspend fun calcMoodStatistics(@Query("userId") userId : String) : Response<MoodStatistics>

}