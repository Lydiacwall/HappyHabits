package com.example.happyhabits.feature_toilet.presentation.toilet_screen

import androidx.navigation.NavController
import com.example.happyhabits.feature_workout.presentation.workout_screen.WorkoutPageEvent

sealed class ToiletPageEvent {
    data class ChangePage(val HabitOrBack: String, val navController: NavController): ToiletPageEvent()
}