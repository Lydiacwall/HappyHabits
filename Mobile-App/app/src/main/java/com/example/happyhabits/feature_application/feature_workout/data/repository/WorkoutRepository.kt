package com.example.happyhabits.feature_application.feature_workout.data.repository

import android.util.Log
import com.example.happyhabits.feature_application.feature_workout.data.network.ApiHelper
import com.example.happyhabits.feature_application.feature_workout.domain.model.Workout
import com.example.happyhabits.feature_application.feature_workout.domain.repository.IWorkoutRepository

class WorkoutRepository (
    private val workoutApi: ApiHelper
): IWorkoutRepository {
    override suspend fun addWorkout(workout: Workout, type: Int) {
        try {
            workoutApi.addWorkout(workout, type)
        } catch (e: Exception) {
            throw e;
        }
    }

}