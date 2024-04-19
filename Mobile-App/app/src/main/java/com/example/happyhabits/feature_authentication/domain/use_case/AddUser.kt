package com.example.happyhabits.feature_authentication.domain.use_case

import com.example.happyhabits.feature_authentication.domain.model.InvalidUserException
import com.example.happyhabits.feature_authentication.domain.model.User
import com.example.happyhabits.feature_authentication.domain.repository.IUserRepository

class AddUser(
    private val repository: IUserRepository
) {

    @Throws(InvalidUserException::class)
    suspend operator fun invoke(user: User) {
        if (user.birthDate.isBlank()) {
             throw InvalidUserException("The birth date cannot be empty !!!")
        }
        if (user.email.isBlank()) {
            throw InvalidUserException("The email cannot be empty !!!")
        }
        if (user.lastName.isBlank()) {
            throw InvalidUserException("The last name cannot be empty !!!")
        }
        if (user.firstName.isBlank()) {
            throw InvalidUserException("The first name cannot be empty !!!")
        }
        if (user.password.isBlank()) {
            throw InvalidUserException("The password cannot be empty !!!")
        }

//        repository.add
    }
}