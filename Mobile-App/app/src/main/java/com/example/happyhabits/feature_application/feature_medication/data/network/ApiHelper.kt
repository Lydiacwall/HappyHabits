package com.example.happyhabits.feature_application.feature_medication.data.network

import com.example.happyhabits.core.data.model.Mapper.toDomain
import com.example.happyhabits.feature_application.feature_medication.Medicine
import com.example.happyhabits.feature_application.feature_medication.MedicineForm

class ApiHelper(private val apiService: ApiService) {
    suspend fun addMedicine(medicine: Medicine){
        try {
            val response = apiService.addMedication(
                MedicineForm(
                    userId = medicine.getUserId()?: "",
                    name = medicine.getName(),
                    dosageQuantity = medicine.getDosageQuantity(),
                    dosageUnitMeasurement = medicine.getDosageUnitMeasurement(),
                    startDay = medicine.getStartDay(),
                    endDay = medicine.getEndDay(),
                    timesShouldBeTakenToday = medicine.getTimesShouldBeTakenToday(),
                    notes = medicine.getNotes()
                )
            )
        } catch (e: Exception) {
            throw e;
        }
    }
    suspend fun removeMedicine(medicine: Medicine){
        try {
            val response = apiService.removeMedication(
                MedicineForm(
                    userId = medicine.getUserId()?: "",
                    name = medicine.getName(),
                    dosageQuantity = medicine.getDosageQuantity(),
                    dosageUnitMeasurement = medicine.getDosageUnitMeasurement(),
                    startDay = medicine.getStartDay(),
                    endDay = medicine.getEndDay(),
                    timesShouldBeTakenToday = medicine.getTimesShouldBeTakenToday(),
                    notes = medicine.getNotes()
                )
            )
        } catch (e: Exception) {
            throw e;
        }
    }
    suspend fun logMedicine(medicine: Medicine){
        try {
            val response = apiService.logMedication(
                MedicineForm(
                    userId = medicine.getUserId()?: "",
                    name = medicine.getName(),
                    dosageQuantity = medicine.getDosageQuantity(),
                    dosageUnitMeasurement = medicine.getDosageUnitMeasurement(),
                    startDay = medicine.getStartDay(),
                    endDay = medicine.getEndDay(),
                    timesShouldBeTakenToday = medicine.getTimesShouldBeTakenToday(),
                    notes = medicine.getNotes()
                )
            )
        } catch (e: Exception) {
            throw e;
        }
    }
    suspend fun retrieveMedicines(userId: String): List<Medicine> {
        try {
            val response = apiService.retrieveMedicines(userId)
            var medsList: MutableList<Medicine> = mutableListOf()
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