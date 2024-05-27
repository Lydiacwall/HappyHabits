package com.example.happyhabits.feature_application.feature_statistics_food.presentation

import com.example.happyhabits.feature_application.feature_food.presentation.food_screen.FoodEvent

sealed class FoodStatisticsEvent {
    data class dateSelected(val dateInput: String): FoodStatisticsEvent()
    data class FoodForInfo(val idOfFood: String, val nameOfFood: String): FoodStatisticsEvent()
    data class GetTodaysStatistics(val noImportantString: String): FoodStatisticsEvent()
    data class RetrieveFoods(val dateString: String): FoodStatisticsEvent()
}