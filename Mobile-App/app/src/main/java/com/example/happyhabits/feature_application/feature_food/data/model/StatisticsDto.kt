package com.example.happyhabits.feature_application.feature_food.data.model

import kotlinx.serialization.Serializable

@Serializable
class StatisticsDto(
    var protein: Float,
    var fats: Float,
    var carbs: Float,
    var fiber: Float
){}