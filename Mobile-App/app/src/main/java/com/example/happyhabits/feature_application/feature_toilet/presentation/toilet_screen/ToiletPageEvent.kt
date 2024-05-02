package com.example.happyhabits.feature_application.feature_toilet.presentation.toilet_screen

import androidx.navigation.NavController

sealed class ToiletPageEvent {

    data class TypeChanged(val type :String) : ToiletPageEvent()
    data class NoteChanged(val notes : String) : ToiletPageEvent()
    data class TimeChanged(val time : String) : ToiletPageEvent()
    data class AddToiletLog(val type: String,val notes: String , val time : String) : ToiletPageEvent()

}