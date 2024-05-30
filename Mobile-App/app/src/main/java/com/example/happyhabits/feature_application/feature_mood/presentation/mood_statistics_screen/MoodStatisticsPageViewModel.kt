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
    init{

        viewModelScope.launch {
            Manager.currentUser?.let{
                val moodStats= moodUseCases.calcMoodStatistics(
                    it.id,
                )
                if(moodStats != null){
                    setList(moodStats.moodMap)
                }
            }
        }
    }

    private fun setList(list : HashMap<String, String>){
        monthList = list
    }

    fun getList() : HashMap<String , String>{
        return  monthList
    }
}