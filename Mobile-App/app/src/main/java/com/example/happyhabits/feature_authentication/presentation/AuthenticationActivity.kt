package com.example.happyhabits.feature_authentication.presentation

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
import com.example.happyhabits.feature_authentication.presentation.choose_role.ChooseRoleView
import com.example.happyhabits.feature_authentication.presentation.login.SignInView
import com.example.happyhabits.feature_authentication.presentation.sign_up_user.SignUpUserView
import com.example.happyhabits.feature_authentication.presentation.splash_screen.SplashScreen
import com.example.happyhabits.feature_authentication.presentation.get_started.GetStartedView
import com.example.happyhabits.feature_application.presentation.HomePageView
import com.example.happyhabits.feature_workout.presentation.workout_screen.WorkoutPageView
import com.example.happyhabits.feature_authentication.presentation.util.Screen
import com.example.happyhabits.ui.theme.HappyHabitsTheme
import dagger.hilt.android.AndroidEntryPoint
import com.example.happyhabits.feature_toilet.presentation.toilet_screen.ToiletPageView

@AndroidEntryPoint
class AuthenticationActivity: ComponentActivity() {
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
                    NavHost(navController = navController, startDestination = Screen.SplashScreen.route){
                        composable(
                            route = Screen.SplashScreen.route
                        ){
                            SplashScreen(navController = navController)
                        }
                        composable(
                            route = Screen.GetStartedScreen.route
                        ) {
                            GetStartedView(navController = navController)
                        }
                        composable(
                            route = Screen.LoginScreen.route
                        ) {
                            SignInView(navController = navController)
                        }
                        composable(
                            route = Screen.AddUserScreen.route +
                                    "?type={type}",
                            arguments = listOf(
                                navArgument(
                                    name = "type"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = 0
                                }
                            )
                        ) {
                            SignUpUserView(navController = navController)
                        }
                        composable(
                            route = Screen.ChooseRoleScreen.route
                        ) {
                            ChooseRoleView(navController = navController)
                        }
                        composable(
                            route = Screen.HomePageScreen.route
                        ){
                            HomePageView(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
