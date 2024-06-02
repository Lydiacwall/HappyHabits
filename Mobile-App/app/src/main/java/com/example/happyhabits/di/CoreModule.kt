package com.example.happyhabits.di
import com.example.happyhabits.core.domain.use_case.SendStatistics
import com.example.happyhabits.core.data.network.ApiHelper
import com.example.happyhabits.core.data.network.ApiService
import com.example.happyhabits.core.data.repository.CoreRepositoryImpl
import com.example.happyhabits.core.domain.repository.ICoreRepository
import com.example.happyhabits.core.domain.use_case.CoreUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun provideApiService(@Named("BaseRetrofit") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCoreHelper(coreService: ApiService): ApiHelper {
        return ApiHelper(coreService);
    }

    @Provides
    @Singleton
    fun provideCoreRepository(coreApi: ApiHelper): ICoreRepository {
        return CoreRepositoryImpl(coreApi)
    }

    @Provides
    @Singleton
    fun provideCoreUseCases(repository: ICoreRepository): CoreUseCases {
        return CoreUseCases(
            sendStatistics = SendStatistics(repository)
        )
    }
}
