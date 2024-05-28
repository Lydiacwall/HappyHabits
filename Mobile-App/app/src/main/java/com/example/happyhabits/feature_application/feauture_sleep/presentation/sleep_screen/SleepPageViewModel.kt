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
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
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
        init{
            _state.value= _state.value.copy(sleepgoal = Manager.currentUser?.sleepGoal.toString())
        }

        fun getSleepGoal() : String {
            return Manager.currentUser?.sleepGoal.toString()
        }

        
        @RequiresApi(Build.VERSION_CODES.O)
        fun onEvent(event:SleepPageEvent){
            when(event){
                is SleepPageEvent.QualityChanged -> {
                    _state.value = _state.value.copy(quality = event.quality)
                }
                is SleepPageEvent.TimeChanged -> {
                    val newtime = event.time.toFloat().toInt().toString()
                    _state.value= _state.value.copy(time = newtime)
                    print(_state.value.time)
                }
                is SleepPageEvent.AddSleepLog -> {
                    viewModelScope.launch {
                        Manager.currentUser?.let { sleepUseCases.addSleepHabit(it.id, LocalDate.now(), time= event.time, quality = event.quality) }
                    }
                }
                is SleepPageEvent.UpdateSleepGoal -> {
                    // Update current user in mobile phone
                    if (event.sleepGoal.isEmpty()) {
                        event.sleepGoal = "0"
                    }
                    _state.value= _state.value.copy(sleepgoal = Manager.currentUser?.sleepGoal.toString())
                    Manager.currentUser?.sleepGoal = event.sleepGoal.toInt()
                    // Update current user in database
                    viewModelScope.launch {
                        Manager.currentUser?.let { sleepUseCases.updateSleepGoal(it.id, event.sleepGoal.toInt()) }
                    }
                }
            }
        }

    }
