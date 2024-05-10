package com.example.happyhabits.feature_application.feature_workout.domain.repository

import com.example.happyhabits.feature_application.feature_workout.domain.model.Workout

interface IWorkoutRepository {
    suspend fun addWorkout(workout: Workout, type: Int)
}