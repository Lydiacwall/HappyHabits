package com.example.happyhabits.feature_application.feature_mood.domain.use_case

import com.example.happyhabits.feature_application.feature_mood.domain.repository.IMoodRepository
import java.time.LocalDate

class AddMoodHabit(
    private val repository: IMoodRepository
) {
    suspend operator fun invoke(userId: String, date: LocalDate, diary: String, scale: String) {
        try {
            repository.addMoodHabit(userId= userId, date= date, diary= diary, scale= scale)
        } catch (e: Exception) {
            throw e;
        }
    }
}
