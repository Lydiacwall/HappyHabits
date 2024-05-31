package com.example.happyhabits.feature_application.feature_workout.data.repository

import android.util.Log
import com.example.happyhabits.feature_application.feature_workout.data.network.ApiHelper
import com.example.happyhabits.feature_application.feature_workout.domain.model.Workout
import com.example.happyhabits.feature_application.feature_workout.domain.model.WorkoutStatistics
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
    override suspend fun getFastWorkoutStatistics(userId: String, month: Int, year:Int, type: String): WorkoutStatistics {
        try {
            return  workoutApi.getFastWorkoutStatistics(userId, month, year, type)
        } catch (e: Exception) {
            throw e;
        }
    }
    override suspend fun getExercisesWorkoutStatistics(userId: String, month: Int, year:Int, type: String): WorkoutStatistics {
        try {
            return  workoutApi.getExercisesWorkoutStatistics(userId, month, year, type)
        } catch (e: Exception) {
            throw e;
        }
    }
    override suspend fun getWeightsWorkoutStatistics(userId: String, month: Int, year:Int): WorkoutStatistics {
        try {
            return  workoutApi.getWeightsWorkoutStatistics(userId, month, year)
        } catch (e: Exception) {
            throw e;
        }
    }

}