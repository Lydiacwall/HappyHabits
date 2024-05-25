package com.example.happyhabits.feature_application.feature_statistics_workout.presentation.workout_pop_up_statistics_screen

sealed class WorkoutPopUpStatisticsEvent {
    data class UpdatedMonth(val newMonth: Int) :WorkoutPopUpStatisticsEvent()
    data class UpdatedYear(val newYear: Int) :WorkoutPopUpStatisticsEvent()
    data class DateSelected(val noImportantString: String) :WorkoutPopUpStatisticsEvent()
}