package com.example.happyhabits.feature_application.feature_workout.data.model

import com.example.happyhabits.feature_application.feature_workout.domain.model.Exercise
import kotlinx.serialization.Serializable

@Serializable
data class WeightsForm(
    override val userId: String,
    override val date: String,
    override val time: String,
    override val notes: String?,
    val exercises: List<Exercise>
) : WorkoutForm(userId, date, "Weights", time, notes)