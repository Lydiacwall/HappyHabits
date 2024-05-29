package com.example.happyhabits.feature_application.feature_symptoms.domain.repository

import com.example.happyhabits.feature_application.feature_symptoms.data.model.SymptomStatistics
import java.time.LocalDate

interface ISymptomRepository {
    suspend fun addSymptomHabit(userId: String, date: LocalDate, name: String, notes: String)
    suspend fun calcSymptomStatistics(userId: String, monthNumber: String): SymptomStatistics?
}