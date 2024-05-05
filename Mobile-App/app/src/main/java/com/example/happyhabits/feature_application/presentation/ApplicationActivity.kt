package com.example.happyhabits.feature_application.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.happyhabits.feature_application.feature_mood.presentation.mood_screen.MoodPageView
import com.example.happyhabits.feature_application.feature_toilet.presentation.toilet_screen.ToiletPageView
import com.example.happyhabits.feature_application.presentation.util.Screen
import com.example.happyhabits.feature_application.feature_workout.presentation.workout_pop_up_screen.WorkoutPopUpView
import com.example.happyhabits.feature_application.feature_workout.presentation.workout_screen.WorkoutPageView
import com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_screen.SleepPageView

import com.example.happyhabits.feature_application.home_page.HomePageView
import com.example.happyhabits.ui.theme.HappyHabitsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApplicationActivity: ComponentActivity() {
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
                    NavHost(navController = navController, startDestination = Screen.HomePageScreen.route){
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
                        composable(
                            route = Screen.ToiletPageScreen.route
                        ){
                            ToiletPageView(navController = navController)
                        }
                        composable(
                            route = Screen.WorkoutPopUpScreen.route
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
                        composable(
                            route = Screen.SleepPageScreen.route
                        ){
                            SleepPageView(navController = navController)
                        }
                        composable(
                            route = Screen.MoodPageScreen.route
                        ){
                            MoodPageView()//TODO : navController = navController)
                        }


                    }
                }
            }
        }
    }
}