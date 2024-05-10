package com.example.happyhabits.feature_application.feature_mood.presentation.mood_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.core.data.model.Manager
import com.example.happyhabits.feature_application.feature_toilet.presentation.toilet_screen.ToiletPageEvent
import com.example.happyhabits.feature_application.feature_toilet.presentation.toilet_screen.ToiletState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MoodViewModel @Inject constructor(

): ViewModel(){
    @RequiresApi(Build.VERSION_CODES.O)
    private val _state = mutableStateOf(MoodState())
    @RequiresApi(Build.VERSION_CODES.O)
    val state: MutableState<MoodState> = _state ;
    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: MoodPageEvent) {
        when (event) {
            is MoodPageEvent.MoodChanged -> {
               _state.value = _state.value.copy(mood = event.mood)
            }
           is  MoodPageEvent.DiaryChanged-> {
               _state.value = _state.value.copy(diary = event.diary)
            }
            is MoodPageEvent.AddMoodLog->{
                // TODO : SAVE THE MOOD
            }

          }

    }
}