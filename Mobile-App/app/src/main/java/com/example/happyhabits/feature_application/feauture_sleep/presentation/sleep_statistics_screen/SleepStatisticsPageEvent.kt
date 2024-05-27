package com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_statistics_screen

sealed class SleepStatisticsPageEvent {
    data class WeekhasChanged(val startDate : String , val endDate : String) : SleepStatisticsPageEvent()

}