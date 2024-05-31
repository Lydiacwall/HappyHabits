package com.example.happyhabits.feature_application.feature_workout.domain.use_case

import com.example.happyhabits.feature_application.feature_workout.domain.model.WorkoutStatistics
import com.example.happyhabits.feature_application.feature_workout.domain.repository.IWorkoutRepository

class GetExercisesWorkoutStatistics (
    private val repository: IWorkoutRepository
    ) {
        suspend operator fun invoke(userId: String, month: Int, year:Int, type: String): WorkoutStatistics {
            try {
                return repository.getExercisesWorkoutStatistics(userId, month, year, type)
            } catch (e: Exception){
                throw e;
            }
        }
    }