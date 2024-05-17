package com.example.happyhabits.di

import com.example.happyhabits.feature_application.feature_toilet.data.network.ApiHelper
import com.example.happyhabits.feature_application.feature_toilet.data.network.ApiService
import com.example.happyhabits.feature_application.feature_toilet.data.repository.ToiletRepositoryImpl
import com.example.happyhabits.feature_application.feature_toilet.domain.repository.IToiletRepository
import com.example.happyhabits.feature_application.feature_toilet.domain.use_case.AddToiletHabit
import com.example.happyhabits.feature_application.feature_toilet.domain.use_case.ToiletUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ToiletModule {
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideToiletApiHelper(toiletApiService: ApiService): ApiHelper {
        return ApiHelper(toiletApiService);
    }

    @Provides
    @Singleton
    fun provideToiletRepository(toiletApi: ApiHelper): IToiletRepository {
        return ToiletRepositoryImpl(toiletApi)
    }

    @Provides
    @Singleton
    fun provideToiletUseCases(repository: IToiletRepository): ToiletUseCases {
        return ToiletUseCases(
            addToiletHabit = AddToiletHabit(repository)
        )
    }
}