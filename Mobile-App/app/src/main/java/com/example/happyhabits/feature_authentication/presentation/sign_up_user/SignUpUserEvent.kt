package com.example.happyhabits.feature_authentication.presentation.sign_up_user

import com.example.happyhabits.feature_authentication.presentation.login.LoginEvent

sealed class SignUpUserEvent {
    data class AddUser(val firstName: String, val lastName: String, val email: String, val password: String, val verifyPassword: String, val birthdate: String, val speciality: String = "None"): SignUpUserEvent()
}