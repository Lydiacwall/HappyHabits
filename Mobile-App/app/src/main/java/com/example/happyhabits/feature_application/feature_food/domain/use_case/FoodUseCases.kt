package com.example.happyhabits.feature_application.feature_food.domain.use_case

data class FoodUseCases (
    val getSearchResults:GetSearchResults,
    val getFoodInformation:GetFoodInformation,
    val removeFoodFromDataBase: RemoveFoodFromDataBase,
    val saveLog: SaveFoodLog,
    val retrieveFoodList: RetrieveFoodList,
    val getTodaysStatistics: GetTodaysStatistics
)