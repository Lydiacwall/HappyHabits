package com.example.happyhabits.feature_application.feature_medication.data.network

import com.example.happyhabits.feature_application.feature_medication.MedicineForm
import com.example.happyhabits.feature_application.feature_medication.data.model.RemovalForm
import com.example.happyhabits.feature_application.feature_medication.data.model.MedicineDto
import com.example.happyhabits.feature_application.feature_medication.data.model.MedicationForm
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("api/Medication/AddMedicine")
    suspend fun addMedication(@Body medicineForm: MedicineForm): Response<String>
    @GET("api/Medication/GetMedicinesToday")
    suspend fun retrieveMedicines(@Query("userId") userId: String): Response<List<MedicineDto>>
    @PATCH("api/Medication/RemoveMedicine")
    suspend fun removeMedication(@Body medicineForm: RemovalForm): Response<String>
    @POST("api/Medication/LogMedicationActivity")
    suspend fun logMedication(@Body medicationForm: MedicationForm): Response<String>
}
