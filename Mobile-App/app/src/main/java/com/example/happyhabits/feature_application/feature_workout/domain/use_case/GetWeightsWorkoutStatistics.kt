package com.example.happyhabits.feature_application.feature_workout.domain.use_case

import com.example.happyhabits.feature_application.feature_workout.domain.model.WorkoutStatistics
import com.example.happyhabits.feature_application.feature_workout.domain.repository.IWorkoutRepository

class GetWeightsWorkoutStatistics (
    private val repository: IWorkoutRepository
) {
    suspend operator fun invoke(userId: String, month: Int, year:Int): WorkoutStatistics {
        try {
            return repository.getWeightsWorkoutStatistics(userId, month, year)
        } catch (e: Exception){
            throw e;
        }
    }
}