package com.example.happyhabits.feature_application.feauture_sleep.domain.repository

import java.time.LocalDate

interface ISleepRepository {
    suspend fun addSleepHabit(userId: String, date: LocalDate, time: String, quality: String)
}