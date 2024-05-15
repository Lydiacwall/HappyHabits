package com.example.happyhabits.di

import com.example.happyhabits.feature_application.feature_mood.data.network.ApiHelper
import com.example.happyhabits.feature_application.feature_mood.data.network.ApiService
import com.example.happyhabits.feature_application.feature_mood.data.repository.MoodRepository
import com.example.happyhabits.feature_application.feature_mood.domain.repository.IMoodRepository
import com.example.happyhabits.feature_application.feature_mood.domain.use_case.AddMoodHabit
import com.example.happyhabits.feature_application.feature_mood.domain.use_case.MoodUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoodModule {
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiHelper(moodService: ApiService): ApiHelper {
        return ApiHelper(moodService);
    }

    @Provides
    @Singleton
    fun provideMoodRepository(moodApi: ApiHelper): IMoodRepository {
        return MoodRepository(moodApi)
    }

    @Provides
    @Singleton
    fun provideMoodUseCases(repository: IMoodRepository): MoodUseCases {
        return MoodUseCases(
            addMoodHabit = AddMoodHabit(repository)
        )
    }
}