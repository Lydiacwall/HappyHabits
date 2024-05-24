package com.example.happyhabits.feature_application.feature_food.presentation.food_search_screen

import com.example.happyhabits.feature_application.feature_food.domain.model.Macros
import com.example.happyhabits.feature_application.feature_food.domain.model.SpecificFood

data class FoodSearchState (
    val searchInput: String? ="",
    val meal: String?="",
    val specificFood: SpecificFood=
        SpecificFood(
            foodId = "",
            name = "",
            meal = "",
            calories = 0.0f,
            protein = 0.0f,
            fats = 0.0f,
            carbs = 0.0f,
            fiber = 0.0f,
            quantity = 0.0f,
            measurement = ""
        ),
    val foodsAccordingToSearch: List<String> = listOf()
)