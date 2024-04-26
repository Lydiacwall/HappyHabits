package com.example.happyhabits.feature_authentication.domain.repository

import com.example.happyhabits.feature_authentication.domain.model.User

interface IUserRepository {
    suspend fun getUserByPasswordAndEmail(password: String, email: String): User?
}