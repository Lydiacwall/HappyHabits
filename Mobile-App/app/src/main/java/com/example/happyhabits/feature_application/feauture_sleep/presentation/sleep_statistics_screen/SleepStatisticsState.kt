package com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_statistics_screen

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
@RequiresApi(Build.VERSION_CODES.O)
data class SleepStatisticsState (

    var average : Float = 0.0f,
    var difference : Float = 0.0f,
    var sleepDurations : List<Float> = listOf(0.0f),
    var quality : String = "good"

)
