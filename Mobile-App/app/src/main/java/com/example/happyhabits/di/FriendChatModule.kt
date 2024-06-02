package com.example.happyhabits.di

import com.example.happyhabits.feature_application.feature_chat.data.network.ApiHelper
import com.example.happyhabits.feature_application.feature_chat.data.network.ApiService
import com.example.happyhabits.feature_application.feature_chat.data.repository.MessageRepository
import com.example.happyhabits.feature_application.feature_chat.domain.repository.IMessageRepository
import com.example.happyhabits.feature_application.feature_chat.domain.use_case.FriendChatUseCases
import com.example.happyhabits.feature_application.feature_chat.domain.use_case.GetConversationOfFriend
import com.example.happyhabits.feature_application.feature_chat.domain.use_case.GetFriendList
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import com.microsoft.signalr.TransportEnum
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FriendChatModule {
    @Provides
    fun provideHubConnection(@Named("hubUrl") hubUrl: String): HubConnection {
        return HubConnectionBuilder.create(hubUrl)
            .withTransport(TransportEnum.WEBSOCKETS)
            .build()
    }

    @Provides
    @Named("hubUrl")
    fun provideHubUrl(): String {
        // Replace "http://your_signalr_hub_url" with your actual SignalR hub URL
        return "http://192.168.1.19:5057/chatHub"
    }

    @Provides
    @Singleton
    fun provideApiService(@Named("BaseRetrofit") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMessageApiHelper(messageApiService: ApiService): ApiHelper {
        return ApiHelper(messageApiService);
    }

    @Provides
    @Singleton
    fun provideMessageRepository(messageApi: ApiHelper): IMessageRepository {
        return MessageRepository(messageApi)
    }

    @Provides
    @Singleton
    fun provideFriendChatUseCases(messageRepository: IMessageRepository): FriendChatUseCases {
        return FriendChatUseCases(
            getConversationOfFriend = GetConversationOfFriend(messageRepository),
            getFriendList = GetFriendList(messageRepository)
        )
    }
}