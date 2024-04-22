package com.example.happyhabits.feature_authentication.data.data_source

import com.example.happyhabits.feature_authentication.domain.model.User

interface IUserDao {
    fun getUserByPasswordAndEmail(password: String, email: String): User?
}
