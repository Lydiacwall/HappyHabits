package com.example.happyhabits.feature_application.feauture_sleep.domain.use_case

import com.example.happyhabits.feature_application.feauture_sleep.domain.repository.ISleepRepository
import java.time.LocalDate

class AddSleepHabit(
    private val repository: ISleepRepository
) {
    suspend operator fun invoke(userId: String, date: LocalDate, time: String, quality: String) {
        try {
            repository.addSleepHabit(userId, date, time, quality)
        } catch (e: Exception) {
            throw e;
        }
    }
}