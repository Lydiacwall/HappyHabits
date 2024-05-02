package com.example.happyhabits.feature_application.feature_workout.presentation.workout_pop_up_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.happyhabits.feature_application.feature_workout.presentation.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkoutPopUpViewmodel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(WorkoutPopUpState())
    val state: State<WorkoutPopUpState> = _state;

    init {
        val type = savedStateHandle.get<String>("type");
        when(type){
            "running" -> {
                _state.value = _state.value.copy(type = "Running", unitMeasurement = "km")
            }
            "weights" -> {
                _state.value = _state.value.copy(type = "Weights", unitMeasurement = "kg")
            }
            "biking" -> {
                _state.value = _state.value.copy(type = "Biking", unitMeasurement = "km")
            }
            "yoga" -> {
                _state.value = _state.value.copy(type = "Yoga")
            }
            "swimming" -> {
                _state.value = _state.value.copy(type = "Swimming")
            }
        }
    }
    fun onEvent(event: WorkoutPopUpEvent.ChangePage) {
        event.navController.navigate(Screen.WorkoutPageScreen.route)
    }
}
