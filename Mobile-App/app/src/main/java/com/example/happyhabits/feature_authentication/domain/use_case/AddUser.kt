package com.example.happyhabits.feature_authentication.domain.use_case

import com.example.happyhabits.feature_authentication.domain.model.InvalidUserException
import com.example.happyhabits.feature_authentication.domain.model.Type
import com.example.happyhabits.feature_authentication.domain.model.User
import com.example.happyhabits.feature_authentication.domain.repository.IUserRepository

class AddUser(
    private val repository: IUserRepository
) {

    @Throws(InvalidUserException::class)
    suspend operator fun invoke(firstName: String, lastName: String, email: String, password: String, birthdate: String, speciality: String = "None", type: Type){


        if (firstName.isBlank()) {
            throw InvalidUserException("First Name")
        }
        if (lastName.isBlank()) {
            throw InvalidUserException("Last Name")
        }
        if (email.isBlank()) {
            throw InvalidUserException("Email")
        }
        if (password.isBlank()) {
            throw InvalidUserException("Password")
        }
        if (birthdate == "DD/MM/YY") {
            throw InvalidUserException("Birthdate")
        }
        repository.addNewUser(firstName= firstName, lastName= lastName, email= email, password= password, birthdate= birthdate, speciality= speciality, type= type)    }
}