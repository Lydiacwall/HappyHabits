package com.example.happyhabits.feature_application.feature_statistics_workout.presentation.workout_statistics_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.happyhabits.feature_application.presentation.util.Screen
import javax.inject.Inject

@HiltViewModel
class WorkoutStatisticsPageViewmodel @Inject constructor(
): ViewModel() {
    fun onEvent(event: WorkoutStatisticsPageEvent.ChangePage) {
        when(event.HabitOrBack){
            "back" ->
                {
                    event.navController.navigate(Screen.HomePageScreen.route)
                }
            "running"->
                {
                    event.navController.navigate(Screen.WorkoutStatisticsPopUpScreen.route + "?type=running")
                }
            "weights"->
            {
                event.navController.navigate(Screen.WorkoutStatisticsPopUpScreen.route + "?type=weights")
            }

            "biking"->
            {
                event.navController.navigate(Screen.WorkoutStatisticsPopUpScreen.route + "?type=biking")
            }
            "yoga"->
            {
                event.navController.navigate(Screen.WorkoutStatisticsPopUpScreen.route + "?type=yoga")
            }
            "swimming"->
            {
                event.navController.navigate(Screen.WorkoutStatisticsPopUpScreen.route + "?type=swimming")
            }
        }
    }
}
