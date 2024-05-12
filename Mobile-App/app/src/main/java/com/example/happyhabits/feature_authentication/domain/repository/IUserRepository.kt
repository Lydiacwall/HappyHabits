package com.example.happyhabits.feature_authentication.domain.repository

import com.example.happyhabits.core.domain.model.Type
import com.example.happyhabits.core.domain.model.User

interface IUserRepository {
    suspend fun getUserByPasswordAndEmail(password: String, email: String): User?
    suspend fun addNewUser(firstName: String, lastName: String, email: String, password: String, birthdate: String, speciality: String, type: Type): User?
    suspend fun updateSleepGoal(userId: String, sleepGoal: Int)
}