package com.example.happyhabits.feature_application.feature_food.domain.model

class Macros(
    protein: Float,
    carbs: Float,
    fiber: Float,
    fats: Float,
    totalGrams: Float
) {
    val proteinPercentage: Float
    val carbsPercentage: Float
    val fiberPercentage: Float
    val fatsPercentage: Float

    init {
        if (totalGrams != 0f) {
            proteinPercentage = (protein / totalGrams) * 100
            carbsPercentage = (carbs / totalGrams) * 100
            fiberPercentage = (fiber / totalGrams) * 100
            fatsPercentage = (fats / totalGrams) * 100
        }
        else {
            proteinPercentage = 0f
            carbsPercentage = 0f
            fiberPercentage = 0f
            fatsPercentage = 0f
        }
    }

    override fun toString(): String {
        return "Macros(proteinPercentage=$proteinPercentage, carbsPercentage=$carbsPercentage, fiberPercentage=$fiberPercentage, fatsPercentage=$fatsPercentage)"
    }
}