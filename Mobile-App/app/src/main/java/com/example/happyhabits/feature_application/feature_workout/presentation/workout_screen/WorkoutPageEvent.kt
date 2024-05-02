package com.example.happyhabits.feature_application.feature_workout.presentation.workout_screen

import androidx.navigation.NavController

sealed class WorkoutPageEvent {
    data class ChangePage(val HabitOrBack: String, val navController: NavController): WorkoutPageEvent()
}