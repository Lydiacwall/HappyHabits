package com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_statistics_screen

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
@RequiresApi(Build.VERSION_CODES.O)
data class SleepStatisticsState (
    val startDate: String = LocalDate.now().with(DayOfWeek.SUNDAY).minusWeeks(1).toString(),
    val endDate: String = LocalDate.now().with(DayOfWeek.SUNDAY).minusWeeks(1).minusDays(6).toString()
)
