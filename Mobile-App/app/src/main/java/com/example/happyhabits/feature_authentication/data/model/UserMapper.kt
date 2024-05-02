package com.example.happyhabits.feature_authentication.data.model

import com.example.happyhabits.feature_authentication.domain.model.User

object UserMapper {
    fun UserDto.toDomain(): User {
        return User(
            id = this.id,
            firstName = this.firstName,
            lastName = this.lastName,
            password = this.password,
            email = this.email,
            type = this.type,
            birthDate = this.birthdate,
            streak = this.streak
        )
    }
}