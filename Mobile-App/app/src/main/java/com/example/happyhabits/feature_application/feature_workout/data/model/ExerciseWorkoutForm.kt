package com.example.happyhabits.feature_application.feature_workout.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ExercisesWorkoutForm(
    override val userId: String,
    override val date: String,
    override val time: String,
    override val notes: String?,
    val duration: String,
    val simpleExercises: List<String> = emptyList()
) : WorkoutForm(userId, date, "Exercises", time, notes)