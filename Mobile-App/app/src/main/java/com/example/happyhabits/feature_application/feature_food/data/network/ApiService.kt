package com.example.happyhabits.feature_application.feature_food.data.network

import com.example.happyhabits.feature_application.feature_food.data.model.FoodDto
import com.example.happyhabits.feature_application.feature_food.data.model.FoodForm
import com.example.happyhabits.feature_application.feature_medication.data.model.MedicationForm
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @DELETE("api/Food/DeleteHabit")
    suspend fun removeFood(@Query("id") foodId:String): Response<String>
    @POST("api/Food/SaveLog")
    suspend fun saveLog(@Body foodForm: FoodForm): Response<String>
    @GET("api/Medication/GetFoodActivities")
    suspend fun retrieveFoodActivities(@Query("userId") userId: String, @Query("date") date: String): Response<List<FoodDto>>
}
