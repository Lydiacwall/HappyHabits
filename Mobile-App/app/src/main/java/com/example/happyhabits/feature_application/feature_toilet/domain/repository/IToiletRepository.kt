package com.example.happyhabits.feature_application.feature_toilet.domain.repository

import com.example.happyhabits.feature_application.feature_toilet.domain.model.Toilet
import java.time.LocalDate

interface IToiletRepository {
    suspend fun addToiletHabit(userId: String, date: LocalDate, type: String, time: String, notes: String)
}
