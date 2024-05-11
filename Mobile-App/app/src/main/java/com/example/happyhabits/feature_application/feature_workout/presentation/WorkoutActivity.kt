package com.example.happyhabits.feature_application.feature_workout.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi

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
 //       setContent {
//            HappyHabitsTheme {
//                Surface(
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    val navController = rememberNavController()
//                    NavHost(navController = navController, startDestination = Screen.WorkoutPageScreen.route){
//                        composable(
//                            route = "home_page_screen"
//                        ){
//                            val intent = Intent(this@WorkoutActivity, ApplicationActivity::class.java)
//                            startActivity(intent)
//                        }
//                        composable(
//                            route = Screen.WorkoutPageScreen.route
//                        ){
//                            WorkoutPageView(navController = navController)
//                        }
//                        composable(route= Screen.WorkoutPopUpScreen.route
//                        ){
//                            WorkoutPopUpView(navController = navController)
//                        }
//                        composable(
//                            route = Screen.WorkoutPopUpScreen.route +
//                                    "?type={type}",
//                            arguments = listOf(
//                                navArgument(
//                                    name = "type"
//                                ) {
//                                    type = NavType.StringType
//                                    defaultValue = ""
//                                }
//                            )
//                        ) {
//                            WorkoutPopUpView(navController = navController)
//                        }
//                        composable(route= Screen.ToiletPageScreen.route
//                        ){
//                            ToiletPageView(navController =  navController)
//                        }
//                    }
                //}
 //           }
 //       }
    }
}
