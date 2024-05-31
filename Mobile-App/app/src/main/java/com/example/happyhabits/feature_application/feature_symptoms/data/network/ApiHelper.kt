package com.example.happyhabits.feature_application.feature_symptoms.data.network

import com.example.happyhabits.feature_application.feature_symptoms.data.model.SymptomForm
import com.example.happyhabits.feature_application.feature_symptoms.data.model.SymptomStatistics

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

    suspend fun calcSymptomsStatistics(userId: String , monthNumber : Int, yearNumber: Int) : SymptomStatistics? {
        try {
            val response = apiService.calcSymptomsStatistics(userId, monthNumber, yearNumber)
            return response.body()
        } catch (e: Exception) {
            throw e;
        }
    }
}
