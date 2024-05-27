package com.example.happyhabits.feature_application.feature_statistics.statistics_screen

import androidx.lifecycle.ViewModel
import com.example.happyhabits.R
import com.example.happyhabits.feature_application.presentation.util.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsPageViewModel @Inject constructor(): ViewModel() {
    // we declare all the screens here we want to be redirected

    data class StatisticItem(val title: String, val icon: Int, val route: String)

    private val screenlist = listOf(
    StatisticItem("Sleep Statistics", R.drawable.sleep_icon_purple,Screen.SleepStatisticsPageScreen.route),
    StatisticItem("Workout Statistics", R.drawable.running_purple_icon,Screen.WorkoutStatisticsPageScreen.route),
    StatisticItem("Food Statistics", R.drawable.food_icon_purple, Screen.FoodStatisticsScreen.route),
    StatisticItem("Symptom Statistics", R.drawable.symptoms_icon_purple,Screen.SymptomsStatisticsPageSceen.route),
    StatisticItem("Medication Statistics", R.drawable.medication_icon_purple,Screen.MedicationPageScreen.route),
    StatisticItem("Mood Statistics", R.drawable.mood_icon_purple,Screen.MoodStatisticsPageScreen.route),
    StatisticItem("Toilet Statistics", R.drawable.toilet_icon_purple,Screen.SleepPageScreen.route)
    )
    fun getList(): List<StatisticItem> {
        return screenlist
    }

}