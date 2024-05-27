package com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_statistics_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.feature_application.feauture_sleep.domain.use_case.SleepUseCases
import com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_screen.SleepPageEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class SleepStatisticsPageViewModel@Inject constructor(): ViewModel() {

    //val imageList()
    private var averageList= listOf(0.0f)
    private var average = 0.0f
    private var difference = 0.0
    private var quality =" "
    init{
        //by default show the previous week stats

        val today = LocalDate.now()
        val lastSunday = today.with(DayOfWeek.SUNDAY).minusWeeks(1)
        val lastMonday = lastSunday.minusDays(6)


        // set them from the actual database
        setList(listOf(6.7f, 10.2f, 8.1f, 8.5f, 9f, 5.6f, 7f))
        setAverage(7.8f)
        setDif(0.2)
        setQuality("good")


        
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: SleepStatisticsPageEvent){
        when(event) {
            is SleepStatisticsPageEvent.WeekhasChanged ->{
                SleepUseCases
            }
        }
    }

    fun getList(): List<Float> {
        return averageList
    }

    fun getAverage(): Float {
        return average
    }

    fun getDifference(): Double{
        return difference
    }

    fun getQuality() : String{
        return quality
    }

    private fun setList(list : List<Float>){
        averageList = list
    }

    private fun setAverage(av : Float){
        average = av
    }

    private fun setDif(dif : Double){
        difference= dif
    }

    private fun setQuality(qual : String){
        quality= qual
    }



}