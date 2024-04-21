package com.example.happyhabits.feature_authentication.presentation.login

import com.example.happyhabits.feature_authentication.domain.model.User

data class LoginState(
    val password: String? = null,
    val email: String? = null,
    val user: User? = null,
    val error: String? = null,
    val isSuccess: Boolean = false
)

