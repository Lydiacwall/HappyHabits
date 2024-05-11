package com.example.happyhabits.feature_application.feauture_sleep.data.network

import com.example.happyhabits.feature_application.feauture_sleep.data.model.SleepForm

class ApiHelper(private val apiService: ApiService) {
    suspend fun addSleepHabit(sleepForm: SleepForm) {
        try {
            val response = apiService.addSleepHabit(sleepForm)
            println(response.code())
        } catch (e: Exception) {
            throw e;
        }
    }
}