package com.example.happyhabits.feature_application.feature_workout.presentation.workout_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.happyhabits.feature_application.feature_workout.presentation.util.Screen
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
                    event.navController.navigate(Screen.WorkoutPopUpScreen.route + "?type=running")
                }
            "weights"->
            {
                event.navController.navigate(Screen.WorkoutPopUpScreen.route + "?type=weights")
            }

            "biking"->
            {
                event.navController.navigate(Screen.WorkoutPopUpScreen.route + "?type=biking")
            }
            "yoga"->
            {
                event.navController.navigate(Screen.WorkoutPopUpScreen.route + "?type=yoga")
            }
            "swimming"->
            {
                event.navController.navigate(Screen.WorkoutPopUpScreen.route + "?type=swimming")
            }

        }
    }
}
