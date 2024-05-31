package com.example.happyhabits.feature_application.feature_workout.data.model

import kotlinx.serialization.Serializable

@Serializable
class FastActivityStatistics (
    val averageDuration: Double,
    val averageElevation: Double,
    val averageQuantity: Double,
    val totalQuantity: Float,
    val totalWorkouts: Int
)
