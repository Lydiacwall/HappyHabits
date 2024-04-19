package com.example.happyhabits.feature_authentication.presentation.util

sealed class Screen(val route: String) {
    data object LoginScreen: Screen("login_screen")
    data object AddUserScreen: Screen("add_user_screen")
}