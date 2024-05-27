package com.example.happyhabits.feature_application.feature_medication.data.model

import kotlinx.serialization.Serializable

@Serializable
class MedicationForm(
    val userId: String,
    val date: String,
    val medicines: List<String>
){
}