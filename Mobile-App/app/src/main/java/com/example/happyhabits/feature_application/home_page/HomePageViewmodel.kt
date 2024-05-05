package com.example.happyhabits.feature_application.home_page


import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.happyhabits.feature_application.presentation.util.Screen
import javax.inject.Inject

@HiltViewModel
class HomePageViewmodel @Inject constructor(
): ViewModel() {
    fun onEvent(event: HomePageEvent.ChangePage) {
        when(event.pageName){
            "homepage"->{
                event.navController.navigate(Screen.HomePageScreen.route)
            }
            "messages" -> {
            }
            "statistics"-> {
            }
            "profile"->
            {
                event.navController.navigate(Screen.ProfilePageScreen.route)
            }

        }
    }
}
