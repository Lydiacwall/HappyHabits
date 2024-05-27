package com.example.happyhabits.feature_application.feature_medication.presentation.medication_screen

import com.example.happyhabits.feature_application.feature_medication.Medicine

data class MedicationScreenState (

    val usersMedications: List<Medicine> = emptyList(),
    val nameToBeAdded: String = "",
    val dosageQuantityToBeAdded: Float? = null,
    val dosageUnitMeasurementToBeAdded: String? = null,
    val startDayToBeAdded: String = "MM/DD/YY",
    val endDayToBeAdded: String = "MM/DD/YY",
    val successPerDayToBeAdded: Float? = 0.0f,
    val timesTakenTodayToBeAdded: Int? = 0,
    val timesShouldBeTakenTodayToBeAdded: Int = -1,
    val takenToBeAdded: Boolean = false,
    val notesToBeAdded: String? = "",
    val numOfPages: Int =if((usersMedications.size)%9==0){(usersMedications.size)/9}else{((usersMedications.size)/9)+1},
    val currentPage: Int = 0,
    val idsOfMedicationsTaken: List<String> = emptyList(),
    val currentDate: String = "MM/DD/YY"
)