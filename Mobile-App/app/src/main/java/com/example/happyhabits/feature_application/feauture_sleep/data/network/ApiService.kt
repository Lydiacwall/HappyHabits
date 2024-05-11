package com.example.happyhabits.feature_application.feauture_sleep.data.network

import com.example.happyhabits.feature_application.feauture_sleep.data.model.SleepForm
import com.example.happyhabits.feature_application.feauture_sleep.domain.use_case.AddSleepHabit
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/Sleep/add")
    suspend fun addSleepHabit(@Body sleepForm: SleepForm): Response<String>

}