package com.example.happyhabits.feature_application.feature_profile.presentation.profile_page

import com.example.happyhabits.core.domain.model.Type

data class ProfileState(
    val firstName: String? = "",
    val lastName: String? = "",
    val email: String? = "",
    val birthdate: String? = "",
    val userId: String? = "",
    val type: Type?= Type.CLIENT,
    val speciality: String = "None"
)