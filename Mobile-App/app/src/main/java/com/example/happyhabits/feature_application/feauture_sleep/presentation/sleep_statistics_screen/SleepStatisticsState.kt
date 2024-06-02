package com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_statistics_screen

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.happyhabits.feature_application.feature_chat.domain.model.FriendGroup
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
@RequiresApi(Build.VERSION_CODES.O)
data class SleepStatisticsState (

    var average : Float = 0.0f,
    var difference : Float = 0.0f,
    var sleepDurations : List<Float> = listOf(0.0f),
    var quality : String = "good",
    var averageHours: Float = 0.0f,
    var averageMinutes: Float = 0.0f,
    var differenceHours: Float = 0.0f,
    var differenceMinutes: Float = 0.0f,
    val clientsList: List<FriendGroup> = emptyList()
)
