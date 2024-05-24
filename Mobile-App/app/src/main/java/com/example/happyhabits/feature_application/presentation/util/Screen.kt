package com.example.happyhabits.feature_application.presentation.util

sealed class Screen(val route: String) {
    data object HomePageScreen: Screen("home_page_screen")
    data object WorkoutPageScreen: Screen("workout_page_screen")
    data object WorkoutPopUpScreen: Screen("workout_pop_up_screen")
    data object ToiletPageScreen : Screen("toilet_page_screen")
    data object SleepPageScreen : Screen("sleep_page_screen")
    data object ProfilePageScreen: Screen("profile_page_screen")
    data object MoodPageScreen : Screen("mood_page_screen")
    data object MedicationPageScreen: Screen("medication_page_screen")
    data object InboxPageScreen : Screen("inbox_screen")
    data object ChatPageScreen : Screen("chat_screen")
    data object SymptomsPageScreen: Screen("symptoms_page_screen")
    data object StatisticsPageScreen : Screen ("statistics_page_screen")
    data object SleepStatisticsPageScreen : Screen ("sleep_statistics_page_screen")
    data object MoodStatisticsPageScreen : Screen ("mood_statistics_page_screen")
    data object SymptomsStatisticsPageSceen : Screen("symptoms_statistics_page_screen")
    data object FoodPageScreen: Screen("food_page_screen")
    data object FoodSearchScreen: Screen("food_search_screen")
    data object FoodDetailsScreen: Screen("food_details_screen")
}