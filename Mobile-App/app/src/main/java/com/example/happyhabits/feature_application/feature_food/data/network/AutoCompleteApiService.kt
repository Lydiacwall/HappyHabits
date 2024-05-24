package com.example.happyhabits.feature_application.feature_food.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AutoCompleteApiService
{
    @GET("auto-complete")
    suspend fun getAutoCompleteSuggestions(@Query("q") searchInput:String): Response<List<String>>


}