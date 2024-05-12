package com.example.happyhabits.feature_authentication.data.network

import com.example.happyhabits.feature_application.feauture_sleep.data.model.SleepGoalForm
import com.example.happyhabits.feature_authentication.data.model.Credentials
import com.example.happyhabits.feature_authentication.data.model.SignUpForm
import com.example.happyhabits.feature_authentication.data.model.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/User/login")
    suspend fun authenticateUser(@Body credentials: Credentials): Response<UserDto>

    @POST("api/User/SignUp")
    suspend fun addNewUser(@Body signUpForm: SignUpForm): Response<UserDto>

    @POST("api/User/SleepGoal")
    suspend fun updateSleepGoal(@Body sleepGoalForm: SleepGoalForm): Response<String>
}
