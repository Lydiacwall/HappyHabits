package com.example.happyhabits.feature_application.feature_profile.presentation.profile_page

import androidx.navigation.NavController
import com.example.happyhabits.feature_application.feature_workout.presentation.workout_screen.WorkoutPageEvent
import com.example.happyhabits.feature_application.home_page.HomePageEvent

sealed class ProfileEvent {
    data class ChangePage(val pageName: String, val navController:NavController): ProfileEvent()

}