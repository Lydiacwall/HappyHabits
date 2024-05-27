package com.example.happyhabits.feature_application.feauture_sleep.data.network

import com.example.happyhabits.feature_application.feauture_sleep.data.model.SleepForm
import com.example.happyhabits.feature_application.feauture_sleep.data.model.SleepStatistics
import com.example.happyhabits.feature_application.feauture_sleep.domain.use_case.AddSleepHabit
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("api/Sleep/AddHabit")
    suspend fun addSleepHabit(@Body sleepForm: SleepForm): Response<String>

    @GET("api/Sleep/GetStatistics")
    suspend fun calcSleepStats(@Query("userId") userId: String,@Query("monday") monday: String,@Query("sunday") sunday: String) : Response<SleepStatistics>

}