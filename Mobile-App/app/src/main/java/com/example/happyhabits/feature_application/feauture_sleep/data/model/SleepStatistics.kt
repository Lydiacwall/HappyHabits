package com.example.happyhabits.feature_application.feauture_sleep.data.model

data class SleepStatistics (
    val sleepDurations : List<Float>,
    val dailyAverageHours : Float,
    val dailyAverageMinutes : Float,
    val differenceInHours : Float,
    val differenceInMinutes : Float,
    val mostFrequentQuality : String
)

