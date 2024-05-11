package com.example.happyhabits.feature_application.feauture_sleep.domain.model

import com.example.happyhabits.core.domain.model.Habit
import java.time.LocalDate

class Sleep(
    id : String,
    userId : String,
    date : LocalDate,
    var time : String,
    var quality : String
): Habit(id = id, userId = userId, date = date)