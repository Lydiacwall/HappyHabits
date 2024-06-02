package com.example.happyhabits.core.data.network

import com.example.happyhabits.core.data.model.StatisticsForm
import com.example.happyhabits.feature_application.feature_food.data.model.SpecificFoodForm
import com.example.happyhabits.feature_application.feature_food.domain.model.SpecificFood

class ApiHelper(private val apiService: ApiService) {

    suspend fun sendStatistics(senderId: String, groupId: String, type: String, statistics: Map<String, Any>, friendUsername: String): String {
        try {
            var updatedStatistics: Map<String, Any> = emptyMap()
            if (type=="Food"){
                val specificFoodList: List<SpecificFood> = statistics["Foods"] as List<SpecificFood>
                val specificFoodFormList : MutableList<SpecificFoodForm> = mutableListOf()
                for (food in specificFoodList) {
                    val newSpecificFoodForm = SpecificFoodForm(
                        id = food.getFoodId(),
                        name = food.getName(),
                        meal = food.getMeal(),
                        calories = food.getCalories(),
                        protein = food.getProtein(),
                        fats = food.getFats(),
                        fiber = food.getFiber(),
                        carbs = food.getCarbs(),
                        quantity = food.getQuantity(),
                        measurement = food.getMeasurement()
                    )
                    specificFoodFormList.add(newSpecificFoodForm)
                }
                val foodStatisticsDictionary: Map<String, Any> = mapOf(
                    "calories" to (statistics["calories"] ?:"Not Found"),
                    "proteinPercentage" to (statistics["proteinPercentage"] ?:"Not Found"),
                    "fatsPercentage" to (statistics["fatsPercentage"] ?:"Not Found"),
                    "carbsPercentage" to (statistics["carbsPercentage"] ?:"Not Found"),
                    "FiberPercentage" to (statistics["fiberPercentage"] ?:"Not Found"),
                    "Foods" to (specificFoodFormList)
                )
                updatedStatistics = foodStatisticsDictionary
            }
            else {
                updatedStatistics = statistics
            }
            val response = apiService.sendStatistics(StatisticsForm(senderId = senderId, groupId = groupId, type = type, statistics = updatedStatistics, friendUsername = friendUsername))
            return if (response.isSuccessful && response.body() != null)
            {
                return response.body()!!
            } else
            {
                "Unsuccessful Statistics Delivery"
            }
        } catch (e: Exception) {
            throw e;
        }
    }
}