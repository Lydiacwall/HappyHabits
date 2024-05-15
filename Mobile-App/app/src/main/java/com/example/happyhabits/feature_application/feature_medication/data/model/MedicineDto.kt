package com.example.happyhabits.feature_application.feature_medication.data.model

import kotlinx.serialization.Serializable

@Serializable
class MedicineDto (
    val id: String?,
    val userId: String?,
    val name: String,
    val dosageQuantity: Float?,
    val dosageUnitMeasurement: String?,
    val startDay: String?,
    val endDay: String?,
    val timesShouldBeTaken: Int,
    val timesTaken: Int,
    val notes: String?
)