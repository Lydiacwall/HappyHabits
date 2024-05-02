package com.example.happyhabits.feature_workout.presentation.workout_pop_up_screen

import androidx.navigation.NavController
import com.example.happyhabits.feature_workout.presentation.workout_screen.WorkoutPageEvent

sealed class WorkoutPopUpEvent {
    data class ChangePage(val back: String, val navController: NavController): WorkoutPopUpEvent()

}