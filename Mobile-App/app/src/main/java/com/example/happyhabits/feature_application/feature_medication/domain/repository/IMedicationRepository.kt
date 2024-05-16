package com.example.happyhabits.feature_application.feature_medication.domain.repository

import com.example.happyhabits.core.domain.model.User
import com.example.happyhabits.feature_application.feature_medication.Medicine
    import com.example.happyhabits.feature_application.feature_medication.MedicineForm
import com.example.happyhabits.feature_application.feature_medication.domain.use_case.AddMedication

interface IMedicationRepository {
//    suspend fun getUserByPasswordAndEmail(password: String, email: String): User?
    suspend fun addNewMedication(medication: Medicine): String
    suspend fun removeMedication(medication: Medicine): String
    suspend fun logMedication(medication: Medicine): String
    suspend fun retrieveMedications(userId: String): List<Medicine>
}