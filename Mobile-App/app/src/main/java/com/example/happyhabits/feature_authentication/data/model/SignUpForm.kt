package com.example.happyhabits.feature_authentication.data.model

data class SignUpForm (
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val birthdate: String,
    val speciality: String
){}