package com.example.happyhabits.feature_application.feature_symptoms.presentation.syptoms_screen

sealed class SymptomsPageEvent {
    data class NotesChanged(val notes:String) : SymptomsPageEvent()
    data class SymptomChanged(val symptom:String) : SymptomsPageEvent()
    data class AddSymptomLog(val notes:String, val symptom:String) : SymptomsPageEvent()
}