package com.example.happyhabits.feature_authentication.data.repository

import com.example.happyhabits.feature_authentication.data.model.Credentials
import com.example.happyhabits.feature_authentication.data.model.SignUpForm
import com.example.happyhabits.feature_authentication.data.network.ApiHelper
import com.example.happyhabits.core.domain.model.InvalidUserException
import com.example.happyhabits.core.domain.model.Type
import com.example.happyhabits.core.domain.model.User
import com.example.happyhabits.feature_authentication.domain.repository.IUserRepository

class UserRepositoryImpl(
    private val userApi: ApiHelper
): IUserRepository {

    override suspend fun getUserByPasswordAndEmail(password: String, email: String): User? {
        return userApi.authenticate(Credentials(email, password));
    }

    @Throws(InvalidUserException::class)
    override suspend fun addNewUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        birthdate: String,
        speciality: String,
        type: Type
    ): User? {
        try {
            return userApi.signUpUser(
                SignUpForm(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password,
                    birthdate = birthdate,
                    speciality = speciality,
                    type = type
                )
            )
        } catch (e: Exception) {
            throw e;
        }
    }
}