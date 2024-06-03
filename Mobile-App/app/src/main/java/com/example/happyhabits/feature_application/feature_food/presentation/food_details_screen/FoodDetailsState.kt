package com.example.happyhabits.feature_application.feature_food.presentation.food_details_screen

import com.example.happyhabits.feature_application.feature_food.domain.model.DataBaseFood
import com.example.happyhabits.feature_application.feature_food.domain.model.Macros
import com.example.happyhabits.feature_application.feature_food.domain.model.Measurement
import com.example.happyhabits.feature_application.feature_food.domain.model.SpecificFood

data class FoodDetailsState (
    val searchInput: String? ="",
    val quantityChosen: Float? = 0f,
    val meal:String="",
    val foodDefined:Boolean=false,
    val specificFoodMarcos: Macros = Macros(1f,1f,1f,1f, 1f),
    val measurementChosen: Measurement = Measurement("gram", 1f),
    val specificFood: SpecificFood =
    SpecificFood(
        id = "",
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
    val dataBaseFood:DataBaseFood = DataBaseFood(
        name = "",
        calories = 0f,
        protein = 1f,
        fats = 1f,
        carbs = 1f,
        fiber = 1f,
        measurements = emptyList()
    )
)