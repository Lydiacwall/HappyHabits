package com.example.happyhabits.feature_application.presentation.util

sealed class Screen(val route: String) {
    data object HomePageScreen: Screen("home_page_screen")
    data object WorkoutPageScreen: Screen("workout_page_screen")
    data object WorkoutPopUpScreen: Screen("workout_pop_up_screen")
    data object ToiletPageScreen : Screen("toilet_page_screen")
    data object SleepPageScreen : Screen("sleep_page_screen")
    data object MoodPageScreen : Screen("mood_page_screen")
}