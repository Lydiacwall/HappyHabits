package com.example.happyhabits.feature_application.feauture_sleep.domain.use_case

import com.example.happyhabits.feature_authentication.domain.repository.IUserRepository
import java.time.LocalDate

class UpdateSleepGoal(
    private val userRepository: IUserRepository
) {
    suspend operator fun invoke(userId: String, sleepGoal: Int) {
        try {
            userRepository.updateSleepGoal(userId= userId, sleepGoal= sleepGoal)
        } catch (e: Exception) {
            throw e;
        }
    }
}