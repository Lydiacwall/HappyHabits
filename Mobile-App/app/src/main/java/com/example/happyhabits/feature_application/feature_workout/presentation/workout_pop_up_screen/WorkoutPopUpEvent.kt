package com.example.happyhabits.feature_application.feature_workout.presentation.workout_pop_up_screen

import androidx.navigation.NavController
import com.example.happyhabits.feature_application.feature_workout.domain.model.Exercise
import com.example.happyhabits.feature_application.feature_workout.presentation.workout_screen.WorkoutPageEvent

sealed class WorkoutPopUpEvent {
    data class ChangePage(val navController: NavController): WorkoutPopUpEvent()
    data class TimeHoursChanged(val hours: Int): WorkoutPopUpEvent()
    data class TimeMinutesChanged(val minutes: Int): WorkoutPopUpEvent()
    data class DurationHoursChanged(val hours: Int): WorkoutPopUpEvent()
    data class DurationMinutesChanged(val minutes: Int): WorkoutPopUpEvent()
    data class TimeChanged(val nonFunctioningString: String): WorkoutPopUpEvent()
    data class DurationChanged(val newDuration: String): WorkoutPopUpEvent()
    data class NotesChanged(val newNote: String): WorkoutPopUpEvent()
    data class QuantityChanged(val newQuantity: Float): WorkoutPopUpEvent()
    data class ElevationChanged(val newElevation: Float): WorkoutPopUpEvent()
    data class ExercisesChanged(val newExercise: List<Exercise>): WorkoutPopUpEvent()

    data class ExerciseNameChanged(val newExerciseName: String): WorkoutPopUpEvent()
    data class SimpleExerciseNameChanged(val newSimpleExerciseName: String): WorkoutPopUpEvent()
    data class ExerciseSetsChanged(val newSets: Int): WorkoutPopUpEvent()
    data class ExerciseRepsChanged(val newReps: Int): WorkoutPopUpEvent()
    data class ExerciseKgsChanged(val newKgs: Float): WorkoutPopUpEvent()
    data class AddCurrentExercise(val exerciseName: String): WorkoutPopUpEvent()
    data class AddCurrentSimpleExercise(val simpleExerciseName: String): WorkoutPopUpEvent()
    data class SaveWorkout(val nonNecessaryString: String, val navController: NavController): WorkoutPopUpEvent()
    data class RemoveExercise(val typeOfExercise: String, val index:Int): WorkoutPopUpEvent()

}