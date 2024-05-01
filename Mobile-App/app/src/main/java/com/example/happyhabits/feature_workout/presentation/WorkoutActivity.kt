package com.example.happyhabits.feature_workout.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.happyhabits.feature_application.presentation.HomePageView
import com.example.happyhabits.feature_workout.presentation.util.Screen
import com.example.happyhabits.feature_workout.presentation.workout_screen.WorkoutPageView
import com.example.happyhabits.feature_workout.presentation.workout_pop_up_screen.WorkoutPopUpView
import com.example.happyhabits.ui.theme.HappyHabitsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkoutActivity: ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyHabitsTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.WorkoutPageScreen.route){
                        composable(
                            route = Screen.HomePageScreen.route
                        ){
                            HomePageView(navController = navController)
                        }
                        composable(
                            route = Screen.WorkoutPageScreen.route
                        ){
                            WorkoutPageView(navController = navController)
                        }
                        composable(route= Screen.WorkoutPopUpScreen.route
                        ){
                            WorkoutPopUpView(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
