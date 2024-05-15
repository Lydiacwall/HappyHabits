package com.example.happyhabits.feature_application.feature_medication.domain.use_case

import com.example.happyhabits.feature_application.feature_medication.Medicine
import com.example.happyhabits.feature_application.feature_medication.domain.repository.IMedicationRepository

class RemoveMedication(
    private val repository: IMedicationRepository
) {

    @Throws(Medicine.InvalidMedicationException::class)
    suspend operator fun invoke(removeMed: Medicine): String {
        try {
            if (removeMed.getName().isBlank()) {
                throw Medicine.InvalidMedicationException("Name")
            }
            if (removeMed.getStartDay()== "DD/MM/YY") {
                throw Medicine.InvalidMedicationException("Start Day")
            }
            if (removeMed.getEndDay() == "DD/MM/YY") {
                throw Medicine.InvalidMedicationException("End Day")
            }
            if (removeMed.getTimesShouldBeTakenToday() <= 0) {
                throw Medicine.InvalidMedicationException("Times Should Be Taken Today")
            }
            // Add any other validations you might need here
            return repository.removeMedication(removeMed)
        } catch (e: Exception) {
            throw e;
        }
    }
}