package com.example.happyhabits.feature_application.feature_food.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SpecificFoodForm (
    val id: String,
    var name: String,
    var meal: String,
    var calories: Float,
    var protein: Float,
    var fats: Float,
    var carbs: Float,
    var fiber: Float,
    var quantity: Float,
    var measurement: String,
){

}