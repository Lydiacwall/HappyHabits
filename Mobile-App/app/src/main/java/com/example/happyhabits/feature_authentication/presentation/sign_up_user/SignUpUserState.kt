package com.example.happyhabits.feature_authentication.presentation.sign_up_user

import com.example.happyhabits.feature_authentication.domain.model.Type
import java.time.LocalDate

data class SignUpUserState (
    val error: String? = null,
    val isSuccess: Boolean = false,
    val wrongField: String? = "",
    val nameInput: String = "",
    val surname: String = "",
    val emailInput: String = "",
    val passwordInput: String = "",
    val verifyPassword: String = "",
    val type: Type = Type.CLIENT
)