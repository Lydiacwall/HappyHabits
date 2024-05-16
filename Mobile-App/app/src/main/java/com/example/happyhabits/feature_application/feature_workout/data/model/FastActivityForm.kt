package com.example.happyhabits.feature_application.feature_workout.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FastActivityForm(
    val userId: String,
    val date: String,
    val type: String,
    val time: String,
    val duration: String,
    val notes: String? = "",
    val quantity: Float,
    val elevation: Float
)