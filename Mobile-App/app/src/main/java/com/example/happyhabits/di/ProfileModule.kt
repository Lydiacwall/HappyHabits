package com.example.happyhabits.di

import com.example.happyhabits.feature_application.feature_profile.data.network.ApiHelper
import com.example.happyhabits.feature_application.feature_profile.data.network.ApiService
import com.example.happyhabits.feature_application.feature_profile.data.repository.ProfileRepository
import com.example.happyhabits.feature_application.feature_profile.domain.repository.IProfileRepository
import com.example.happyhabits.feature_application.feature_profile.domain.use_case.AddNewFriendship
import com.example.happyhabits.feature_application.feature_profile.domain.use_case.ProfileUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {
    @Provides
    @Singleton
    fun provideApiService(@Named("BaseRetrofit") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileApiHelper(profileApiService: ApiService): ApiHelper {
        return ApiHelper(profileApiService)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(profileApi: ApiHelper): IProfileRepository {
        return ProfileRepository(profileApi)
    }

    @Provides
    @Singleton
    fun provideProfileUseCases(profileRepository: IProfileRepository): ProfileUseCases {
        return ProfileUseCases(
            addNewFriendship = AddNewFriendship(profileRepository)
        )
    }
}