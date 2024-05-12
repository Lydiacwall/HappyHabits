package com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_screen

import androidx.navigation.NavController

sealed class SleepPageEvent {
    data class TimeChanged(val time: String ) :  SleepPageEvent()
    data class QualityChanged(val quality :String) : SleepPageEvent()
    data class SleepGoalChanged(val sleepgoal : Int) : SleepPageEvent()
    data class AddSleepLog(val time : String , val quality: String) : SleepPageEvent()

    data class UpdateSleepGoal(val sleepGoal: String) : SleepPageEvent()
}