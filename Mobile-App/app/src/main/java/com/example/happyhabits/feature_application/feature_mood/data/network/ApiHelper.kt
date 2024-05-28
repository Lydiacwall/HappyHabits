package com.example.happyhabits.feature_application.feature_mood.data.network

import com.example.happyhabits.feature_application.feature_mood.data.model.MoodForm
import com.example.happyhabits.feature_application.feature_mood.data.network.ApiService

class ApiHelper(private val apiService: ApiService) {
    suspend fun addMoodHabit(moodForm: MoodForm) {
        try {
            val response = apiService.addMoodHabit(moodForm)
            println(response.code())
            print(response.message())
        } catch (e: Exception) {
            throw e;
        }
    }
//    suspend fun calcMoodSatistics(){
//        try{
//
//        }
//    }
}