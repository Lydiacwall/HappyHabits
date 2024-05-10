package com.example.happyhabits.feature_application.feature_workout.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
open class WorkoutForm(
    @Transient open val userId: String = "",
    @Transient open val date: String = "",
    @Transient open val type: String = "",
    @Transient open val time: String = "",
    @Transient open val notes: String? = null
) {}