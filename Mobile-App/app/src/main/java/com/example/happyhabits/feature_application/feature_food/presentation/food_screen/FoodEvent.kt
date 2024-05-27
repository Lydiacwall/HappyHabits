package com.example.happyhabits.feature_application.feature_food.presentation.food_screen;


sealed class FoodEvent {
    data class SearchInputChanges(val newSearch: String):  FoodEvent()
    data class MealInputSet(val newMeal: String): FoodEvent()
    data class FoodRemoval(val nameOfFood: String, val mealOfFood:String): FoodEvent()
    data class FoodRemovalFromDatabase(val idOfFood: String): FoodEvent()
    data class FoodForInfo(val idOfFood: String, val nameOfFood: String): FoodEvent()
    data class SaveFoodLog(val noImportantString: String): FoodEvent()
    data class GetTodaysStatistics(val noImportantString: String): FoodEvent()

}