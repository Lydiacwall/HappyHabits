package com.example.happyhabits.feature_application.feature_workout.presentation.workout_pop_up_screen

import androidx.navigation.NavController

sealed class WorkoutPopUpEvent {
    data class ChangePage(val back: String, val navController: NavController): WorkoutPopUpEvent()

}