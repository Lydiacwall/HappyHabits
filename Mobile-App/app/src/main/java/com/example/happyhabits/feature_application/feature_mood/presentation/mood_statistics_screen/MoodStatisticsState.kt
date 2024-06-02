package com.example.happyhabits.feature_application.feature_mood.presentation.mood_statistics_screen

import com.example.happyhabits.feature_application.feature_chat.domain.model.FriendGroup

data class MoodStatisticsState(
    var moodList: HashMap<String, String> = hashMapOf("5/27/2024" to "Meh"),
    val clientsList: List<FriendGroup> = emptyList()
)

