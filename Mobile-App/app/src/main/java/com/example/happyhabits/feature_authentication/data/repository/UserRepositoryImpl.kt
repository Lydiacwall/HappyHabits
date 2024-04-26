package com.example.happyhabits.feature_authentication.data.repository

import com.example.happyhabits.feature_authentication.data.data_source.IUserDao
import com.example.happyhabits.feature_authentication.data.model.Credentials
import com.example.happyhabits.feature_authentication.data.network.ApiHelper
import com.example.happyhabits.feature_authentication.domain.model.Type
import com.example.happyhabits.feature_authentication.domain.model.User
import com.example.happyhabits.feature_authentication.domain.repository.IUserRepository

class UserRepositoryImpl(
    private val userApi: ApiHelper
): IUserRepository {

    override suspend fun getUserByPasswordAndEmail(password: String, email: String): User? {
        return userApi.authenticate(Credentials(email, password));
    }
}