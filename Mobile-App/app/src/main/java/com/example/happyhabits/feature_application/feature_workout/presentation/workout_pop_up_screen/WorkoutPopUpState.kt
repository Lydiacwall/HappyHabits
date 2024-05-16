package com.example.happyhabits.feature_application.feature_workout.presentation.workout_pop_up_screen

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.happyhabits.feature_application.feature_workout.domain.model.Exercise
import com.example.happyhabits.feature_application.feature_workout.domain.model.ExercisesWorkout
import com.example.happyhabits.feature_application.feature_workout.domain.model.FastActivity
import com.example.happyhabits.feature_application.feature_workout.domain.model.Weights
import com.example.happyhabits.feature_application.feature_workout.domain.model.Workout
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
data class WorkoutPopUpState (
    val type: String = "",
    val time: String = "hh : mm",
    val duration: String = "hh : mm",
    val notes: String = "",
    val unitMeasurement: String = "",
    val quantity: Float = 0f,
    val elevation: Float = 0f,
    val hoursTime: Int =0,
    val minutesTime: Int =0,
    val hoursDuration: Int =0,
    val minutesDuration: Int =0,
    val currentExercise: Exercise = Exercise("", 0, 0, 0f),
    val currentSimpleExercise: String = "",
    val exercises: List<Exercise> = emptyList(),
    val simpleExercises: List<String> = emptyList(),
    val currentWorkout: Workout? = Workout("", "", LocalDate.now(),"","", "hh : mm", "hh : mm",""),
    val currentFastActivityWorkout: Workout? = FastActivity("", "", LocalDate.now(),"", "", "hh : mm", "hh : mm", 0f,0f),
    val currentWeightsWorkout: Workout? = Weights("", "", LocalDate.now(),"hh : mm", "hh : mm", null),
    val currentExercisesWorkout: Workout? = ExercisesWorkout("", "", LocalDate.now(),"", "", "hh : mm", null)
)

