package com.example.happyhabits.feature_application.feature_toilet.domain.model

import com.example.happyhabits.core.domain.model.Habit
import java.sql.Time
import java.time.LocalDate

class Toilet (
    id : String,
    userId : String,
    date : LocalDate,
    var type : String,
    var time : String ,
    var notes : String? = ""
) : Habit(id, userId, date){}