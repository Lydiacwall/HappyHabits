package com.example.happyhabits.feature_application.feature_workout.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ExercisesWorkoutForm(
    val userId: String,
    val date: String,
    val type: String = "Weights",
    val time: String,
    val notes: String? = "",
    val duration: String,
    val simpleExercises: List<String>
)
