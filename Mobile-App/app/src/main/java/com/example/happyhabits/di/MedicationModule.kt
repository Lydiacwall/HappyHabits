package com.example.happyhabits.di

import com.example.happyhabits.feature_application.feature_medication.data.repository.MedicationRepositoryImpl
import com.example.happyhabits.feature_application.feature_medication.domain.repository.IMedicationRepository
import com.example.happyhabits.feature_application.feature_medication.data.network.ApiHelper
import com.example.happyhabits.feature_application.feature_medication.data.network.ApiService
import com.example.happyhabits.feature_application.feature_medication.domain.use_case.AddMedication
import com.example.happyhabits.feature_application.feature_medication.domain.use_case.LogMedication
import com.example.happyhabits.feature_application.feature_medication.domain.use_case.MedicationUseCases
import com.example.happyhabits.feature_application.feature_medication.domain.use_case.RemoveMedication
import com.example.happyhabits.feature_application.feature_medication.domain.use_case.RetrieveMedications
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object  MedicationModule {
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMedicationHelper(medicationService: ApiService): ApiHelper {
        return ApiHelper(medicationService);
    }

    @Provides
    @Singleton
    fun provideMedicationRepository(medicineApi: ApiHelper): IMedicationRepository {
        return MedicationRepositoryImpl(medicineApi)
    }

    @Provides
    @Singleton
    fun provideMedicationUseCases(repository: IMedicationRepository): MedicationUseCases {
        return MedicationUseCases(
           addMedication = AddMedication(repository),
            removeMedication = RemoveMedication(repository),
            logMedication = LogMedication(repository),
            retrieveMedications = RetrieveMedications(repository)
        )
    }
}