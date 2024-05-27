package com.example.happyhabits.feature_application.feature_profile.domain.use_case

import com.example.happyhabits.feature_application.feature_profile.domain.repository.IProfileRepository

class AddNewFriendship(
    private val repository: IProfileRepository
) {
    suspend operator fun invoke(scannerId: String, friendId: String) {
        try {
            repository.addNewFriendship(scannerId= scannerId, friendId = friendId)
        }
        catch (e: Exception) {
            throw e
        }
    }
}