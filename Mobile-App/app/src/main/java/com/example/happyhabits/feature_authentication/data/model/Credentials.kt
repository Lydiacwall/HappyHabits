package com.example.happyhabits.feature_authentication.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Credentials(
    val email: String,
    val password: String
)
