package com.example.happyhabits.feature_authentication.domain.use_case

import com.example.happyhabits.feature_authentication.domain.model.InvalidUserException
import com.example.happyhabits.feature_authentication.domain.model.User
import com.example.happyhabits.feature_authentication.domain.repository.IUserRepository

class AuthenticateUser(
    private val repository: IUserRepository
) {
    operator fun invoke(password: String, email: String): User? {
        if (password.isBlank()) {
            throw InvalidUserException("The password cannot be empty !!!")
        }
        if (email.isBlank()) {
            throw InvalidUserException("The email cannot be empty !!!")
        }
        return repository.getUserByPasswordAndEmail(password, email);
    }
}