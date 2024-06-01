package com.example.happyhabits.feature_application.feature_symptoms.presentation.symptoms_statistics_screen

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
 data class SymptomStatisticsState (
     var symptomList : List<String> = listOf(""),
     var month : String = Month.of( LocalDate.now().month.value).getDisplayName(TextStyle.FULL, Locale.getDefault())

 )