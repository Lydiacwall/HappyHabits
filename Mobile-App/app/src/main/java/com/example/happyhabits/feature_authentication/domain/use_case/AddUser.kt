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
             throw InvalidUserException("Birthdate")
        }
        if (email.isBlank()) {
            throw InvalidUserException("Email")
        }
        if (lastName.isBlank()) {
            throw InvalidUserException("Last Name")
        }
        if (firstName.isBlank()) {
            throw InvalidUserException("First Name")
        }
        if (password.isBlank()) {
            throw InvalidUserException("Password")
        }
//        repository.add
    }
}