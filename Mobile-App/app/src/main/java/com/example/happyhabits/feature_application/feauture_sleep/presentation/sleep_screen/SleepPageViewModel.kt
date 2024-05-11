package com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.core.data.model.Manager
import com.example.happyhabits.feature_application.feauture_sleep.domain.use_case.SleepUseCases
import kotlinx.coroutines.launch
import java.time.LocalDate

@HiltViewModel
class SleepPageViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val sleepUseCases: SleepUseCases
) : ViewModel()
    {
        @RequiresApi(Build.VERSION_CODES.O)
        private val _state = mutableStateOf(SleepState())
        @RequiresApi(Build.VERSION_CODES.O)
        val state : State<SleepState> = _state;
        
        @RequiresApi(Build.VERSION_CODES.O)
        fun onEvent(event:SleepPageEvent){
            when(event){
                is SleepPageEvent.SleepGoalChanged->{
                    //event.navController.navigate()
                }
                is SleepPageEvent.QualityChanged -> {
                    _state.value = _state.value.copy(quality = event.quality)
                }
                is SleepPageEvent.TimeChanged -> {
                    _state.value= _state.value.copy(time = event.time)
                }
                is SleepPageEvent.AddSleepLog ->{
                    viewModelScope.launch {
                        Manager.currentUser?.let { sleepUseCases.addSleepHabit(it.id, LocalDate.now(), time= event.time, quality = event.quality) }
                    }
                }
            }
        }
    }
