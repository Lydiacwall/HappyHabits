package com.example.happyhabits.feature_application.feature_profile.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FriendGroupForm (
    val scannerId: String,
    val genId: String
)