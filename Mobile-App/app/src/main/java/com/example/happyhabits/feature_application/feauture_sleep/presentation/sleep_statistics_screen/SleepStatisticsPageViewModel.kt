package com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_statistics_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.core.data.model.Manager
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

    private var sleepDurations= listOf(0.0f)

    private var average = 0.0f
    private var difference = 0.0f

    private var quality =" "
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

    fun getList(): List<Float> {
        return sleepDurations
    }

    fun getAverage(): Float {
        return average
    }

    fun getDifference(): Float {
        return difference
    }

    fun getQuality() : String{
        return quality
    }

    private fun setList(list : List<Float>){
        sleepDurations = list
    }

    private fun setAverage(avmin : Float , avhours : Float){
        average = (avhours + (avmin * 0.1)).toFloat()
    }

    private fun setDif(difmin : Float, difHour : Float){
        difference= (difHour + difmin *0.1).toFloat()
    }

    private fun setQuality(qual : String){
        quality= qual
    }



}