package com.example.happyhabits.feature_application.feature_medication.data.repository

import com.example.happyhabits.feature_application.feature_medication.Medicine
import com.example.happyhabits.feature_application.feature_medication.data.network.ApiHelper
import com.example.happyhabits.feature_application.feature_medication.domain.repository.IMedicationRepository
import com.example.happyhabits.feature_application.feature_workout.domain.model.Workout

class MedicationRepositoryImpl (
    private val medicineApi: ApiHelper
): IMedicationRepository {
    @Throws(Medicine.InvalidMedicationException::class)
    override suspend fun addNewMedication(medication: Medicine): String {
        try {
            return medicineApi.addMedicine(medication).toString()
        } catch (e: Exception) {
            throw e;
        }
    }
    @Throws(Medicine.InvalidMedicationException::class)
    override suspend fun removeMedication(medication: Medicine): String {
        try {
            return medicineApi.removeMedicine(medication).toString()
        } catch (e: Exception) {
            throw e;
        }
    }
    @Throws(Medicine.InvalidMedicationException::class)
    override suspend fun logMedication(medication: Medicine): String {
        try {
            return medicineApi.logMedicine(medication).toString()
        } catch (e: Exception) {
            throw e;
        }
    }

    @Throws(Medicine.InvalidMedicationException::class)
    override suspend fun retrieveMedications(userId: String): List<Medicine>
    {
        try {
            return medicineApi.retrieveMedicines(userId)
        } catch (e: Exception) {
            throw e;
        }
    }

}