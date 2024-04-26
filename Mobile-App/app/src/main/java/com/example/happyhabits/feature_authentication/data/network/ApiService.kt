package com.example.happyhabits.feature_authentication.data.network

import com.example.happyhabits.feature_authentication.data.model.Credentials
import com.example.happyhabits.feature_authentication.data.model.UserDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("users/authenticate")
    suspend fun authenticateUser(@Body credentials: Credentials): UserDto
}