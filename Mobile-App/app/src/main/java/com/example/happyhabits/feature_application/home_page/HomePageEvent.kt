package com.example.happyhabits.feature_application.home_page

import androidx.navigation.NavController

sealed class HomePageEvent {
    data class ChangePage(val pageName: String, val navController:NavController): HomePageEvent()

}
