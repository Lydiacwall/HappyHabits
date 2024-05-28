package com.example.happyhabits.feature_application.feature_mood.domain.repository

import java.time.LocalDate

interface IMoodRepository {
    suspend fun addMoodHabit(userId: String, date: LocalDate, diary: String, scale: String)
    //suspend fun calcMoodStatistics(userId : String , month : String)
}