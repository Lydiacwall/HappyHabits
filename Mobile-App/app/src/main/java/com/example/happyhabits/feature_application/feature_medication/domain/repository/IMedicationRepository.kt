package com.example.happyhabits.feature_application.feature_medication.domain.repository

import com.example.happyhabits.core.domain.model.User
import com.example.happyhabits.feature_application.feature_medication.Medicine
    import com.example.happyhabits.feature_application.feature_medication.MedicineForm
import com.example.happyhabits.feature_application.feature_medication.domain.use_case.AddMedication

interface IMedicationRepository {
//    suspend fun getUserByPasswordAndEmail(password: String, email: String): User?
    suspend fun addNewMedication(userId: String, name: String, dosageQuantity: Float?, dosageUnitMeasurement: String?, startDay: String, endDay: String, timesShouldBeTakenToday: Int, notes: String? ): String
    suspend fun removeMedication(userId:String, id: String): String
    suspend fun logMedication(userId: String, date: String, medIds: List<String>): String
    suspend fun retrieveMedications(userId: String): List<Medicine>
}