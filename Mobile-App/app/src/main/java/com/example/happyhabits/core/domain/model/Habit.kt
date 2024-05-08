package com.example.happyhabits.core.domain.model

import java.time.LocalDate

abstract class Habit (
    var id : String,
    var userId : String,
    var date : LocalDate
)