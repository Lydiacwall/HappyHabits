package com.example.happyhabits.feature_authentication.domain.model


data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val email: String,
    val type: Type,
    val birthDate: String,
    val speciality: String = "None"
) {
    override fun toString(): String {
        return "First name: $firstName last name: $lastName  id: $id birthdate: $birthDate speciality: $speciality email:  type: $type";
    }
}

enum class Type {
    CLIENT,
    DOCTOR
}

class InvalidUserException(message: String): Exception(message)