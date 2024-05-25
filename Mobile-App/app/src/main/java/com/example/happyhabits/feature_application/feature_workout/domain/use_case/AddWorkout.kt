package com.example.happyhabits.feature_application.feature_workout.domain.use_case

import android.util.Log
import com.example.happyhabits.feature_application.feature_workout.domain.model.Workout
import com.example.happyhabits.feature_application.feature_workout.domain.repository.IWorkoutRepository

class AddWorkout (
    private val repository: IWorkoutRepository
) {
    suspend operator fun invoke(workout: Workout, type: Int) {
        try {
            repository.addWorkout(workout, type)
        } catch (e: Exception){
            throw e;
        }
    }
}