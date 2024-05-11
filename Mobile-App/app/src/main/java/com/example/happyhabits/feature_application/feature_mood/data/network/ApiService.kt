package com.example.happyhabits.feature_application.feature_mood.data.network

import com.example.happyhabits.feature_application.feature_mood.data.model.MoodForm
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/Mood/AddHabit")
    suspend fun addMoodHabit(@Body moodForm: MoodForm): Response<String>
}