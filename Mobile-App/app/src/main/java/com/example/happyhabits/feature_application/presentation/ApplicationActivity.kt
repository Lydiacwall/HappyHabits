package com.example.happyhabits.feature_application.presentation

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
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.happyhabits.feature_application.presentation.HomePageView
import com.example.happyhabits.feature_application.presentation.util.Screen
import com.example.happyhabits.feature_toilet.presentation.toilet_screen.ToiletPageView
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
                            route = "workout_page"
                        ){
                            val intent = Intent(this@ApplicationActivity, WorkoutActivity::class.java)
                            startActivity(intent)
                        }
//                        composable(
//                            route = Screen.ToiletPageScreen.route
//                        ){
//                            ToiletPageView(navController =  navController)
//                        }
                    }
                }
            }
        }
    }
}