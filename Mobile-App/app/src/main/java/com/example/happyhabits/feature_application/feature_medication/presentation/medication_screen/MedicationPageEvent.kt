package com.example.happyhabits.feature_application.feature_medication.presentation.medication_screen

import androidx.navigation.NavController

sealed class MedicationPageEvent {
    data class ChangePage(val addOrRemoveOrBack: String):  MedicationPageEvent()
    data class MedicationTaken(val idOfMedication: Int):  MedicationPageEvent()
    data class RemoveMedication(val idOfMedication: Int):  MedicationPageEvent()
    data class AddMedication(val noImportantString: String):  MedicationPageEvent()
    data class UpdatedAddMedication(val typeChanged: String, val newValueString: String?, val newValueFloat: Float?, val newValueInt:Int?): MedicationPageEvent()
    data class NextPage(val noImportantString: String):  MedicationPageEvent()
    data class PrevPage(val noImportantString: String):  MedicationPageEvent()
}