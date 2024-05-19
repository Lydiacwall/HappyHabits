package com.example.happyhabits.feature_application.feature_symptoms.domain.use_case

import com.example.happyhabits.feature_application.feature_symptoms.domain.repository.ISymptomRepository
import java.time.LocalDate

class AddSymptom(
    private val repository: ISymptomRepository
) {
    suspend operator fun invoke(userId: String, date: LocalDate, name: String, notes: String) {
        try {
            repository.addSymptomHabit(userId= userId, date= date, name= name, notes= notes)
        } catch (e: Exception) {
            throw e;
        }
    }
}