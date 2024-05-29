package com.example.happyhabits.feature_application.feature_mood.domain.use_case

import com.example.happyhabits.feature_application.feature_mood.data.model.MoodStatistics
import com.example.happyhabits.feature_application.feature_mood.data.repository.MoodRepository
import com.example.happyhabits.feature_authentication.domain.repository.IUserRepository

class CalcMoodStatistics(
    private val moodRepository: MoodRepository
) {
    suspend operator fun invoke(userId: String) : MoodStatistics? {
        try {
            return moodRepository.calcMoodStatistics(userId)
        } catch (e: Exception) {
            throw e;
        }
    }
}