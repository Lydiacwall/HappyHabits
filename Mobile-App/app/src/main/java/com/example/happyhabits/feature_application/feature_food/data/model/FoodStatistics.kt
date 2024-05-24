package com.example.happyhabits.feature_application.feature_food.data.model

import kotlinx.serialization.Serializable

@Serializable
class FoodStatistics(
    var proteinPercentage: Float,
    var carbsPercentage: Float,
    var fiberPercentage: Float,
    var fatsPercentage: Float
) {
    override fun toString(): String {
        return "FoodStatistics(proteinPercentage=$proteinPercentage, carbsPercentage=$carbsPercentage, fiberPercentage=$fiberPercentage, fatsPercentage=$fatsPercentage)"
    }
}