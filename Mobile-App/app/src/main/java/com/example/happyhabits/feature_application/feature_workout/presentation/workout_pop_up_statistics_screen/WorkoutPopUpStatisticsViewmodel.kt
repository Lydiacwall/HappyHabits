package com.example.happyhabits.feature_application.feature_workout.presentation.workout_pop_up_statistics_screen


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.core.data.model.Manager
import com.example.happyhabits.core.domain.use_case.CoreUseCases
import com.example.happyhabits.feature_application.feature_chat.domain.use_case.FriendChatUseCases
import com.example.happyhabits.feature_application.feature_workout.domain.use_case.WorkoutUseCases
import com.example.happyhabits.feature_application.feature_workout.presentation.workout_pop_up_statistics_screen.WorkoutPopUpStatisticsEvent
import com.example.happyhabits.feature_application.feature_workout.presentation.workout_pop_up_statistics_screen.WorkoutPopUpStatisticsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class WorkoutPopUpStatisticsViewmodel @Inject constructor(
    private val friendChatUseCases: FriendChatUseCases,
    private val coreUseCases: CoreUseCases,
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
        val userId: String = Manager.currentUser?.id.toString()
        viewModelScope.launch {
            val friends = friendChatUseCases.getFriendList(userId)
            _state.value = _state.value.copy(clientsList = friends)
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
                            workoutUseCases.getWeightsWorkoutStatistics.invoke(
                                it,
                                month = _state.value.monthSelected,
                                year = _state.value.yearSelected
                            )
                        }
                        _state.value = _state.value.copy(averageDurationPerWorkout = formatDoubleToTime(weightWorkoutStats?.avgDuration), topFiveExercise = (weightWorkoutStats?.monthsTopFiveExercises)?: emptyList(), totalNumOfWorkouts = weightWorkoutStats?.totalWorkouts, averageNumOfExercisesPerWorkout = weightWorkoutStats?.avgNumberOfExercisesPerWorkout, averageKgsPerWorkout = weightWorkoutStats?.avgKgsPerWorkout)
                    }
                }
            }
            is WorkoutPopUpStatisticsEvent.SendStatistics-> {
                var typeOfWorkout = ""
                var workoutStatisticsDictionary: Map<String, Any> = emptyMap()
                if (_state.value.type == "Running" || _state.value.type == "Biking") {
                    workoutStatisticsDictionary =  mapOf(
                        "averageDuration" to ((timeStringToDouble(_state.value.averageDurationPerWorkout)) ?: 0.0),
                        "totalWorkouts" to ((_state.value.totalNumOfWorkouts) ?: 0),
                        "averageQuantity" to ((_state.value.averageKilometersPerWorkout) ?: 0.0),
                        "averageElevation" to ((_state.value.averageElevationPerWorkout) ?: 0),
                        "totalQuantity" to ((_state.value.totalNumOfKilometers) ?: 0.0)
                    )
                    typeOfWorkout = "FastActivities"
                } else if (_state.value.type == "Yoga" || _state.value.type == "Swimming") {
                    workoutStatisticsDictionary =  mapOf(
                        "averageDuration" to ((timeStringToDouble(_state.value.averageDurationPerWorkout)) ?: 0.0),
                        "totalWorkouts" to ((_state.value.totalNumOfWorkouts) ?: 0),
                        "averageExercisePerWorkout" to ((_state.value.averageNumOfExercisesPerWorkout) ?: 0),
                        "topExercises" to ((_state.value.topFiveExercise) ?: emptyList())
                    )
                    typeOfWorkout = "ExercisesWorkout"
                } else if (_state.value.type == "Weights") {
                    workoutStatisticsDictionary =  mapOf(
                        "averageDuration" to ((timeStringToDouble(_state.value.averageDurationPerWorkout)) ?: 0.0),
                        "totalWorkouts" to ((_state.value.totalNumOfWorkouts) ?: 0),
                        "averageExercisePerWorkout" to ((_state.value.averageNumOfExercisesPerWorkout) ?: 0),
                        "topExercises" to ((_state.value.topFiveExercise) ?: emptyList()),
                        "averageKgsPerWorkout" to ((_state.value.averageKgsPerWorkout) ?: 0.0)
                    )
                    typeOfWorkout = "Weights"
                }
                viewModelScope.launch {
                    val response = coreUseCases.sendStatistics(
                        senderId = Manager.currentUser?.id.toString(),
                        groupId = _state.value.clientsList[event.indexOfFriend].groupId,
                        type = typeOfWorkout,
                        statistics = workoutStatisticsDictionary,
                        friendUsername = _state.value.clientsList[event.indexOfFriend].friendUsername
                    )
                }
            }
        }
    }
    fun formatDoubleToTime(time: Double?): String {
        if (time == null) {
            return "hh : mm"
        }

        val hours = time.toInt() / 60
        val remainingMinutes = time % 60
        val minutes = if (remainingMinutes % 1 >= 0.5) {
            remainingMinutes.toInt() + 1
        } else {
            remainingMinutes.toInt()
        }
        return "$hours : $minutes"
    }
    fun timeStringToDouble(timeString: String?): Double? {
        if (timeString == null || !timeString.matches(Regex("\\d{1,2} : \\d{1,2}"))) {
            return null
        }

        val parts = timeString.split(" : ").map { it.toIntOrNull() }
        if (parts.size != 2 || parts[0] == null || parts[1] == null) {
            return null
        }

        val hours = parts[0]!!
        val minutes = parts[1]!!

        return (hours * 60 + minutes).toDouble()
    }
}

