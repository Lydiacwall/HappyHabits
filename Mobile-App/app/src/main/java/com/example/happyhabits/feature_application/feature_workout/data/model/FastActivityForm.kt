package com.example.happyhabits.feature_application.feature_workout.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FastActivityForm(
    override val userId: String,
    override val date: String,
    override val type: String,
    override val time: String,
    override val notes: String?,
    val quantity: Float?,
    val elevation: Float
): WorkoutForm(userId, date, type, time, notes) {
}