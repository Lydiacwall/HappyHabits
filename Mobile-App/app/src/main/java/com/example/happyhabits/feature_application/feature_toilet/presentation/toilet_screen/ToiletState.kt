package com.example.happyhabits.feature_application.feature_toilet.presentation.toilet_screen

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalTime

data class ToiletState @RequiresApi(Build.VERSION_CODES.O) constructor(
    var type : String ="Select Type",
    var time : String = "12:00",
    var notes : String = "",

 )
