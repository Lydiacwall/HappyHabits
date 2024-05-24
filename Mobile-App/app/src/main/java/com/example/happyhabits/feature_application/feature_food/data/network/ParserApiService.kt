package com.example.happyhabits.feature_application.feature_food.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ParserApiService {
    @GET("parser")
    suspend fun getFoodInformation(@Query("ingr") foodNameInput:String): Response<String>
}