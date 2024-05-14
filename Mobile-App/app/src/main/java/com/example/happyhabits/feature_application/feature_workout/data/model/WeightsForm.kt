package com.example.happyhabits.feature_application.feature_workout.data.model

import com.example.happyhabits.feature_application.feature_workout.domain.model.Exercise
import kotlinx.serialization.Serializable

@Serializable
data class WeightsForm(
    val userId: String,
    val date: String,
    val type: String = "Weights",
    val time: String,
    val duration: String,
    val notes: String? = "",
    val exercises: List<Exercise>
)