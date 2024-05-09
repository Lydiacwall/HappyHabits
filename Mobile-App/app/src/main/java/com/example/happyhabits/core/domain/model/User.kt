package com.example.happyhabits.core.domain.model


data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val email: String,
    val type: Type,
    val birthDate: String,
    val speciality: String = "None",
    val streak: Int = 0
) {
    override fun toString(): String {
        return "First name: $firstName last name: $lastName  id: $id birthdate: $birthDate speciality: $speciality email: $email type: $type streak: $streak";
    }
}

enum class Type {
    CLIENT,
    DOCTOR
}

class InvalidUserException(message: String): Exception(message)
