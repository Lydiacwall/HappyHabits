package com.example.happyhabits.feature_application.feature_workout.domain.use_case

data class WorkoutUseCases(
    val addWorkout: AddWorkout,
    val getFastWorkoutStatistics: GetFastWorkoutStatistics,
    val getExercisesWorkoutStatistics: GetExercisesWorkoutStatistics,
    val getWeightsWorkoutStatistics: GetWeightsWorkoutStatistics
)
