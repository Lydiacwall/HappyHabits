package com.example.happyhabits.feature_authentication.presentation.sign_up_user

import java.time.LocalDate

sealed class SignUpUserEvent {
    data class AddUser(val firstName: String, val lastName: String, val email: String, val password: String, val verifyPassword: String, val birthdate: String, val speciality: String = "None"): SignUpUserEvent()
    data class NameChanged(val name: String) : SignUpUserEvent()
    data class SurnameChanged(val surname: String) : SignUpUserEvent()
    data class EmailChanged(val email: String) : SignUpUserEvent()
    data class PasswordChanged(val password: String) : SignUpUserEvent()
    data class VerifiedPasswordChanged(val verifiedPassword: String) : SignUpUserEvent()
}
