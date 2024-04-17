package com.example.happyhabits.ViewModel

sealed class Screen (val route: String){
    object LogoView : Screen( "logo_view")
    object SignInView : Screen("sign_in_view")
}