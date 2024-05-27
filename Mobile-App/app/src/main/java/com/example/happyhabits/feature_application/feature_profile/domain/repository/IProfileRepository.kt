package com.example.happyhabits.feature_application.feature_profile.domain.repository

interface IProfileRepository {
    suspend fun addNewFriendship(scannerId: String, friendId: String)
}