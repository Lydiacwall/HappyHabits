package com.example.happyhabits.feature_application.feature_mood.presentation.mood_statistics_screen

 sealed class MoodStatisticsPageEvent {

    data class MonthHasChanged(val month : String ) : MoodStatisticsPageEvent()
}