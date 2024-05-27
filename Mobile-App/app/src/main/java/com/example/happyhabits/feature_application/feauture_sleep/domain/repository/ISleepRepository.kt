package com.example.happyhabits.feature_application.feauture_sleep.domain.repository

import com.example.happyhabits.feature_application.feauture_sleep.data.model.SleepStatistics
import java.time.LocalDate

interface ISleepRepository {
    suspend fun addSleepHabit(userId: String, date: LocalDate, time: String, quality: String)
    suspend fun calcSleepStats(userId : String, monday : String, sunday : String) : SleepStatistics?
}

