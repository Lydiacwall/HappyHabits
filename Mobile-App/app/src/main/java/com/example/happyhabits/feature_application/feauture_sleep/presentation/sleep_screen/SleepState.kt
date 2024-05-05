package com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState

data class SleepState @RequiresApi(Build.VERSION_CODES.O) constructor(
    var time : String = "",
    var quality : String = ""
)