package com.example.happyhabits.di

import com.example.happyhabits.feature_authentication.data.network.ApiHelper
import com.example.happyhabits.feature_authentication.data.network.ApiService
import com.example.happyhabits.feature_authentication.data.repository.UserRepositoryImpl
import com.example.happyhabits.feature_authentication.domain.repository.IUserRepository
import com.example.happyhabits.feature_authentication.domain.use_case.AddUser
import com.example.happyhabits.feature_authentication.domain.use_case.AuthenticateUser
import com.example.happyhabits.feature_authentication.domain.use_case.AuthenticationUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {
    @Provides
    @Singleton
    fun provideApiService(@Named("BaseRetrofit") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideUserApiHelper(userApiService: ApiService): ApiHelper {
        return ApiHelper(userApiService);
    }
    @Provides
    @Singleton
    fun provideUserRepository(userApi: ApiHelper): IUserRepository {
        return UserRepositoryImpl(userApi)
    }
    @Provides
    @Singleton
    fun provideAuthenticationUseCases(repository: IUserRepository): AuthenticationUseCases {
        return AuthenticationUseCases(
            addUser = AddUser(repository),
            authenticateUser = AuthenticateUser(repository)
        )
    }
}