package com.example.happyhabits.feature_application.feature_food.presentation.food_search_screen;

import androidx.navigation.NavController
sealed class FoodSearchEvent {
    data class SearchInputChanged(val newSearchInput:String):  FoodSearchEvent()

}