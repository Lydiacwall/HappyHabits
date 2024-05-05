package com.example.happyhabits.feature_application.feature_profile.presentation.profile_page

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.feature_application.feature_profile.presentation.profile_page.ProfileEvent
import com.example.happyhabits.feature_application.feature_profile.presentation.profile_page.ProfileState
import com.example.happyhabits.feature_application.home_page.HomePageEvent
import com.example.happyhabits.feature_application.presentation.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewmodel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state;

    fun onEvent(event: ProfileEvent.ChangePage) {
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

