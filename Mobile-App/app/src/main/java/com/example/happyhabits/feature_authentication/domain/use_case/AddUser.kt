package com.example.happyhabits.feature_authentication.domain.use_case

import com.example.happyhabits.feature_authentication.domain.model.InvalidUserException
import com.example.happyhabits.feature_authentication.domain.model.User
import com.example.happyhabits.feature_authentication.domain.repository.IUserRepository

class AddUser(
    private val repository: IUserRepository
) {

    @Throws(InvalidUserException::class)
    suspend operator fun invoke(firstName: String, lastName: String, email: String, password: String, birthdate: String, speciality: String = "None"){
        if (birthdate.isBlank()) {
             throw InvalidUserException("The birth date cannot be empty !")
        }
        if (email.isBlank()) {
            throw InvalidUserException("The email cannot be empty !")
        }
        if (lastName.isBlank()) {
            throw InvalidUserException("The last name cannot be empty !")
        }
        if (firstName.isBlank()) {
            throw InvalidUserException("The first name cannot be empty !")
        }
        if (password.isBlank()) {
            throw InvalidUserException("The password cannot be empty !")
        }
//        repository.add
    }
}