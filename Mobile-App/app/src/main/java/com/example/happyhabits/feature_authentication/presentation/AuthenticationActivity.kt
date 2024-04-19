package com.example.happyhabits.feature_authentication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.happyhabits.feature_authentication.presentation.login.SignInView
import com.example.happyhabits.feature_authentication.presentation.util.Screen
import com.example.happyhabits.ui.theme.HappyHabitsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity: ComponentActivity() {

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HappyHabitsTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.LoginScreen.route){
                        composable(
                            route = Screen.LoginScreen.route
                        ) {
                            SignInView(navController = navController)
                        }
                    }
                }
            }
        }
    }
}