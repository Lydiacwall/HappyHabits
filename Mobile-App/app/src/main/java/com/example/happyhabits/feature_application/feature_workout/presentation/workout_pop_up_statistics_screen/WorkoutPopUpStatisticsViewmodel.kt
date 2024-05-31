package com.example.happyhabits.feature_application.feature_workout.presentation.workout_pop_up_statistics_screen


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.core.data.model.Manager
import com.example.happyhabits.feature_application.feature_workout.domain.use_case.WorkoutUseCases
import com.example.happyhabits.feature_application.feature_workout.presentation.workout_pop_up_statistics_screen.WorkoutPopUpStatisticsEvent
import com.example.happyhabits.feature_application.feature_workout.presentation.workout_pop_up_statistics_screen.WorkoutPopUpStatisticsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class WorkoutPopUpStatisticsViewmodel @Inject constructor(
    private val workoutUseCases: WorkoutUseCases,
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
                viewModelScope.launch {
                    if (_state.value.type == "Running" || _state.value.type == "Biking")
                    {
                        val fastWorkoutStats = Manager.currentUser?.id?.let {
                            workoutUseCases.getFastWorkoutStatistics.invoke(
                                it,
                                month = _state.value.monthSelected,
                                year = _state.value.yearSelected,
                                type = _state.value.type.toString()
                            )
                        }
                        _state.value = _state.value.copy(averageDurationPerWorkout = formatDoubleToTime(fastWorkoutStats?.avgDuration), averageElevationPerWorkout = fastWorkoutStats?.avgElevationPerWorkout, averageKilometersPerWorkout = fastWorkoutStats?.avgKmsPerWorkout, totalNumOfWorkouts = fastWorkoutStats?.totalWorkouts, totalNumOfKilometers = fastWorkoutStats?.totalKms)
                    }
                    else if (_state.value.type == "Yoga"||_state.value.type=="Swimming")
                    {
                        val exercisesWorkoutStats = Manager.currentUser?.id?.let {
                            workoutUseCases.getExercisesWorkoutStatistics.invoke(
                                it,
                                month = _state.value.monthSelected,
                                year = _state.value.yearSelected,
                                type = _state.value.type.toString()
                            )
                        }
                        _state.value = _state.value.copy(averageDurationPerWorkout = formatDoubleToTime(exercisesWorkoutStats?.avgDuration), totalNumOfWorkouts = exercisesWorkoutStats?.totalWorkouts, averageNumOfExercisesPerWorkout = exercisesWorkoutStats?.avgNumberOfExercisesPerWorkout, topFiveExercise = (exercisesWorkoutStats?.monthsTopFiveExercises)?: emptyList())
                    }
                    else if(_state.value.type =="Weights"){

                        val weightWorkoutStats = Manager.currentUser?.id?.let {
                            workoutUseCases.getExercisesWorkoutStatistics.invoke(
                                it,
                                month = _state.value.monthSelected,
                                year = _state.value.yearSelected,
                                type = _state.value.type.toString()
                            )
                        }
                        _state.value = _state.value.copy(averageDurationPerWorkout = formatDoubleToTime(weightWorkoutStats?.avgDuration), topFiveExercise = (weightWorkoutStats?.monthsTopFiveExercises)?: emptyList(), totalNumOfWorkouts = weightWorkoutStats?.totalWorkouts, averageNumOfExercisesPerWorkout = weightWorkoutStats?.avgNumberOfExercisesPerWorkout, averageKgsPerWorkout = weightWorkoutStats?.avgKgsPerWorkout)
                    }
                }
            }
        }
    }
}

fun formatDoubleToTime(time: Double?): String {
    val timeString = String.format("%.2f", time)
    val parts = timeString.split(".")
    val hours = parts[0].padStart(2, '0')  // Ensure two digits for hours
    val minutes = parts[1]
    return "$hours : $minutes"
}