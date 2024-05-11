package com.example.happyhabits.di

import com.example.happyhabits.feature_application.feauture_sleep.data.network.ApiHelper
import com.example.happyhabits.feature_application.feauture_sleep.data.network.ApiService
import com.example.happyhabits.feature_application.feauture_sleep.data.repository.SleepRepository
import com.example.happyhabits.feature_application.feauture_sleep.domain.repository.ISleepRepository
import com.example.happyhabits.feature_application.feauture_sleep.domain.use_case.AddSleepHabit
import com.example.happyhabits.feature_application.feauture_sleep.domain.use_case.SleepUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SleepModule {
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSleepHelper(sleepApiService: ApiService): ApiHelper {
        return ApiHelper(sleepApiService);
    }

    @Provides
    @Singleton
    fun provideSleepRepository(sleepApi: ApiHelper): ISleepRepository{
        return SleepRepository(sleepApi);
    }

    @Provides
    @Singleton
    fun provideSleepUseCases(repository: ISleepRepository): SleepUseCases {
        return SleepUseCases(
            addSleepHabit = AddSleepHabit(repository)
        )
    }
}