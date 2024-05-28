package com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_statistics_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.core.data.model.Manager
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
    private val sleepUseCases: SleepUseCases

): ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val _state = mutableStateOf(SleepStatisticsState())
    @RequiresApi(Build.VERSION_CODES.O)
    val state: State<SleepStatisticsState> = _state ;

    //private var sleepDurations= listOf(0.0f)

    //private var average = 0.0f
    //private var difference = 0.0f

    //private var quality =" "
    init{

        
        val today = LocalDate.now()
        val lastSunday = today.with(DayOfWeek.SUNDAY).minusWeeks(1)
        val lastMonday = lastSunday.minusDays(6)
        println("$lastMonday")
        println("$lastSunday")

        viewModelScope.launch {
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
                    setQuality("good")// TODO : CHANGE IT
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
                            setQuality("good")// TODO : CHANGE IT
                        }
                    }
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