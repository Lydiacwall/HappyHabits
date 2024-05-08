package com.example.happyhabits.feature_application.home_page


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.happyhabits.core.data.model.Manager
import com.example.happyhabits.core.domain.model.Type
import com.example.happyhabits.core.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.happyhabits.feature_application.presentation.util.Screen
import com.example.happyhabits.feature_authentication.presentation.sign_up_user.SignUpUserState
import javax.inject.Inject

@HiltViewModel
class HomePageViewmodel @Inject constructor(
): ViewModel() {
    private val _state = mutableStateOf(HomePageState())
    val state: State<HomePageState> = _state
    init {
        val user = Manager.currentUser
        _state.value = _state.value.copy(user= user)
    }
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
