package com.example.happyhabits.feature_application.feature_toilet.data.model

import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
class ToiletDto (
    val id: String,
    val userId: String,
    val date: String,
    val type: String,
    val time: String,
    val notes: String
)
