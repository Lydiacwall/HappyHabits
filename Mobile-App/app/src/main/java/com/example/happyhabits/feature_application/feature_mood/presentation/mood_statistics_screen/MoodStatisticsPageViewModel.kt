package com.example.happyhabits.feature_application.feature_mood.presentation.mood_statistics_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.core.data.model.Manager
import com.example.happyhabits.core.domain.use_case.CoreUseCases
import com.example.happyhabits.feature_application.feature_chat.domain.use_case.FriendChatUseCases
import com.example.happyhabits.feature_application.feature_food.presentation.statistics_food.FoodStatisticsEvent
import com.example.happyhabits.feature_application.feature_mood.domain.use_case.MoodUseCases
import com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_statistics_screen.SleepStatisticsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class MoodStatisticsPageViewModel @Inject constructor(
    private val friendChatUseCases: FriendChatUseCases,
    private val coreUseCases: CoreUseCases,
    private val moodUseCases: MoodUseCases
): ViewModel() {


    @RequiresApi(Build.VERSION_CODES.O)
    private val _state = mutableStateOf(MoodStatisticsState())

    @RequiresApi(Build.VERSION_CODES.O)
    val state: State<MoodStatisticsState> = _state;

    init {
        val userId: String = Manager.currentUser?.id.toString()

        viewModelScope.launch {
            val friends = friendChatUseCases.getFriendList(userId)
            _state.value = _state.value.copy(clientsList = friends)
            Manager.currentUser?.let {
                val moodStats = moodUseCases.calcMoodStatistics(
                    it.id,
                )
                if (moodStats != null) {
                    _state.value = _state.value.copy(moodList = moodStats.moodMap)
                } else {
                    print("null")
                }
            }
        }
    }

    fun onEvent(event: MoodStatisticsPageEvent) {

        when (event) {
            is MoodStatisticsPageEvent.SendStatistics -> {
                val moodStatistics: Map<String, Any> = _state.value.moodList.map { (date, mood) ->
                    date to mood
                }.toMap()
                viewModelScope.launch {
                    val response = coreUseCases.sendStatistics(
                        senderId = Manager.currentUser?.id.toString(),
                        groupId = _state.value.clientsList[event.indexOfFriend].groupId,
                        type = "Mood",
                        statistics = mapOf("moodMap" to moodStatistics),
                        friendUsername = _state.value.clientsList[event.indexOfFriend].friendUsername
                    )
                }
            }
        }
    }
}