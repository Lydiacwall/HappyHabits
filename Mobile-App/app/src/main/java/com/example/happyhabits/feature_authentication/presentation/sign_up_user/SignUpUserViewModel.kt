package com.example.happyhabits.feature_authentication.presentation.sign_up_user

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.feature_authentication.domain.use_case.AuthenticationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpUserViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases
): ViewModel() {
    private val _state = mutableStateOf(SignUpUserState())
    val state: State<SignUpUserState> = _state;

    fun onEvent(event: SignUpUserEvent) {
        when (event) {
            is SignUpUserEvent.AddUser -> {

                viewModelScope.launch {
                    // TODO Sign up user
                }
            }
        }
    }
}