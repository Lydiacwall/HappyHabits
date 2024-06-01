package com.example.happyhabits.feature_authentication.presentation.login

import com.example.happyhabits.R
import com.example.happyhabits.core.domain.model.User

data class LoginState(
    val email: String = "",
    val password: String = "",
    val user: User? = null,
    val error: String? = null,
    val isSuccess: Boolean = false,
    val hiddenPassword: Boolean = true,
    val imageId: Int = R.drawable.hide_password
)
