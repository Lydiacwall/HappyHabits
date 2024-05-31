package com.example.happyhabits.feature_application.feature_mood.presentation.mood_statistics_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.core.data.model.Manager
import com.example.happyhabits.feature_application.feature_mood.domain.use_case.MoodUseCases
import com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_statistics_screen.SleepStatisticsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class MoodStatisticsPageViewModel @Inject constructor(
    private val moodUseCases: MoodUseCases
): ViewModel() {

    private var monthList : HashMap<String,String> = hashMapOf("" to "")

    @RequiresApi(Build.VERSION_CODES.O)
    private val _state = mutableStateOf(MoodStatisticsState())
    @RequiresApi(Build.VERSION_CODES.O)
    val state: State<MoodStatisticsState> = _state ;


    init{

        viewModelScope.launch {
            Manager.currentUser?.let{
                val moodStats= moodUseCases.calcMoodStatistics(
                    it.id,
                )
                if(moodStats != null){
                    _state.value= _state.value.copy(moodList =moodStats.moodMap )
                }
                else{
                    print("null")
                }
            }
        }
    }


}