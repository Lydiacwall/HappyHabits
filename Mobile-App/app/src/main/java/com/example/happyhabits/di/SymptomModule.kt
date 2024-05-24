package com.example.happyhabits.di

import com.example.happyhabits.feature_application.feature_symptoms.data.network.ApiHelper
import com.example.happyhabits.feature_application.feature_symptoms.data.network.ApiService
import com.example.happyhabits.feature_application.feature_symptoms.data.repository.SymptomRepository
import com.example.happyhabits.feature_application.feature_symptoms.domain.repository.ISymptomRepository
import com.example.happyhabits.feature_application.feature_symptoms.domain.use_case.AddSymptom
import com.example.happyhabits.feature_application.feature_symptoms.domain.use_case.SymptonUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SymptomModule {

    @Provides
    @Singleton
    fun provideApiService(@Named("BaseRetrofit") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSymptomApiHelper(symptomApiService: ApiService): ApiHelper {
        return ApiHelper(symptomApiService)
    }

    @Provides
    @Singleton
    fun provideSymptomRepository(symptomApi: ApiHelper): ISymptomRepository {
        return SymptomRepository(symptomApi)
    }

    @Provides
    @Singleton
    fun provideSymptomUseCases(repository: ISymptomRepository): SymptonUseCases {
        return SymptonUseCases(
            addSymptomUseCases = AddSymptom(repository)
        )
    }
}