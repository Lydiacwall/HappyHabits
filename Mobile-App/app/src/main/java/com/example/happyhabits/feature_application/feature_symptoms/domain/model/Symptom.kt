package com.example.happyhabits.feature_application.feature_symptoms.domain.model

import com.example.happyhabits.core.domain.model.Habit
import java.time.LocalDate

class Symptom (
    id: String,
    userId: String,
    date: LocalDate,
    var name: String,
    var notes : String
): Habit(id = id, userId = userId, date = date)