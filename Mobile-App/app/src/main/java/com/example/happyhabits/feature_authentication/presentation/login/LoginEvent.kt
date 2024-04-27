package com.example.happyhabits.feature_authentication.presentation.login

sealed class LoginEvent {
    data class Validate(val password: String, val email: String): LoginEvent()
}