package com.example.happyhabits.feature_application.feature_workout.presentation.workout_pop_up_statistics_screen

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
data class WorkoutPopUpStatisticsState (
    val type: String? = "",
    val monthSelected: Int = -1,
    val yearSelected: Int = -1,
    val dateSelected:Boolean = false,
    val averageDurationPerWorkout: String? = "hh : mm",
    val averageElevationPerWorkout: Double? = 0.0,
    val averageKilometersPerWorkout: Double?= 0.0,
    val averageKgsPerWorkout: Float?= 0f,
    val averageNumOfExercisesPerWorkout: Int?= 0,
    val totalNumOfWorkouts: Int?= 0,
    val topFiveExercise: List<String> = listOf("Mountain Pose",
        "Downward Dog",
        "testing a really really really big title: Warrior Pose",
        "Tree Pose",
        "testing a big title: Child's Pose"),
    val totalNumOfKilometers: Float?= 0f,
    val monthList: List<String> = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"),
    val yearList: List<Int> = (2000..LocalDate.now().year).toList()
) { }