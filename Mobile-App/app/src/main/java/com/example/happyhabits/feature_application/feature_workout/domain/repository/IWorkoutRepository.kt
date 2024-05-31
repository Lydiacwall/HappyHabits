package com.example.happyhabits.feature_application.feature_workout.domain.repository

import com.example.happyhabits.feature_application.feature_workout.domain.model.Workout
import com.example.happyhabits.feature_application.feature_workout.domain.model.WorkoutStatistics

interface IWorkoutRepository {
    suspend fun addWorkout(workout: Workout, type: Int)
    suspend fun getFastWorkoutStatistics(userId: String, month: Int, year:Int, type: String): WorkoutStatistics
    suspend fun getExercisesWorkoutStatistics(userId: String, month: Int, year:Int, type: String): WorkoutStatistics
    suspend fun getWeightsWorkoutStatistics(userId: String, month: Int, year:Int): WorkoutStatistics


}