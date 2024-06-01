package com.example.happyhabits.feature_application.home_page

import com.example.happyhabits.core.domain.model.Type
import com.example.happyhabits.core.domain.model.User
import com.example.happyhabits.feature_application.feature_chat.domain.model.FriendGroup

data class HomePageState (
    val name: String? = "",
    val streak: Int? = 0,
    val type: Type?= Type.CLIENT,
    val specialty: String? = "",
    val clientsList: List<FriendGroup> =  emptyList()
//        listOf("Andrews Daniel", "Andrews Daniel", "Andrews Daniel", "Andrews Daniel", "Andrews Daniel", "Bridgers Miranda", "Geller Simon", "Georgiou Nikos", "Robinson Maria", "Simpson Carrie", "Wallace Gina")
)