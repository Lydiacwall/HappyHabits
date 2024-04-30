package com.example.happyhabits.feature_authentication.data.network

import com.example.happyhabits.feature_authentication.data.model.Credentials
import com.example.happyhabits.feature_authentication.data.model.SignUpForm
import com.example.happyhabits.feature_authentication.data.model.UserMapper.toDomain
import com.example.happyhabits.feature_authentication.domain.model.InvalidUserException
import com.example.happyhabits.feature_authentication.domain.model.User

class ApiHelper(private val apiService: ApiService) {
    suspend fun authenticate(credentials: Credentials): User? {
        try {
            val response = apiService.authenticateUser(credentials)
            return if (response.isSuccessful && response.body() != null) {
                // You can access the HTTP status code if needed
                val statusCode = response.code()
                println(statusCode)
                val userDto = response.body()
                println(response.body()!!.id)
                userDto?.toDomain()
            } else {
                // Log error response
                val errorBody = response.errorBody()?.string()
                println("Error during authentication: ${response.code()} - $errorBody")
                null
            }
        } catch (e: Exception) {
            println("Network error: ${e.localizedMessage}")
            return null
        }
    }

    @Throws(InvalidUserException::class)
    suspend fun signUpUser(signUpForm: SignUpForm): User? {
        try {
            val response = apiService.addNewUser(signUpForm)
            return if (response.isSuccessful && response.body() != null) {
                // You can access the HTTP status code if needed
                val statusCode = response.code()
                println(statusCode)
                val userDto = response.body()
                println(response.body()!!.id)
                userDto?.toDomain()
            } else if (response.code() == 409) {
                // Log error response
                val errorBody = response.errorBody()?.string()
                println("Error during signing up: ${response.code()} - $errorBody")
                throw InvalidUserException("There is already a user with the same account")
            }
            else { null }
        } catch (e: Exception) {
            println("Network error: ${e.localizedMessage}")
            throw e;
        }
    }
}
