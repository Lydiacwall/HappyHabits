package com.example.happyhabits.feature_application.feature_statistics_workout.presentation.workout_pop_up_statistics_screen


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.happyhabits.feature_application.feature_statistics_workout.presentation.workout_pop_up_statistics_screen.WorkoutPopUpStatisticsEvent
import com.example.happyhabits.feature_application.feature_statistics_workout.presentation.workout_pop_up_statistics_screen.WorkoutPopUpStatisticsState
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.happyhabits.R
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class WorkoutPopUpStatisticsViewmodel @Inject constructor(
//    private val workoutUseCases: WorkoutUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(WorkoutPopUpStatisticsState())
    val state: State<WorkoutPopUpStatisticsState> = _state;

    init {
        val type = savedStateHandle.get<String>("type")
        when(type){
            "running" -> {
                _state.value = _state.value.copy(type = "Running")
            }
            "weights" -> {
                _state.value = _state.value.copy(type = "Weights")
            }
            "biking" -> {
                _state.value = _state.value.copy(type = "Biking")
            }
            "yoga" -> {
                _state.value = _state.value.copy(type = "Yoga")
            }
            "swimming" -> {
                _state.value = _state.value.copy(type = "Swimming")
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: WorkoutPopUpStatisticsEvent) {

        when (event) {
            is WorkoutPopUpStatisticsEvent.UpdatedYear->{
                _state.value = _state.value.copy(yearSelected = event.newYear)
            }
            is WorkoutPopUpStatisticsEvent.UpdatedMonth->{
                _state.value = _state.value.copy(monthSelected = event.newMonth)
            }
            is WorkoutPopUpStatisticsEvent.DateSelected->{
                _state.value = _state.value.copy(dateSelected = true)
            }
        }
    }
}