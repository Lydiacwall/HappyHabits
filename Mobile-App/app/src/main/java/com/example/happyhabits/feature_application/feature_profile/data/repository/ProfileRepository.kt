package com.example.happyhabits.feature_application.feature_profile.data.repository

import com.example.happyhabits.feature_application.feature_profile.data.model.FriendGroupForm
import com.example.happyhabits.feature_application.feature_profile.data.network.ApiHelper
import com.example.happyhabits.feature_application.feature_profile.domain.repository.IProfileRepository

class ProfileRepository(
    private val profileApi: ApiHelper
): IProfileRepository {
    override suspend fun addNewFriendship(scannerId: String, friendId: String) {
        try {
            profileApi.addNewFriendship(
                FriendGroupForm(scannerId, friendId)
            )
        } catch (e: Exception) {
            throw e
        }
    }

}