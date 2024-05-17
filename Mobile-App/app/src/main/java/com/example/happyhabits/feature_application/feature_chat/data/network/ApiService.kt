package com.example.happyhabits.feature_application.feature_chat.data.network

import com.example.happyhabits.feature_application.feature_chat.domain.model.FriendGroup
import com.example.happyhabits.feature_application.feature_chat.domain.model.Message
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/Message/RetrieveConversation")
    suspend fun getConversation(@Query("groupId") groupId: String) : Response<List<Message>>
    @GET("api/Message/GetFriendList")
    suspend fun getFriendList(@Query("userId") userId: String): Response<List<FriendGroup>>
}