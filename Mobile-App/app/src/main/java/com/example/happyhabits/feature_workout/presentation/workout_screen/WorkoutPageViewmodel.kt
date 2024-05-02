package com.example.happyhabits.feature_workout.presentation.workout_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.navigation.NavController
import javax.inject.Inject

@HiltViewModel
class WorkoutPageViewmodel @Inject constructor(
): ViewModel() {
    fun onEvent(event: WorkoutPageEvent.ChangePage) {
        when(event.HabitOrBack){
            "back" ->
                {
                    event.navController.navigate("home_page_screen")
                }
            "running"->
                {
                    event.navController.navigate("workout")
                }
            "weights"->{}
            "biking"->{}
            "yoga"->{}
        }
    }
}
