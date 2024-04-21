package com.example.happyhabits.feature_authentication.domain.use_case


data class AuthenticationUseCases(
    val addUser: AddUser,
    val authenticateUser: AuthenticateUser
)