package com.example.happyhabits.feature_application.home_page

import com.example.happyhabits.core.domain.model.Type
import com.example.happyhabits.core.domain.model.User

data class HomePageState (
    val name: String? = "",
    val streak: Int? = 0,
    val type: Type?= Type.CLIENT,
    val clientsList: List<String> = listOf("Andrews Daniel", "Bridgers Miranda", "Geller Simon", "Georgiou Nikos", "Robinson Maria", "Simpson Carrie", "Wallace Gina")
)