package com.example.happyhabits.feature_application.feature_medication.domain.use_case

import com.example.happyhabits.feature_application.feature_medication.Medicine
import com.example.happyhabits.feature_application.feature_medication.domain.repository.IMedicationRepository

class RetrieveMedications(
    private val repository: IMedicationRepository
) {

    @Throws(Medicine.InvalidMedicationException::class)
    suspend operator fun invoke(userId: String): List<Medicine> {
        try {
            // Add any other validations you might need here
            return repository.retrieveMedications(userId)
        } catch (e: Exception) {
            throw e;
        }
    }
}