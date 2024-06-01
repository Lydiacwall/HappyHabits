package com.example.happyhabits.feature_application.feature_workout.data.network

import android.util.Log
import com.example.happyhabits.feature_application.feature_workout.data.model.ExerciseWorkoutForm
import com.example.happyhabits.feature_application.feature_workout.data.model.ExercisesWorkoutStatistics
import com.example.happyhabits.feature_application.feature_workout.data.model.FastActivityForm
import com.example.happyhabits.feature_application.feature_workout.data.model.FastActivityStatistics
import com.example.happyhabits.feature_application.feature_workout.data.model.WeightsForm
import com.example.happyhabits.feature_application.feature_workout.data.model.WeightsStatistics
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
            val responseBody = response.body()?: FastActivityStatistics(averageDuration = 0.0, averageElevation = 0.0, averageQuantity = 0.0, totalQuantity = 0f, totalWorkouts = 0)
            val newWorkout = WorkoutStatistics(type= type, totalWorkouts = responseBody.totalWorkouts , avgDuration = responseBody.averageDuration, avgKmsPerWorkout = responseBody.averageQuantity, avgElevationPerWorkout = responseBody.averageElevation, totalKms = responseBody.totalQuantity)
            return newWorkout
        } catch (e: Exception) {
            throw e;
        }
    }
    suspend fun getExercisesWorkoutStatistics(userId: String, month: Int, year:Int, type: String): WorkoutStatistics {
        try {
            val response = apiService.getExercisesWorkoutStatistics(userId, month, year, type)
            val responseBody = response.body()?: ExercisesWorkoutStatistics(averageDuration = 0.0, topExercises = emptyList(), averageExercisePerWorkout = 0, totalWorkouts = 0)
            val newWorkout = WorkoutStatistics(type= type, totalWorkouts = responseBody.totalWorkouts , avgDuration = responseBody.averageDuration, monthsTopFiveExercises = responseBody.topExercises, avgNumberOfExercisesPerWorkout = responseBody.averageExercisePerWorkout)
            return newWorkout
        } catch (e: Exception) {
            throw e;
        }
    }
    suspend fun getWeightsWorkoutStatistics(userId: String, month: Int, year:Int): WorkoutStatistics {
        try {
            val response = apiService.getWeightsWorkoutStatistics(userId, month, year)
            val responseBody = response.body()?: WeightsStatistics(averageDuration = 0.0, topExercises = emptyList(), totalWorkouts = 0, averageExercisePerWorkout = 0, averageKgsPerWorkout = 0f)
            val newWorkout = WorkoutStatistics(type= "Weights", totalWorkouts = responseBody.totalWorkouts , avgDuration = responseBody.averageDuration, avgNumberOfExercisesPerWorkout = responseBody.averageExercisePerWorkout, monthsTopFiveExercises = responseBody.topExercises, avgKgsPerWorkout = responseBody.averageKgsPerWorkout)
            return newWorkout
        } catch (e: Exception) {
            throw e;
        }
    }
}