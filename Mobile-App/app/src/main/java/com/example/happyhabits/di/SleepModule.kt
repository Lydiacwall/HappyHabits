package com.example.happyhabits.di

import com.example.happyhabits.feature_application.feauture_sleep.data.network.ApiHelper
import com.example.happyhabits.feature_application.feauture_sleep.data.network.ApiService
import com.example.happyhabits.feature_application.feauture_sleep.data.repository.SleepRepository
import com.example.happyhabits.feature_application.feauture_sleep.domain.repository.ISleepRepository
import com.example.happyhabits.feature_application.feauture_sleep.domain.use_case.AddSleepHabit
import com.example.happyhabits.feature_application.feauture_sleep.domain.use_case.CalcSleepStatistics
import com.example.happyhabits.feature_application.feauture_sleep.domain.use_case.SleepUseCases
import com.example.happyhabits.feature_application.feauture_sleep.domain.use_case.UpdateSleepGoal
import com.example.happyhabits.feature_authentication.domain.repository.IUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SleepModule {
    @Provides
    @Singleton
    fun provideApiService(@Named("BaseRetrofit") retrofit: Retrofit): ApiService {
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
    fun provideSleepUseCases(sleepRepository: ISleepRepository, userRepository: IUserRepository): SleepUseCases {
        return SleepUseCases(
            addSleepHabit = AddSleepHabit(sleepRepository),
            updateSleepGoal = UpdateSleepGoal(userRepository = userRepository),
            calcSleepStatistics = CalcSleepStatistics(sleepRepository)
        )
    }

}