package com.example.happyhabits.feature_application.feature_food.data.repository

import com.example.happyhabits.feature_application.feature_food.data.network.ApiHelper
import com.example.happyhabits.feature_application.feature_food.domain.model.DataBaseFood
import com.example.happyhabits.feature_application.feature_food.domain.model.Macros
import com.example.happyhabits.feature_application.feature_food.domain.model.SpecificFood
import com.example.happyhabits.feature_application.feature_food.domain.repository.IFoodRepository


class FoodRepositoryImpl (
    private val foodApi: ApiHelper
): IFoodRepository {
    override suspend fun getAutoCompleteSuggestions(searchInput: String): List<String> {
        try {
            return foodApi.getSearchSuggestion(searchInput)
        } catch (e: Exception) {
            throw e;
        }
    }

    override suspend fun getFoodInformation(foodNameInput: String): DataBaseFood? {
        try {
            return foodApi.getFoodInformation(foodNameInput)
        } catch (e: Exception) {
            throw e;
        }
    }
    override suspend fun removeFoodFromDataBase(foodId:String): String?
    {
        try {
            return foodApi.removeFoodFromDataBase(foodId)
        } catch (e: Exception) {
            throw e;
        }
    }

    override suspend fun saveLog(userId: String, date: String, foods: List<SpecificFood>): String?
    {
        try {
            return foodApi.saveLog(userId, date, foods)
        } catch (e: Exception) {
            throw e;
        }
    }

    override suspend fun retrieveFoodList(userId: String, date:String): List<SpecificFood> {
        try {
            return foodApi.retrieveFoodList(userId,date)
        } catch (e: Exception) {
            throw e;
        }
    }

    override suspend fun getMacros(userId: String, date:String): List<Float>{
        try {
            return foodApi.getMacros(userId, date)
        } catch (e: Exception) {
            throw e;
        }
    }
}