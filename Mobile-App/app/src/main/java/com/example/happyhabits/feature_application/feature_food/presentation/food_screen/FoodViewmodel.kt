package com.example.happyhabits.feature_application.feature_food.presentation.food_screen

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
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class FoodViewmodel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val foodUseCases: FoodUseCases
): ViewModel()
{
    private val _state = mutableStateOf(FoodState())
    val state: State<FoodState> = _state;

    init{
        viewModelScope.launch {
            val currentDate = Date()
            val dateFormat = SimpleDateFormat("MM/dd/yy", Locale.getDefault())
            val formattedDate = dateFormat.format(currentDate)
            val retrievedFoodsList = Manager.currentUser?.id?.let { foodUseCases.retrieveFoodList(it, formattedDate ) } ?: emptyList()
            _state.value = _state.value.copy(foodList = retrievedFoodsList)
            val specificFoodJson = savedStateHandle.get<String>("specificFood") ?: ""
            var newSpecificFood = false
            val specificFood = if (specificFoodJson.isNotEmpty()) {
                newSpecificFood = true
                Json.decodeFromString<SpecificFood>(specificFoodJson)
            } else {
                SpecificFood(
                    foodId = "",
                    name = "",
                    meal = "",
                    calories = 0.0f,
                    protein = 0.0f,
                    fats = 0.0f,
                    carbs = 0.0f,
                    fiber = 0.0f,
                    quantity = 0.0f,
                    measurement = ""
                )
            }
            if (newSpecificFood) {
                val newSpecificFoodList: MutableList<SpecificFood> =
                    _state.value.foodList.map { existingFood ->
                        if (existingFood.getName() == specificFood.getName() && existingFood.getMeal() == specificFood.getMeal()) {
                            specificFood
                        } else {
                            existingFood
                        }
                    }.toMutableList()
                if (!newSpecificFoodList.contains(specificFood)) {
                    newSpecificFoodList.add(specificFood)
                }
                _state.value = _state.value.copy(foodList = newSpecificFoodList)
            }
        }
    }
    fun onEvent(event: FoodEvent) {

        when (event) {
            is FoodEvent.SearchInputChanges-> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(searchInput = event.newSearch)
                }
            }
            is FoodEvent.MealInputSet-> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(chosenMeal = event.newMeal)
                }
            }
            is FoodEvent.FoodRemoval -> {
                val newSpecificFoodList: MutableList<SpecificFood> = _state.value.foodList
                    .filter { (it.getName() != event.nameOfFood)&&(it.getMeal()!=event.mealOfFood) }
                    .map { SpecificFood(it) }
                    .toMutableList()
                _state.value = _state.value.copy(foodList = newSpecificFoodList)
            }
            is FoodEvent.FoodForInfo -> {
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
            is FoodEvent.FoodRemovalFromDatabase ->{
                viewModelScope.launch {
                    foodUseCases.removeFoodFromDataBase(event.idOfFood)
                }
            }
            is FoodEvent.SaveFoodLog->{
                viewModelScope.launch {
                    val userId = Manager.currentUser?.id ?: ""
                    val currentDate = Date()
                    val dateFormat = SimpleDateFormat("MM/dd/yy", Locale.getDefault())
                    val formattedDate = dateFormat.format(currentDate)
                    foodUseCases.saveLog(userId = userId, date = formattedDate, _state.value.foodList)
                }
            }
            is FoodEvent.GetTodaysStatistics->{
                viewModelScope.launch {
                    val currentDate = Date()
                    val dateFormat = SimpleDateFormat("MM/dd/yy", Locale.getDefault())
                    val formattedDate = dateFormat.format(currentDate)
                    val lisOfStatistics = Manager.currentUser?.id?.let { foodUseCases.getTodaysStatistics(it,formattedDate) } ?: listOf(1f,1f,1f,1f)
                    var totalCalories: Float = 0f
                    for (food in state.value.foodList) {
                        totalCalories += food.getCalories()
                    }
                    _state.value = _state.value.copy(totalCalories = totalCalories, totalProtein = lisOfStatistics[1], totalCarbs = lisOfStatistics[2], totalFats = lisOfStatistics[3], totalFiber = lisOfStatistics[4])
                }
            }
        }
    }
}