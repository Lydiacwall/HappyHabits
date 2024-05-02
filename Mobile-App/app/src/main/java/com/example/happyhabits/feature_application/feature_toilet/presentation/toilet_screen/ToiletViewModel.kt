package com.example.happyhabits.feature_application.feature_toilet.presentation.toilet_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope

@HiltViewModel
class ToiletViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val _state = mutableStateOf(ToiletState())
    @RequiresApi(Build.VERSION_CODES.O)
    val state: State<ToiletState> = _state ;

    fun newToiletLog() {

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: ToiletPageEvent) {
        when (event) {
            is ToiletPageEvent.NoteChanged -> {
                _state.value = _state.value.copy(notes = event.notes)

            }

            is ToiletPageEvent.TimeChanged -> {
                _state.value = _state.value.copy(time = event.time)
            }

            is ToiletPageEvent.TypeChanged -> {

                _state.value = _state.value.copy(type = event.type)

            }
            is ToiletPageEvent.AddToiletLog ->{
                // TODO : SAVE
            }
        }
    }
}
