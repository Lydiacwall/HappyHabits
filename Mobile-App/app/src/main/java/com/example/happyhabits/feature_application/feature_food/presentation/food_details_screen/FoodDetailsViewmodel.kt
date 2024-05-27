package com.example.happyhabits.feature_application.feature_food.presentation.food_details_screen

import android.provider.ContactsContract.Data
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.feature_application.feature_food.domain.model.DataBaseFood
import com.example.happyhabits.feature_application.feature_food.domain.model.Macros
import com.example.happyhabits.feature_application.feature_food.domain.model.SpecificFood
import com.example.happyhabits.feature_application.feature_food.domain.use_case.FoodUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailsViewmodel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val foodUseCases: FoodUseCases
): ViewModel()
{
    private val _state = mutableStateOf(FoodDetailsState())
    val state: State<FoodDetailsState> = _state;
    init {
        val result = savedStateHandle.get<String>("searchedFood");
        val meal = savedStateHandle.get<String>("meal") ?: "";
        _state.value = _state.value.copy(searchInput = result, meal = meal)
        viewModelScope.launch {
            val newFood = foodUseCases.getFoodInformation(_state.value.searchInput?:"")?: DataBaseFood("", null, null, null, null, null, emptyList())
            if(newFood.getName()!=null){_state.value = _state.value.copy(dataBaseFood = newFood)}
        }

    }
    fun onEvent(event: FoodDetailsEvent) {
        when (event) {
            is FoodDetailsEvent.QuantityChanged-> {
                _state.value = _state.value.copy(quantityChosen = event.newQuantity)
            }
            is FoodDetailsEvent.MeasurementChosen-> {
                _state.value = _state.value.copy(measurementChosen = event.newMeasurement)
            }
            is FoodDetailsEvent.FoodChosen->{
                var newSpecificFood = SpecificFood(_state.value.specificFood)
                newSpecificFood = newSpecificFood.makeSpecificFood(_state.value.dataBaseFood,(_state.value.quantityChosen?:0f), _state.value.measurementChosen, _state.value.meal )
                val sumOfGrams = newSpecificFood.getProtein()+newSpecificFood.getCarbs()+newSpecificFood.getFats()+newSpecificFood.getFiber()
                val newMacros = Macros(protein=newSpecificFood.getProtein(), carbs=newSpecificFood.getCarbs(), fats = newSpecificFood.getFats(), fiber=newSpecificFood.getFiber(), totalGrams = sumOfGrams)
                _state.value = _state.value.copy(specificFood = SpecificFood(newSpecificFood), foodDefined = true, specificFoodMarcos = newMacros)
            }
        }
    }
}
