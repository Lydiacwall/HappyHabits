package com.example.happyhabits.feature_application.feature_symptoms.data.network

import com.example.happyhabits.feature_application.feature_symptoms.data.model.SymptomForm
import com.example.happyhabits.feature_application.feature_symptoms.data.model.SymptomStatistics
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("api/Symptom/AddHabit")
    suspend fun addSymptomHabit(@Body symptomForm: SymptomForm): Response<String>

    @GET("api/Symptom/GetStatistics")
    suspend fun calcSymptomsStatistics(@Query("userId") userId : String , @Query("month") month: Int, @Query("year") year: Int ) : Response<SymptomStatistics>
}