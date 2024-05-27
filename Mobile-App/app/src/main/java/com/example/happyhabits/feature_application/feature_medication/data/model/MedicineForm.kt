package com.example.happyhabits.feature_application.feature_medication

class MedicineForm (
    val userId: String,
    val name: String,
    val dosageQuantity: Float?,
    val dosageUnitMeasurement: String?,
    val startDay: String,
    val endDay: String,
    val timesShouldBeTaken: Int,
    val notes: String?
){
    override fun toString(): String {
        return "MedicineForm(userId='$userId', name='$name', dosageQuantity=$dosageQuantity, " +
                "dosageUnitMeasurement='$dosageUnitMeasurement', startDay='$startDay', " +
                "endDay='$endDay', timesShouldBeTaken=$timesShouldBeTaken, notes=$notes)"
    }
}
