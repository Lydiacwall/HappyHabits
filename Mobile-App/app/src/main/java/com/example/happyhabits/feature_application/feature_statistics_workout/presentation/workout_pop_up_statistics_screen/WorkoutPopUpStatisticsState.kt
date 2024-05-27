package com.example.happyhabits.feature_application.feature_statistics_workout.presentation.workout_pop_up_statistics_screen

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
    val averageElevationPerWorkout: Float? = -1f,
    val averageKilometersPerWorkout: Float?= -1f,
    val averageKgsPerWorkout: Float?= -1f,
    val averageNumOfExercisesPerWorkout: Float?= -1f,
    val totalNumOfWorkouts: Int?= -1,
    val topFiveExercise: List<String> = listOf("Mountain Pose",
        "Downward Dog",
        "testing a really really really big title: Warrior Pose",
        "Tree Pose",
        "testing a big title: Child's Pose"),
    val totalNumOfKilometers: Float?= -1f,
    val monthList: List<Int> = listOf(1,2,3,4,5,6,7,8,9,10,11,12),
    val yearList: List<Int> = (2000..LocalDate.now().year).toList()
) { }