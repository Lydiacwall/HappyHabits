package com.example.happyhabits.core.data.model

data class StatisticsForm(
    val senderId: String,
    val groupId: String,
    val statistics: Map<String, Any>,
    val type: String,
    val friendUserName: String
)