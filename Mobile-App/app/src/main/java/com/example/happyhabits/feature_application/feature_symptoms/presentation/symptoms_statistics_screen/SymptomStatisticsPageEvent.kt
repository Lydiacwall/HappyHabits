package com.example.happyhabits.feature_application.feature_symptoms.presentation.symptoms_statistics_screen

 sealed class SymptomStatisticsPageEvent {
     data class MonthHasChanged(val monthNumber : String) : SymptomStatisticsPageEvent()
}