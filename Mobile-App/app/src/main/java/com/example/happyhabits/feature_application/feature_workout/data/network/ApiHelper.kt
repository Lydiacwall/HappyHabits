package com.example.happyhabits.feature_application.feature_workout.data.network

import com.example.happyhabits.feature_application.feature_workout.data.model.ExercisesWorkoutForm
import com.example.happyhabits.feature_application.feature_workout.data.model.FastActivityForm
import com.example.happyhabits.feature_application.feature_workout.data.model.WeightsForm
import com.example.happyhabits.feature_application.feature_workout.domain.model.ExercisesWorkout
import com.example.happyhabits.feature_application.feature_workout.domain.model.FastActivity
import com.example.happyhabits.feature_application.feature_workout.domain.model.Weights
import com.example.happyhabits.feature_application.feature_workout.domain.model.Workout
import kotlinx.serialization.json.Json

class ApiHelper(private val apiService: ApiService) {
    suspend fun addWorkout(workout: Workout, type: Int) {
        try {
            if (type == 0) {
                val fastActivity = workout as FastActivity
                val response = apiService.addFastActivity(
                    FastActivityForm(
                        userId = fastActivity.userId,
                        date = fastActivity.date.toString(),
                        type = fastActivity.type,
                        time = fastActivity.time,
                        duration = fastActivity.duration,
                        notes = fastActivity.notes,
                        quantity = fastActivity.quantity,
                        elevation = fastActivity.elevation
                    )
                )
            }
            else if (type == 1) {
                val weights = workout as Weights
                val weightsForm = WeightsForm(
                    userId = weights.userId,
                    date = weights.date.toString(),
                    time = weights.time,
                    duration = weights.duration,
                    notes = weights.notes,
                    exercises = weights.exercisesList
                )
                val response = apiService.addWeightsActivity(weightsForm)
            }
            else {
                val exerciseWorkout = workout as ExercisesWorkout
                val exercisesForm = ExercisesWorkoutForm(
                    userId = exerciseWorkout.userId,
                    date = exerciseWorkout.date.toString(),
                    time = exerciseWorkout.time,
                    notes = exerciseWorkout.notes,
                    duration = exerciseWorkout.duration,
                    simpleExercises = exerciseWorkout.simpleExercisesList
                )
                val response = apiService.addExerciseWorkoutActivity(exercisesForm)
            }

        } catch (e: Exception) {
            throw e;
        }
    }
}