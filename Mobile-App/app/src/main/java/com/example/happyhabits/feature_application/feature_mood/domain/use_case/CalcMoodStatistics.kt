package com.example.happyhabits.feature_application.feature_mood.domain.use_case

import com.example.happyhabits.feature_authentication.domain.repository.IUserRepository

class CalcMoodStatistics(
    private val userRepository: IUserRepository
) {
    suspend operator fun invoke(userId: String, month: String) {
        try {
            //userRepository.updateSleepGoal(userId= userId, sleepGoal= sleepGoal)
        } catch (e: Exception) {
            throw e;
        }
    }
}