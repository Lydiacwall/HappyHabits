package com.example.happyhabits.feature_application.feature_symptoms.presentation.symptoms_statistics_screen

import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
 data class SymptomStatisticsState (
     var symptomList : List<String> = listOf("")
 )