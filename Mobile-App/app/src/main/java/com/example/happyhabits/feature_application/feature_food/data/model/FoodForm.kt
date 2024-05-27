package com.example.happyhabits.feature_application.feature_food.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FoodForm (
    val userId: String,
    val date: String,
    val specificFoodsForms: List<SpecificFoodForm>
){}