package com.example.happyhabits.feature_authentication.presentation.util

sealed class Screen(val route: String) {
    data object SplashScreen: Screen("splash_screen")
    data object GetStartedScreen: Screen("get_started_screen")
    data object LoginScreen: Screen("login_screen")
    data object AddUserScreen: Screen("add_user_screen")
    data object AddDoctorScreen: Screen("add_doctor_screen")
    data object ChooseRoleScreen: Screen("choose_role_screen")
    data object HomePageScreen: Screen("home_page_screen")


}