package com.example.happyhabits.feature_authentication.data.model

import com.example.happyhabits.core.domain.model.Type
import kotlinx.serialization.Serializable

@Serializable
class UserDto (
    val id: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val email: String,
    val type: Type,
    val birthdate: String,
    val streak: Int
)