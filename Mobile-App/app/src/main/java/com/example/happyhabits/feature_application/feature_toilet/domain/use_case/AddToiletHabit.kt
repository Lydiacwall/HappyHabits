package com.example.happyhabits.feature_application.feature_toilet.domain.use_case

import com.example.happyhabits.feature_application.feature_toilet.domain.model.Toilet
import com.example.happyhabits.feature_application.feature_toilet.domain.repository.IToiletRepository
import java.time.LocalDate

class AddToiletHabit(
    private val repository: IToiletRepository
) {
    suspend operator fun invoke(userId: String, date: LocalDate, type: String, time: String, notes: String){
        try {
            repository.addToiletHabit(userId, date, type, time, notes)
        } catch (e: Exception) {
            throw e;
        }
    }
}
