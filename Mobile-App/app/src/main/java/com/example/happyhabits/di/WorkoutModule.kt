package com.example.happyhabits.di

import com.example.happyhabits.feature_application.feature_workout.data.network.ApiHelper
import com.example.happyhabits.feature_application.feature_workout.data.network.ApiService
import com.example.happyhabits.feature_application.feature_workout.data.repository.WorkoutRepository
import com.example.happyhabits.feature_application.feature_workout.domain.repository.IWorkoutRepository
import com.example.happyhabits.feature_application.feature_workout.domain.use_case.AddWorkout
import com.example.happyhabits.feature_application.feature_workout.domain.use_case.WorkoutUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkoutModule {
    @Provides
    @Singleton
    fun provideApiService(@Named("BaseRetrofit") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideWorkoutApiHelper(workoutApiService: ApiService): ApiHelper {
        return ApiHelper(workoutApiService);
    }
    @Provides
    @Singleton
    fun provideWorkoutRepository(workoutApi: ApiHelper): IWorkoutRepository {
        return WorkoutRepository(workoutApi)
    }
    @Provides
    @Singleton
    fun provideWorkoutUseCases(repository: IWorkoutRepository): WorkoutUseCases {
        return WorkoutUseCases(
            addWorkout = AddWorkout(repository)
        )
    }
}