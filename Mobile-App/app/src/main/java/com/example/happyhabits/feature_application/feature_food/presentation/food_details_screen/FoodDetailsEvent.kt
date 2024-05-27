package com.example.happyhabits.feature_application.feature_food.presentation.food_details_screen;

import androidx.navigation.NavController
import com.example.happyhabits.feature_application.feature_food.domain.model.Measurement

sealed class FoodDetailsEvent {
    data class QuantityChanged(val newQuantity: Float):  FoodDetailsEvent()
    data class MeasurementChosen(val newMeasurement: Measurement):  FoodDetailsEvent()
    data class FoodChosen(val noImportantString: String): FoodDetailsEvent()

}