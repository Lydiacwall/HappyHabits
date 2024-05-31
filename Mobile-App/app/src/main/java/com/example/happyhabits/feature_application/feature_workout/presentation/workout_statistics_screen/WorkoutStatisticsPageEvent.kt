package com.example.happyhabits.feature_application.feature_workout.presentation.workout_statistics_screen

import androidx.navigation.NavController

sealed class WorkoutStatisticsPageEvent {
    data class ChangePage(val HabitOrBack: String, val navController: NavController): WorkoutStatisticsPageEvent()
}