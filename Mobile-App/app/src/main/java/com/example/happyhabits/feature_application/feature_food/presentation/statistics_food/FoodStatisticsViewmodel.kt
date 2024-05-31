package com.example.happyhabits.feature_application.feature_food.presentation.statistics_food

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.core.data.model.Manager
import com.example.happyhabits.feature_application.feature_food.domain.model.Macros
import com.example.happyhabits.feature_application.feature_food.domain.model.SpecificFood
import com.example.happyhabits.feature_application.feature_food.domain.use_case.FoodUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
                    _state.value = _state.value.copy(dateSelected = event.dateInput)
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
                    var pickedFood: SpecificFood? =
                        _state.value.foodList.find { it.getName() == event.nameOfFood }
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
                }
            }
            is FoodStatisticsEvent.GetTodaysStatistics->{
                viewModelScope.launch {
                    val currentDate = _state.value.dateSelected
                    val lisOfStatistics = Manager.currentUser?.id?.let { foodStatisticsUseCases.getTodaysStatistics(it,currentDate) } ?: listOf(1f,1f,1f,1f)
                    var totalCalories = 0f
                    for (food in state.value.foodList) {
                        totalCalories += food.getCalories()
                    }
                    _state.value = _state.value.copy(totalCalories = totalCalories, totalProtein = lisOfStatistics[0], totalCarbs = lisOfStatistics[1], totalFats = lisOfStatistics[2], totalFiber = lisOfStatistics[3])
                }
            }
            is FoodStatisticsEvent.RetrieveFoods->{
                if(event.dateString=="MM/dd/yy"){
                    val currentDate = Date()
                    val dateFormat = SimpleDateFormat("MM/dd/yy", Locale.getDefault())
                    val formattedDate = dateFormat.format(currentDate)
                    _state.value = _state.value.copy(dateSelected = formattedDate)
                }
                viewModelScope.launch {
                    val retrievedFoodsList =
                        Manager.currentUser?.id?.let { foodStatisticsUseCases.retrieveFoodList(it, _state.value.dateSelected) }
                            ?: emptyList()
                    _state.value = _state.value.copy(foodList = retrievedFoodsList)
                }
            }
        }
    }
}