package com.example.happyhabits.feature_application.feature_toilet.presentation.toilet_screen

import androidx.navigation.NavController

sealed class ToiletPageEvent {
    data class ChangePage(val HabitOrBack: String, val navController: NavController): ToiletPageEvent()
}