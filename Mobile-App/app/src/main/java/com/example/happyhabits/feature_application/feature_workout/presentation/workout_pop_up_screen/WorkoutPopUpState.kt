package com.example.happyhabits.feature_application.feature_workout.presentation.workout_pop_up_screen

import com.example.happyhabits.feature_application.feature_workout.domain.model.Exercise

data class WorkoutPopUpState(
    val type: String = "",
    val time: String = "MMM dd yyyy",
    val notes: String = "",
    val unitMeasurement: String = "",
    val quantity: Float? = null,
    val elevation: Float? = null,
    val muscleGroup: String? = null,
    val exercises: List<Exercise>? = null
)

