package com.example.happyhabits.feature_application.feature_profile.data.network

import com.example.happyhabits.feature_application.feature_profile.data.model.FriendGroupForm

class ApiHelper(private val apiService: ApiService) {
    suspend fun addNewFriendship(friendGroupForm: FriendGroupForm) {
        try {
            val response = apiService.addNewFriendGroup(friendGroupForm)
            if (response.code() == 409) {
                throw Exception("This user is already added to your friend group")
            }
        }
        catch (e: Exception) {
            throw e
        }
    }
}
