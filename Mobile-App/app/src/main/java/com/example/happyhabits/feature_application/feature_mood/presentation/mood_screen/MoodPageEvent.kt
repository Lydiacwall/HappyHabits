package com.example.happyhabits.feature_application.feature_mood.presentation.mood_screen

sealed class MoodPageEvent {
    data class MoodChanged(val mood : String) : MoodPageEvent()
    data class DiaryChanged(val diary : String) : MoodPageEvent()
    data class AddMoodLog(val diary : String,val mood : String) : MoodPageEvent()

}