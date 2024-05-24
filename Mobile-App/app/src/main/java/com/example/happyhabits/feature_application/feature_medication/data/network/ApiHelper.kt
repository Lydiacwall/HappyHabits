package com.example.happyhabits.feature_application.feature_medication.data.network

import android.util.Log
import com.example.happyhabits.core.data.model.Mapper.toDomain
import com.example.happyhabits.feature_application.feature_medication.Medicine
import com.example.happyhabits.feature_application.feature_medication.MedicineForm
import com.example.happyhabits.feature_application.feature_medication.data.model.MedicationForm
import com.example.happyhabits.feature_application.feature_medication.data.model.RemovalForm

class ApiHelper(private val apiService: ApiService) {
    suspend fun addMedicine(userId: String, name: String, dosageQuantity: Float?, dosageUnitMeasurement: String?, startDay: String, endDay: String, timesShouldBeTakenToday: Int, notes: String? ): String
    {
        try {
            val response = apiService.addMedication(
                MedicineForm(
                    userId = userId,
                    name = name,
                    dosageQuantity = dosageQuantity,
                    dosageUnitMeasurement = dosageUnitMeasurement,
                    startDay = startDay,
                    endDay = endDay,
                    timesShouldBeTaken = timesShouldBeTakenToday,
                    notes = notes
                )
            )
            return response.toString()
        } catch (e: Exception) {
            throw e;
        }
    }
    suspend fun removeMedicine(userId:String, id: String):String{
        try {
            val response = apiService.removeMedication(
                RemovalForm(userId =userId, id = id)
            )
            return response.toString()
        } catch (e: Exception) {
            throw e;
        }
    }
    suspend fun logMedicine(userId: String, date: String, medIds: List<String>): String{
        try {
            val idsToStrings = medIds.map { it.toString() }
            val medicationForm = MedicationForm(userId= userId, date = date, medicines = idsToStrings )
            val response = apiService.logMedication(medicationForm)
            return response.toString()
        } catch (e: Exception) {
            throw e;
        }
    }
    suspend fun retrieveMedicines(userId: String): List<Medicine> {
        try {
            val response = apiService.retrieveMedicines(userId)
            val medsList: MutableList<Medicine> = mutableListOf()
            if(response.isSuccessful)
            {
                val medicineDtos = response.body()
                if (medicineDtos != null) {
                    for(medDto in medicineDtos){
                        val newMed = medDto.toDomain()
                        medsList.add(newMed)
                    }
                }
            }
            return medsList
        } catch (e: Exception) {
            throw e;
        }
    }
}