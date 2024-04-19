package com.example.happyhabits.feature_authentication.presentation.login

import com.example.happyhabits.feature_authentication.domain.model.User

sealed class LoginEvent {
    data class Validate(val password: String, val email: String): LoginEvent()
}