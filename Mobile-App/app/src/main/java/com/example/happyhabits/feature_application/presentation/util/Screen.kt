package com.example.happyhabits.feature_application.presentation.util

sealed class Screen(val route: String) {
    data object HomePageScreen: Screen("home_page_screen")
}