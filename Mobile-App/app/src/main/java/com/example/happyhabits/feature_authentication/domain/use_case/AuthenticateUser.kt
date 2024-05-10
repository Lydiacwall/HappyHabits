package com.example.happyhabits.feature_authentication.domain.use_case

import com.example.happyhabits.core.domain.model.InvalidUserException
import com.example.happyhabits.core.domain.model.User
import com.example.happyhabits.feature_authentication.domain.repository.IUserRepository

class AuthenticateUser(
    private val repository: IUserRepository
) {
    suspend operator fun invoke(password: String, email: String): User? {
        if (password.isBlank()) {
            throw InvalidUserException("The password cannot be empty !!!")
        }
        if (email.isBlank()) {
            throw InvalidUserException("The email cannot be empty !!!")
        }
        val user = repository.getUserByPasswordAndEmail(password, email);
        return user;
    }
}
