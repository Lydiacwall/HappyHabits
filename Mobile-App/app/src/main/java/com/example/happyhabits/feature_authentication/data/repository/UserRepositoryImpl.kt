package com.example.happyhabits.feature_authentication.data.repository

import com.example.happyhabits.feature_authentication.data.data_source.IUserDao
import com.example.happyhabits.feature_authentication.domain.model.User
import com.example.happyhabits.feature_authentication.domain.repository.IUserRepository

class UserRepositoryImpl(
    private val dao: IUserDao
): IUserRepository {
    override fun getUserByPasswordAndEmail(password: String, email: String): User? {
        return dao.getUserByPasswordAndEmail(password, email);
    }
}