package com.example.happyhabits.feature_authentication.data.network

import com.example.happyhabits.feature_authentication.data.model.Credentials

class ApiHelper(private val apiService: ApiService) {
    suspend fun authenticate(credentials: Credentials) = apiService.authenticateUser(credentials)
}
