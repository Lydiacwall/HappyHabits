package com.example.happyhabits.feature_application.feature_symptoms.data.network

import com.example.happyhabits.feature_application.feature_symptoms.data.model.SymptomForm
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/Symptom/AddHabit")
    suspend fun addSymptomHabit(@Body symptomForm: SymptomForm): Response<String>
}