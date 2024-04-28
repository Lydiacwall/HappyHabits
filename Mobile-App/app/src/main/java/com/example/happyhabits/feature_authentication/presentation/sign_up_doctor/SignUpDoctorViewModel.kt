//package com.example.happyhabits.feature_authentication.presentation.sign_up_doctor
//
//import androidx.compose.runtime.State
//import androidx.compose.runtime.mutableStateOf 
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.happyhabits.feature_authentication.domain.use_case.AuthenticationUseCases
//import com.example.happyhabits.feature_authentication.domain.model.InvalidUserException
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//public class SignUpDoctorViewModel @Inject constructor(
//    private val authenticationUseCases: AuthenticationUseCases
//): ViewModel() {
//    private val _state = mutableStateOf(SignUpUserState())
//    val state: State<SignUpUserState> = _state;
//
//    fun onEvent(event: SignUpUserEvent) {
//        when (event) {
//            is SignUpUserEvent.AddUser -> {
