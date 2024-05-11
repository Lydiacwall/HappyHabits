package com.example.happyhabits.feature_application.home_page

import com.example.happyhabits.core.domain.model.User

data class HomePageState (
    val name: String? = "",
    val streak: Int? = 0
)