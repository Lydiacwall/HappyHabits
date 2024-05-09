package com.example.happyhabits.feature_authentication.data.model

import com.example.happyhabits.core.domain.model.Type

data class SignUpForm (
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val birthdate: String,
    val speciality: String,
    val type: Type
){}