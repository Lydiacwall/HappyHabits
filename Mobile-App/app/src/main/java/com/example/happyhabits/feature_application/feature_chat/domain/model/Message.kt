package com.example.happyhabits.feature_application.feature_chat.domain.model

data class Message(
    val senderId: String,
    val timestamp: String,
    val content: String
)
