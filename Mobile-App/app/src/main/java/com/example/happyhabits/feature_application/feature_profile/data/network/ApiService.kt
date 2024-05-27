package com.example.happyhabits.feature_application.feature_profile.data.network

import com.example.happyhabits.feature_application.feature_profile.data.model.FriendGroupForm
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/Message/CreateFriendGroup")
    suspend fun addNewFriendGroup(@Body friendGroupForm: FriendGroupForm): Response<String>
}