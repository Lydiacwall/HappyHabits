package com.example.happyhabits.feature_application.feauture_sleep.data.network

import com.example.happyhabits.feature_application.feauture_sleep.data.model.SleepForm
import com.example.happyhabits.feature_application.feauture_sleep.data.model.SleepStatistics

class ApiHelper(private val apiService: ApiService) {
    suspend fun addSleepHabit(sleepForm: SleepForm) {
        try {
            val response = apiService.addSleepHabit(sleepForm)
            println(response.code())
        } catch (e: Exception) {
            throw e;
        }
    }

    suspend fun calcSleepStats(userId : String, monday : String , sunday : String) : SleepStatistics?{
        try{
            val response = apiService.calcSleepStats(userId, monday, sunday)
            return response.body()
        }catch (e: Exception) {
            throw e;
        }
    }
}