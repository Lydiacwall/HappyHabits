package com.example.happyhabits.feature_toilet.util



sealed class Screen (val route: String){
    data object HomePageScreen: Screen("home_page_screen")
    data object ToiletPageScreen: Screen("toilet_page_screen")
}