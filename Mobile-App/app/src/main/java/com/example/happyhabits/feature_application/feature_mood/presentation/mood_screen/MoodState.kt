package com.example.happyhabits.feature_application.feature_mood.presentation.mood_screen

import android.os.Build
import androidx.annotation.RequiresApi

data class MoodState @RequiresApi(Build.VERSION_CODES.O) constructor(
    var mood : String ="",
    var diary : String = ""
)