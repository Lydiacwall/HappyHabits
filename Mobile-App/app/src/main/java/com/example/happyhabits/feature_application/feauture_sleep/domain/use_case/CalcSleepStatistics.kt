package com.example.happyhabits.feature_application.feauture_sleep.domain.use_case

import com.example.happyhabits.feature_application.feauture_sleep.domain.repository.ISleepRepository

class CalcSleepStatistics(
    private val sleepRepository: ISleepRepository
) {
    suspend operator fun invoke(userId: String, monday: String, sunday: String) {
        try {
            sleepRepository.caclSleepStats(userId, monday,sunday)
        } catch (e: Exception) {
            throw e;
        }
    }

}