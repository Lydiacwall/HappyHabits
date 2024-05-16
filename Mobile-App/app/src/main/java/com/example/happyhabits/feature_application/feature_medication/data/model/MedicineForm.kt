package com.example.happyhabits.feature_application.feature_medication

class MedicineForm (
    val userId: String,
    val name: String,
    val dosageQuantity: Float?,
    val dosageUnitMeasurement: String?,
    val startDay: String,
    val endDay: String,
    val timesShouldBeTakenToday: Int,
    val notes: String?
){}
