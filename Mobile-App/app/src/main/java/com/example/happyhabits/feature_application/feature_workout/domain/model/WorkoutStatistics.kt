package com.example.happyhabits.feature_application.feature_workout.domain.model

class WorkoutStatistics(
    val type: String,
    val avgDuration: Double,
    val totalWorkouts: Int,
    val avgKmsPerWorkout: Double? = 0.0,
    val avgElevationPerWorkout: Double? = 0.0,
    val totalKms: Float? = 0f,
    val avgKgsPerWorkout: Float? = 0f,
    val monthsTopFiveExercises: List<String>? = listOf(),
    val avgNumberOfExercisesPerWorkout: Int? = 0
) {
    override fun toString(): String {
        return "WorkoutStatistics(type='$type', avgDuration=$avgDuration, totalWorkouts=$totalWorkouts, " +
                "avgKmsPerWorkout=$avgKmsPerWorkout, avgElevationPerWorkout=$avgElevationPerWorkout, " +
                "totalKms=$totalKms, avgKgsPerWorkout=$avgKgsPerWorkout, " +
                "monthsTopFiveExercises=$monthsTopFiveExercises, " +
                "avgNumberOfExercisesPerWorkout=$avgNumberOfExercisesPerWorkout)"
    }
}