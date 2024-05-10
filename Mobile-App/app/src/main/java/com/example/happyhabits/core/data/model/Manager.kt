package com.example.happyhabits.core.data.model

import com.example.happyhabits.core.domain.model.User

object Manager {
    var currentUser: User? = null

    fun setUser(user: User?) {
        currentUser = user
    }
}
