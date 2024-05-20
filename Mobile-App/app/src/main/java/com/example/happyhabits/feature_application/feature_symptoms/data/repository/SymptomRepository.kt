package com.example.happyhabits.feature_application.feature_symptoms.data.repository

import com.example.happyhabits.feature_application.feature_symptoms.data.model.SymptomForm
import com.example.happyhabits.feature_application.feature_symptoms.data.network.ApiHelper
import com.example.happyhabits.feature_application.feature_symptoms.domain.repository.ISymptomRepository
import java.time.LocalDate

class SymptomRepository(
    private val symptomApi: ApiHelper
): ISymptomRepository {
    override suspend fun addSymptomHabit(
        userId: String,
        date: LocalDate,
        name: String,
        notes: String
    ) {
        try {
            symptomApi.addSymptomHabit(
                SymptomForm(
                    userId = userId,
                    date = date.toString(),
                    name = name,
                    notes = notes
                )
            )
        } catch (e: Exception) {
            throw e;
        }
    }

}