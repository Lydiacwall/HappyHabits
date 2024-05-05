package com.example.happyhabits.feature_application.feature_workout.presentation.util


sealed class Screen(val route: String) {
    data object HomePageScreen: Screen("home_page_screen")
    data object WorkoutPageScreen: Screen("workout_page_screen")
    data object WorkoutPopUpScreen: Screen("workout_pop_up_screen")
}