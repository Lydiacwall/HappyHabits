package com.example.happyhabits.feature_application.feature_medication.domain.use_case

import com.example.happyhabits.feature_application.feature_medication.Medicine
import com.example.happyhabits.feature_application.feature_medication.MedicineForm
import com.example.happyhabits.feature_application.feature_medication.domain.repository.IMedicationRepository

class AddMedication(
    private val repository: IMedicationRepository
) {

    @Throws(Medicine.InvalidMedicationException::class)
    suspend operator fun invoke(newMed: Medicine): String {
        try {
            if (newMed.getName().isBlank()) {
                throw Medicine.InvalidMedicationException("Name")
            }
            if (newMed.getStartDay()== "DD/MM/YY") {
                throw Medicine.InvalidMedicationException("Start Day")
            }
            if (newMed.getEndDay() == "DD/MM/YY") {
                throw Medicine.InvalidMedicationException("End Day")
            }
            if (newMed.getTimesShouldBeTakenToday() <= 0) {
                throw Medicine.InvalidMedicationException("Times Should Be Taken Today")
            }
            // Add any other validations you might need here
            return repository.addNewMedication(newMed)
        } catch (e: Exception) {
            throw e;
        }
    }
}
