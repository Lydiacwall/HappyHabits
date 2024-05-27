package com.example.happyhabits.feature_application.feature_medication.data.repository

import com.example.happyhabits.feature_application.feature_medication.Medicine
import com.example.happyhabits.feature_application.feature_medication.data.network.ApiHelper
import com.example.happyhabits.feature_application.feature_medication.domain.repository.IMedicationRepository
import com.example.happyhabits.feature_application.feature_workout.domain.model.Workout

class MedicationRepositoryImpl (
    private val medicineApi: ApiHelper
): IMedicationRepository {
    @Throws(Medicine.InvalidMedicationException::class)
    override suspend fun addNewMedication(userId: String, name: String, dosageQuantity: Float?, dosageUnitMeasurement: String?, startDay: String, endDay: String, timesShouldBeTakenToday: Int, notes: String? ): String {
        try {
            return medicineApi.addMedicine(userId, name, dosageQuantity, dosageUnitMeasurement, startDay, endDay, timesShouldBeTakenToday, notes)
        } catch (e: Exception) {
            throw e;
        }
    }
    @Throws(Medicine.InvalidMedicationException::class)
    override suspend fun removeMedication(userId:String, id: String): String {
        try {
            return medicineApi.removeMedicine(userId, id)
        } catch (e: Exception) {
            throw e;
        }
    }
    @Throws(Medicine.InvalidMedicationException::class)
    override suspend fun logMedication(userId: String, date: String, medIds: List<String>): String {
        try {
            return medicineApi.logMedicine(userId, date, medIds)
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