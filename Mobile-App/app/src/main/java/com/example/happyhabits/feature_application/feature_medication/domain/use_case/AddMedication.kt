package com.example.happyhabits.feature_application.feature_medication.domain.use_case

import com.example.happyhabits.feature_application.feature_medication.Medicine
import com.example.happyhabits.feature_application.feature_medication.MedicineForm
import com.example.happyhabits.feature_application.feature_medication.domain.repository.IMedicationRepository

class AddMedication(
    private val repository: IMedicationRepository
) {

    @Throws(Medicine.InvalidMedicationException::class)
    suspend operator fun invoke(userId: String, name: String, dosageQuantity: Float?, dosageUnitMeasurement: String?, startDay: String, endDay: String, timesShouldBeTakenToday: Int, notes: String? ): String {
        try {
            if (name.isBlank()) {
                throw Medicine.InvalidMedicationException("Name")
            }
            if (startDay == "MM/DD/YY") {
                throw Medicine.InvalidMedicationException("Start Day")
            }
            if (endDay == "MM/DD/YY") {
                throw Medicine.InvalidMedicationException("End Day")
            }
            if (timesShouldBeTakenToday <= 0) {
                throw Medicine.InvalidMedicationException("Times Should Be Taken Today")
            }
            // Add any other validations you might need here
            return repository.addNewMedication(userId, name, dosageQuantity, dosageUnitMeasurement, startDay, endDay, timesShouldBeTakenToday, notes)
        } catch (e: Exception) {
            throw e;
        }
    }
}
