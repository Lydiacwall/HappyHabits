package com.example.happyhabits.feature_application.feature_mood.domain.model

import com.example.happyhabits.core.domain.model.Habit
import java.time.LocalDate

class Mood (
    id: String,
    userId: String,
    date: LocalDate,
    val diary: String,
    val scale: String
): Habit(id = id, userId = userId, date = date)