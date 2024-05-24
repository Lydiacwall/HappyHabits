package com.example.happyhabits.feature_application.feature_statistics_food.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.core.data.model.Manager
import com.example.happyhabits.feature_application.feature_food.domain.model.Macros
import com.example.happyhabits.feature_application.feature_food.domain.model.SpecificFood
import com.example.happyhabits.feature_application.feature_food.domain.use_case.FoodUseCases
import com.example.happyhabits.feature_application.feature_food.presentation.food_screen.FoodEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class FoodStatisticsViewmodel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val foodStatisticsUseCases: FoodUseCases
): ViewModel()
{
    private val _state = mutableStateOf(FoodStatisticsState())
    val state: State<FoodStatisticsState> = _state;

    fun onEvent(event: FoodStatisticsEvent) {

        when (event) {
            is FoodStatisticsEvent.dateSelected-> {
                viewModelScope.launch {
                }
            }
            is FoodStatisticsEvent.FoodForInfo -> {
                if (event.idOfFood != "") {
                    var pickedFood: SpecificFood? =
                        _state.value.foodList.find { it.getFoodId() == event.idOfFood }
                    if (pickedFood == null) {
                        pickedFood = SpecificFood(_state.value.foodForInfo)
                    }
                    val pickedFoodMacros = Macros(
                        protein = pickedFood.getProtein(),
                        carbs = pickedFood.getCarbs(),
                        fiber = pickedFood.getFiber(),
                        fats = pickedFood.getFats(),
                        totalGrams = pickedFood.getProtein() + pickedFood.getCarbs() + pickedFood.getFiber() + pickedFood.getFats()
                    )
                    _state.value = _state.value.copy(
                        foodForInfo = pickedFood,
                        foodForInfoMacros = pickedFoodMacros
                    )
                } else {
                    _state.value = _state.value.copy(
                        foodForInfo = SpecificFood("", "", "", 0f, 1f, 1f, 1f, 1f, 0f, "whole"),
                        foodForInfoMacros = Macros(1f, 1f, 1f, 1f, 4f)
                    )
                }
            }
            is FoodStatisticsEvent.GetTodaysStatistics->{
                viewModelScope.launch {
                }
            }
        }
    }
}