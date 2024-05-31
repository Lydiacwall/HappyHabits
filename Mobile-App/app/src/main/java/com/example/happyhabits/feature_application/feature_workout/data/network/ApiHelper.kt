package com.example.happyhabits.feature_application.feature_workout.data.network

import android.util.Log
import com.example.happyhabits.feature_application.feature_workout.data.model.ExerciseWorkoutForm
import com.example.happyhabits.feature_application.feature_workout.data.model.FastActivityForm
import com.example.happyhabits.feature_application.feature_workout.data.model.WeightsForm
import com.example.happyhabits.feature_application.feature_workout.domain.model.ExercisesWorkout
import com.example.happyhabits.feature_application.feature_workout.domain.model.FastActivity
import com.example.happyhabits.feature_application.feature_workout.domain.model.Weights
import com.example.happyhabits.feature_application.feature_workout.domain.model.Workout
import com.example.happyhabits.feature_application.feature_workout.domain.model.WorkoutStatistics

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
                val exercisesForm = ExerciseWorkoutForm(
                    userId = exerciseWorkout.userId,
                    date = exerciseWorkout.date.toString(),
                    time = exerciseWorkout.time,
                    type =  exerciseWorkout.type,
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
    suspend fun getFastWorkoutStatistics(userId: String, month: Int, year:Int, type: String): WorkoutStatistics {
        try {
            val response = apiService.getFastWorkoutStatistics(userId, month, year, type)
            Log.d("RESPONSE", response.toString())
            val newWorkout = WorkoutStatistics(type= type, totalWorkouts = response.totalWorkouts , avgDuration = response.averageDuration, avgKmsPerWorkout = response.averageQuantity, avgElevationPerWorkout = response.averageElevation, totalKms = response.totalQuantity)
            Log.d("NEWWORKOUT", newWorkout.toString())
            return newWorkout
        } catch (e: Exception) {
            throw e;
        }
    }
    suspend fun getExercisesWorkoutStatistics(userId: String, month: Int, year:Int, type: String): WorkoutStatistics {
        try {
            val response = apiService.getExercisesWorkoutStatistics(userId, month, year, type)
            Log.d("RESPONSE", response.toString())
            val newWorkout = WorkoutStatistics(type= type, totalWorkouts = response.totalWorkouts , avgDuration = response.averageDuration, monthsTopFiveExercises = response.topExercises, avgNumberOfExercisesPerWorkout = response.averageExercisePerWorkout)
            Log.d("NEWWORKOUT", newWorkout.toString())
            return newWorkout
        } catch (e: Exception) {
            throw e;
        }
    }
    suspend fun getWeightsWorkoutStatistics(userId: String, month: Int, year:Int): WorkoutStatistics {
        try {
            val response = apiService.getWeightsWorkoutStatistics(userId, month, year)
            Log.d("RESPONSE", response.toString())
            val newWorkout = WorkoutStatistics(type= "Weights", totalWorkouts = response.totalWorkouts , avgDuration = response.averageDuration, avgNumberOfExercisesPerWorkout = response.averageExercisePerWorkout, monthsTopFiveExercises = response.topExercises)
            Log.d("NEWWORKOUT", newWorkout.toString())
            return newWorkout
        } catch (e: Exception) {
            throw e;
        }
    }
}