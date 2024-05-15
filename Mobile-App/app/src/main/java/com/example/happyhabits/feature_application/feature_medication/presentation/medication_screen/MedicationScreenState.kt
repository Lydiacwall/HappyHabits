package com.example.happyhabits.feature_application.feature_medication.presentation.medication_screen

import com.example.happyhabits.feature_application.feature_medication.Medicine
import com.example.happyhabits.feature_application.feature_medication.model.MedicationTaken

data class MedicationScreenState (

    val usersMedications: List<Medicine> = listOf(
        Medicine(
            name = "Aspirin",
            dosageQuantity = 1f,
            dosageUnitMeasurement = "tablet",
            startDay = "2024-05-08",
            endDay = "2024-05-15",
            successPerDay = 0.0f,
            timesTakenToday= 0,
            timesShouldBeTakenToday = 1,
            taken = false,
            notes = "Take with water after meal."
        ),
        Medicine(
            name = "Ibuprofen",
            dosageQuantity = 1f,
            dosageUnitMeasurement = "tablet",
            startDay = "2024-05-08",
            endDay = "2024-05-15",
            successPerDay = 0.0f,
            timesTakenToday= 0,
            timesShouldBeTakenToday = 1,
            taken = false,
            notes = "Do not exceed recommended dosage."
        ),
        Medicine(
            name = "Amoxicillin",
            dosageQuantity = 500f,
            dosageUnitMeasurement = "mg",
            startDay = "2024-05-08",
            endDay = "2024-05-15",
            successPerDay = 0.0f,
            timesTakenToday= 0,
            timesShouldBeTakenToday = 1,
            taken = false,
            notes = "Complete the full course of antibiotics."
        ),
        Medicine(
            name = "Lisinopril",
            dosageQuantity = 10f,
            dosageUnitMeasurement = "mg",
            startDay = "2024-05-08",
            endDay = "2024-05-15",
            successPerDay = 0.0f,
            timesTakenToday= 0,
            timesShouldBeTakenToday = 1,
            taken = false,
            notes = "Monitor blood pressure regularly."
        ),
        Medicine(
            name = "Metformin",
            dosageQuantity = 850f,
            dosageUnitMeasurement = "mg",
            startDay = "2024-05-08",
            endDay = "2024-05-15",
            successPerDay = 0.0f,
            timesTakenToday= 0,
            timesShouldBeTakenToday = 2,
            taken = false,
            notes = "Take with meals."
        ),
        Medicine(
            name = "Simvastatin",
            dosageQuantity = 20f,
            dosageUnitMeasurement = "mg",
            startDay = "2024-05-08",
            endDay = "2024-05-15",
            successPerDay = 0.0f,
            timesTakenToday= 0,
            timesShouldBeTakenToday = 1,
            taken = false,
            notes = "Take at bedtime."
        ),
        Medicine(
            name = "Prednisone",
            dosageQuantity = 5f,
            dosageUnitMeasurement = "mg",
            startDay = "2024-05-08",
            endDay = "2024-05-15",
            successPerDay = 0.0f,
            timesTakenToday= 0,
            timesShouldBeTakenToday = 1,
            taken = false,
            notes = "Take with food to prevent stomach upset."
        ),
        Medicine(
            name = "Levothyroxine",
            dosageQuantity = 50f,
            dosageUnitMeasurement = "mcg",
            startDay = "2024-05-08",
            endDay = "2024-05-15",
            successPerDay = 0.0f,
            timesTakenToday= 0,
            timesShouldBeTakenToday = 1,
            taken = false,
            notes = "Take on an empty stomach."
        ),
        Medicine(
            name = "Diazepam",
            dosageQuantity = 2f,
            dosageUnitMeasurement = "mg",
            startDay = "2024-05-08",
            endDay = "2024-05-15",
            successPerDay = 0.0f,
            timesTakenToday= 0,
            timesShouldBeTakenToday = 1,
            taken = false,
            notes = "May cause drowsiness. Avoid alcohol."
        ),
        Medicine(
            name = "Omeprazole",
            dosageQuantity = 20f,
            dosageUnitMeasurement = "mg",
            startDay = "2024-05-08",
            endDay = "2024-05-15",
            successPerDay = 0.0f,
            timesTakenToday= 0,
            timesShouldBeTakenToday = 1,
            taken = false,
            notes = "Take before meals."
        )
    ),
    val medicationsTaken: List<MedicationTaken> = listOf(),
    val nameToBeAdded: String = "",
    val dosageQuantityToBeAdded: Float? = null,
    val dosageUnitMeasurementToBeAdded: String? = null,
    val startDayToBeAdded: String? = null,
    val endDayToBeAdded: String? = null,
    val successPerDayToBeAdded: Float? = 0.0f,
    val timesTakenTodayToBeAdded: Int? = 0,
    val timesShouldBeTakenTodayToBeAdded: Int? = null,
    val takenToBeAdded: Boolean = false,
    val notesToBeAdded: String? = "",
    val numOfPages: Int =if((usersMedications.size)%9==0){(usersMedications.size)/9}else{((usersMedications.size)/9)+1},
    val currentPage: Int = 0
)