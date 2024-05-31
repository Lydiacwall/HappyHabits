package com.example.happyhabits.feature_application.feature_workout.data.model

import kotlinx.serialization.Serializable

@Serializable
data class WeightsStatistics (
    val averageDuration: Double,
    val topExercises: List<String>,
    val totalWorkouts: Int,
    val averageExercisePerWorkout: Double,
    val averageKgsPerWorkout: Float
)