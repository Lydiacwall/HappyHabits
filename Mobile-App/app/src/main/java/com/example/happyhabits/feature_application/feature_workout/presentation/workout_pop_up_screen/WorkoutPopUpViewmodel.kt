package com.example.happyhabits.feature_application.feature_workout.presentation.workout_pop_up_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.feature_application.feature_workout.domain.model.Exercise
import com.example.happyhabits.feature_authentication.domain.model.Type
import com.example.happyhabits.feature_authentication.presentation.sign_up_user.SignUpUserEvent
import com.example.happyhabits.feature_authentication.presentation.sign_up_user.SignUpUserState
import com.example.happyhabits.feature_application.feature_workout.domain.model.ExercisesWorkout
import com.example.happyhabits.feature_application.feature_workout.domain.model.FastActivity
import com.example.happyhabits.feature_application.feature_workout.domain.model.Weights
import com.example.happyhabits.feature_application.feature_workout.domain.model.Workout
import com.example.happyhabits.feature_application.feature_workout.presentation.util.Screen
import com.example.happyhabits.feature_application.feature_workout.presentation.workout_screen.WorkoutPageEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
    fun onEvent(event: WorkoutPopUpEvent) {

        when (event) {
            is WorkoutPopUpEvent.ChangePage -> {
                event.navController.navigate(Screen.WorkoutPageScreen.route)
            }

            is WorkoutPopUpEvent.TimeHoursChanged -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(hoursTime = event.hours)
                }
            }

            is WorkoutPopUpEvent.TimeMinutesChanged -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(minutesTime = event.minutes)
                }
            }

            is WorkoutPopUpEvent.DurationHoursChanged -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(hoursDuration = event.hours)
                }
            }

            is WorkoutPopUpEvent.DurationMinutesChanged -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(minutesDuration = event.minutes)
                }
            }

            is WorkoutPopUpEvent.TimeChanged -> {
                viewModelScope.launch {
                    var newDurationTime = "hh : mm"
                    var newTimeTime = "hh : mm"
                    if(!(_state.value.hoursTime==0&&_state.value.minutesTime==0)){
                        newTimeTime = "${_state.value.hoursTime} : ${_state.value.minutesTime}"
                        _state.value = _state.value.copy(time= newTimeTime)
                    }
                    if(!(_state.value.hoursDuration==0&&_state.value.minutesDuration==0)){
                        newDurationTime = "${_state.value.hoursDuration} : ${_state.value.minutesDuration}"
                        _state.value = _state.value.copy(duration = newDurationTime)
                    }
                }
            }

            is WorkoutPopUpEvent.DurationChanged -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(duration = event.newDuration)
                }
            }

            is WorkoutPopUpEvent.QuantityChanged -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(quantity = event.newQuantity)
                }
            }

            is WorkoutPopUpEvent.NotesChanged -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(notes = event.newNote)
                    println("New note: ${_state.value.notes}")
                }
            }
            is WorkoutPopUpEvent.ElevationChanged -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(elevation = event.newElevation)
                }
            }
            is WorkoutPopUpEvent.ExercisesChanged -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(exercises = event.newExercise)
                }
            }

            is WorkoutPopUpEvent.ExerciseNameChanged -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(currentExercise = Exercise(event.newExerciseName, _state.value.currentExercise.reps, _state.value.currentExercise.sets, _state.value.currentExercise.kgs))
                }
            }

            is WorkoutPopUpEvent.SimpleExerciseNameChanged -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(currentSimpleExercise = event.newSimpleExerciseName)
                }
            }

            is WorkoutPopUpEvent.ExerciseRepsChanged -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(currentExercise = Exercise(_state.value.currentExercise.name, event.newReps, _state.value.currentExercise.sets, _state.value.currentExercise.kgs))
                }
            }

            is WorkoutPopUpEvent.ExerciseSetsChanged -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(currentExercise = Exercise(_state.value.currentExercise.name, _state.value.currentExercise.reps, event.newSets, _state.value.currentExercise.kgs))
                }
            }

            is WorkoutPopUpEvent.ExerciseKgsChanged -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(currentExercise = Exercise(_state.value.currentExercise.name, _state.value.currentExercise.reps, _state.value.currentExercise.sets, kgs = event.newKgs))
                }
            }

            is WorkoutPopUpEvent.AddCurrentExercise -> {
                viewModelScope.launch {
                    if (!_state.value.exercises.isEmpty())
                    {
                        //adding the current exercise to the list since the user pressed add
                        val newExercises = _state.value.exercises.map { Exercise(it) }.toMutableList()
                        val newExercise = Exercise(_state.value.currentExercise)
                        newExercises.add(newExercise)
                        _state.value = _state.value.copy(exercises = newExercises)
                        //updating the current exercise to an empty exercise
                        _state.value = _state.value.copy(currentExercise = Exercise("", 0, 0, 0f), quantity = 0f)
                    }
                    else
                    {
                        //initializing exercise list with the current exercise since the user pressed add
                        val initialExercise = Exercise(_state.value.currentExercise)
                        val newExercises = listOf(initialExercise)
                        _state.value = _state.value.copy(exercises = newExercises)
                        //updating the current exercise to an empty exercise
                        _state.value = _state.value.copy(currentExercise = Exercise("", 0, 0,0f), quantity = 0f)
                    }
                }
            }
            is WorkoutPopUpEvent.AddCurrentSimpleExercise -> {
                viewModelScope.launch {
                    if (!_state.value.simpleExercises.isEmpty())
                    {
                        val newSimpleExercises = _state.value.simpleExercises.toMutableList()
                        val currentSimpleExercise = _state.value.currentSimpleExercise
                        newSimpleExercises.add(currentSimpleExercise)
                        _state.value = _state.value.copy(simpleExercises = newSimpleExercises)
                        _state.value = _state.value.copy(currentSimpleExercise = "")
                    }
                    else
                    {
                        //initializing exercise list with the current exercise since the user pressed add
                        val newSimpleExercise = _state.value.currentSimpleExercise.let { String(it.toCharArray()) }
                        val newSimpleExercises = listOf(newSimpleExercise)
                        _state.value = _state.value.copy(simpleExercises = newSimpleExercises)
                        //updating the current exercise to an empty exercise
                        _state.value = _state.value.copy(currentSimpleExercise = "")
                    }
                }
            }

            is WorkoutPopUpEvent.SaveWorkout -> {
                ////BACKEND DO SOMETHING WITH EACH WORKOUT AFTER EVERY SAVE
                if(_state.value.type=="Running" || _state.value.type=="Biking") {
                    if(!((_state.value.time=="hh : mm")&&(_state.value.duration=="hh : mm")&&(_state.value.notes=="")&&(_state.value.quantity==0f)&&(_state.value.elevation==0f))){
                        val newWorkout = FastActivity(_state.value.type, _state.value.time,  _state.value.duration,  _state.value.notes, _state.value.quantity,  _state.value.elevation)
                        println("New Workout: ${newWorkout.workoutToString()}")
                        _state.value.copy(currentFastActivityWorkout = newWorkout)
                    }
                } else if(_state.value.type=="Swimming" || _state.value.type=="Yoga") {
                    if(!((_state.value.time=="hh : mm")&&(_state.value.duration=="hh : mm")&&(_state.value.notes=="")&&((_state.value.simpleExercises.isEmpty())))){
                        val newWorkout = ExercisesWorkout(_state.value.type, _state.value.time,  _state.value.duration,  _state.value.notes, simpleExercises = _state.value.simpleExercises)
                        println("New Workout: ${newWorkout.workoutToString()}")
                        _state.value.copy(currentFastActivityWorkout = newWorkout)
                    }
                } else if(_state.value.type=="Weights") {
                    if(!((_state.value.time=="hh : mm")&&(_state.value.duration=="hh : mm")&&(_state.value.notes=="")&&(_state.value.quantity==0f)&&(_state.value.elevation==0f)&&((_state.value.exercises.isEmpty())))){
                        val newWorkout = Weights(_state.value.time,  _state.value.duration,  _state.value.notes,  _state.value.exercises)
                        println("New Workout: ${newWorkout.workoutToString()}")
                        _state.value.copy(currentFastActivityWorkout = newWorkout)
                    }
                }
                event.navController.navigate(Screen.WorkoutPageScreen.route)
            }

        }

    }
}

