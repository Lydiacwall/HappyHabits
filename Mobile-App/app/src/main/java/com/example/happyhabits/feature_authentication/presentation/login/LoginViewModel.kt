package com.example.happyhabits.feature_authentication.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.feature_authentication.domain.use_case.AuthenticationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.happyhabits.core.data.model.Manager

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases
): ViewModel() {
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state;

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _state.value = _state.value.copy(email = event.email)
            }
            is LoginEvent.PasswordChanged -> {
                _state.value = _state.value.copy(password = event.password)
            }
            is LoginEvent.Validate -> {
                viewModelScope.launch {
                    val user = authenticationUseCases.authenticateUser(event.password, event.email)
                    if (user == null) {
                        _state.value = _state.value.copy(error = "No user found with the provided credentials.", isSuccess = false)
                    } else {
                        Manager.setUser(user)
                        _state.value = _state.value.copy(user = user, isSuccess = true, error = null)
                    }
                }
            }
        }
    }
}
