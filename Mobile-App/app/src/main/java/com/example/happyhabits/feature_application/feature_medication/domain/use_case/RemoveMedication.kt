package com.example.happyhabits.feature_application.feature_medication.domain.use_case

import com.example.happyhabits.feature_application.feature_medication.Medicine
import com.example.happyhabits.feature_application.feature_medication.domain.repository.IMedicationRepository

class RemoveMedication(
    private val repository: IMedicationRepository
) {

    @Throws(Medicine.InvalidMedicationException::class)
    suspend operator fun invoke(userId:String, id: String): String {
        try{
            // Add any other validations you might need here
            return repository.removeMedication(userId, id)
        } catch (e: Exception) {
            throw e;
        }
    }
}