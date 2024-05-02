package com.example.happyhabits.feature_workout.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.happyhabits.feature_application.presentation.ApplicationActivity
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
        // animations for loading and unloading screen
//        window.enterTransition = Slide(Gravity.START)
//        window.exitTransition = Slide(Gravity.END)
        super.onCreate(savedInstanceState)
        setContent {
            HappyHabitsTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.WorkoutPageScreen.route){
                        composable(
                            route = "home_page_screen"
                        ){
                            val intent = Intent(this@WorkoutActivity, ApplicationActivity::class.java)
                            startActivity(intent)
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
                        composable(
                            route = Screen.WorkoutPopUpScreen.route +
                                    "?type={type}",
                            arguments = listOf(
                                navArgument(
                                    name = "type"
                                ) {
                                    type = NavType.StringType
                                    defaultValue = ""
                                }
                            )
                        ) {
                            WorkoutPopUpView(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
