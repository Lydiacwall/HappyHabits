package com.example.happyhabits.feature_application.feature_food.presentation.food_search_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.feature_application.feature_food.domain.model.SpecificFood
import com.example.happyhabits.feature_application.feature_food.domain.use_case.FoodUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class FoodSearchViewmodel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val foodUseCases: FoodUseCases
): ViewModel()
{
    private val _state = mutableStateOf(FoodSearchState())
    val state: State<FoodSearchState> = _state;
    init {
        viewModelScope.launch {
            val search = savedStateHandle.get<String>("searchedFood");
            val meal = savedStateHandle.get<String>("meal");
            val specificFoodJson = savedStateHandle.get<String>("specificFood") ?: ""
            val specificFood = if (specificFoodJson.isNotEmpty()) {
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
            var newListOfSearchResults: List<String> = listOf()
            newListOfSearchResults = foodUseCases.getSearchResults(search?:"")
            _state.value =
                _state.value.copy(searchInput = search, meal = meal, specificFood = specificFood, foodsAccordingToSearch = newListOfSearchResults)
        }
    }
    fun onEvent(event: FoodSearchEvent) {

        when (event) {
            is FoodSearchEvent.SearchInputChanged->{
                val newSearch = event.newSearchInput
                _state.value = _state.value.copy(searchInput = newSearch)
            }
        }
    }
}