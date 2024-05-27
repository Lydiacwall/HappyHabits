package com.example.happyhabits.feature_application.feature_food.domain.repository

import com.example.happyhabits.feature_application.feature_food.domain.model.DataBaseFood
import com.example.happyhabits.feature_application.feature_food.domain.model.Macros
import com.example.happyhabits.feature_application.feature_food.domain.model.SpecificFood

interface IFoodRepository {
    suspend fun getAutoCompleteSuggestions(searchInput:String): List<String>
    suspend fun getFoodInformation(foodNameInput:String): DataBaseFood?
    suspend fun removeFoodFromDataBase(foodId:String): String?
    suspend fun  saveLog(userId: String, date: String, foods: List<SpecificFood>): String?
    suspend fun retrieveFoodList(userId: String, date:String): List<SpecificFood>
    suspend fun getMacros(userId: String, date:String): List<Float>
}