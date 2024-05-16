package com.example.happyhabits.feature_application.feature_medication.presentation.medication_screen

import com.example.happyhabits.feature_application.feature_medication.Medicine

data class MedicationScreenState (

    val usersMedications: List<Medicine> = listOf(Medicine(
        medId = null,
        userId = null,
        name = "",
        dosageQuantity = null,
        dosageUnitMeasurement = "",
        startDay = "",
        endDay = "",
        timesShouldBeTakenToday = 0,
        timesTakenToday = null,
        notes = ""
    ),Medicine(
        medId = null,
        userId = null,
        name = "",
        dosageQuantity = null,
        dosageUnitMeasurement = "",
        startDay = "",
        endDay = "",
        timesShouldBeTakenToday = 0,
        timesTakenToday = null,
        notes = ""
    ),Medicine(
            medId = null,
        userId = null,
        name = "",
        dosageQuantity = null,
        dosageUnitMeasurement = "",
        startDay = "",
        endDay = "",
        timesShouldBeTakenToday = 0,
        timesTakenToday = null,
        notes = ""
    )),
    val nameToBeAdded: String = "",
    val dosageQuantityToBeAdded: Float? = null,
    val dosageUnitMeasurementToBeAdded: String? = null,
    val startDayToBeAdded: String = "MMM dd yyyy",
    val endDayToBeAdded: String = "MMM dd yyyy",
    val successPerDayToBeAdded: Float? = 0.0f,
    val timesTakenTodayToBeAdded: Int? = 0,
    val timesShouldBeTakenTodayToBeAdded: Int = -1,
    val takenToBeAdded: Boolean = false,
    val notesToBeAdded: String? = "",
    val numOfPages: Int =if(usersMedications!=null) {
                            var pages = usersMedications?.size?: 1
                            if ((pages) % 9 == 0) {
                                (0) / 9
                            } else {
                                ((pages) / 9) + 1
                            }
                        }else{0},
    val currentPage: Int = 0
)