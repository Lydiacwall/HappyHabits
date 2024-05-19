package com.example.happyhabits.feature_application.feature_symptoms.data.network

import com.example.happyhabits.feature_application.feature_symptoms.data.model.SymptomForm

class ApiHelper(private val apiService: ApiService) {
    suspend fun addSymptomHabit(symptomForm: SymptomForm) {
        try {
            val response = apiService.addSymptomHabit(symptomForm)
            println(response.code())
            println(response.message())
        } catch (e: Exception) {
            throw e;
        }
    }
}