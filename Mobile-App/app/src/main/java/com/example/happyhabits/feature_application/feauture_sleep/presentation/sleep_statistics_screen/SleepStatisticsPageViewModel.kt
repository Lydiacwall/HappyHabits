package com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_statistics_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.core.data.model.Manager
import com.example.happyhabits.core.domain.use_case.CoreUseCases
import com.example.happyhabits.feature_application.feature_chat.domain.use_case.FriendChatUseCases
import com.example.happyhabits.feature_application.feature_toilet.presentation.toilet_screen.ToiletState
import com.example.happyhabits.feature_application.feauture_sleep.domain.use_case.SleepUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class SleepStatisticsPageViewModel @Inject constructor(
    private val friendChatUseCases: FriendChatUseCases,
    private val coreUseCases: CoreUseCases,
    private val sleepUseCases: SleepUseCases

): ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val _state = mutableStateOf(SleepStatisticsState())
    @RequiresApi(Build.VERSION_CODES.O)
    val state: State<SleepStatisticsState> = _state ;

    init{

        
        val today = LocalDate.now()
        val lastSunday = today.with(DayOfWeek.SUNDAY).minusWeeks(1)
        val lastMonday = lastSunday.minusDays(6)

        viewModelScope.launch {
            val userId: String = Manager.currentUser?.id.toString()
            val friends = friendChatUseCases.getFriendList(userId)
            _state.value = _state.value.copy(clientsList = friends)
            Manager.currentUser?.let {
                val sleepStas = sleepUseCases.calcSleepStatistics(
                    it.id,
                    lastMonday.toString(),
                    lastSunday.toString()
                )
                if (sleepStas != null) {
                    setList(sleepStas.sleepDurations)
                    setAverage(sleepStas.dailyAverageMinutes,sleepStas.dailyAverageHours)
                    setDif(sleepStas.differenceInMinutes,sleepStas.differenceInHours)
                    setQuality(sleepStas.mostFrequentQuality)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: SleepStatisticsPageEvent){
        when(event) {
            is SleepStatisticsPageEvent.WeekhasChanged ->{
                viewModelScope.launch {
                    Manager.currentUser?.let {
                        val sleepStas = sleepUseCases.calcSleepStatistics(
                            it.id,
                            event.startDate,
                            event.endDate
                        )
                        if (sleepStas != null) {
                            setList(sleepStas.sleepDurations)
                            setAverage(sleepStas.dailyAverageMinutes, sleepStas.dailyAverageHours)
                            setDif(sleepStas.differenceInMinutes, sleepStas.differenceInHours)
                            setQuality(sleepStas.mostFrequentQuality)
                        }
                        _state.value = _state.value.copy(averageHours = (sleepStas?.dailyAverageHours?:0f), averageMinutes = (sleepStas?.dailyAverageMinutes?:0f), differenceHours = (sleepStas?.differenceInHours?:0f), differenceMinutes = (sleepStas?.differenceInMinutes?:0f))
                    }
                }
            }
            is SleepStatisticsPageEvent.SendStatistics->{
                viewModelScope.launch {
                    val sleepStatisticsDictionary: Map<String, Any> = mapOf(
                        "sleepDurations" to _state.value.sleepDurations,
                        "dailyAverageHours" to _state.value.averageHours,
                        "dailyAverageMinutes" to _state.value.averageMinutes,
                        "differenceInMinutes" to _state.value.differenceMinutes,
                        "differenceInHours" to _state.value.differenceHours,
                        "mostFrequentQuality" to _state.value.quality
                    )
                    val response = coreUseCases.sendStatistics(
                        senderId = Manager.currentUser?.id.toString(),
                        groupId = _state.value.clientsList[event.indexOfFriend].groupId,
                        type = "Sleep",
                        statistics = sleepStatisticsDictionary,
                        friendUsername = _state.value.clientsList[event.indexOfFriend].friendUsername
                    )
                }
            }
        }
    }


    private fun setList(list : List<Float>){
        _state.value = _state.value.copy(sleepDurations = list)

    }

    private fun setAverage(avmin : Float , avhours : Float){
        _state.value = _state.value.copy(average = (avhours + (avmin * 0.1)).toFloat() )

    }

    private fun setDif(difmin : Float, difHour : Float){
        _state.value = _state.value.copy(difference = (difHour + difmin *0.1).toFloat() )

    }

    private fun setQuality(qual : String){
       _state.value = _state.value.copy(quality = qual)
    }



}