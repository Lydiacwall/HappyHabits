package com.example.happyhabits.ViewModel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainViewModel  : ViewModel(){





fun logoviewbutton( ){


}
fun changeScreen(){

}

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LogoView.route ){
        composable(route = Screen.LogoView.route){

        }
    }
}



}