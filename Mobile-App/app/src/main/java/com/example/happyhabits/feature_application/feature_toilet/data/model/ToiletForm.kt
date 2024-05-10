package com.example.happyhabits.feature_application.feature_toilet.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ToiletForm (
    val userId: String,
    val date: String,
    val type: String,
    val time: String,
    val notes: String
) {}
