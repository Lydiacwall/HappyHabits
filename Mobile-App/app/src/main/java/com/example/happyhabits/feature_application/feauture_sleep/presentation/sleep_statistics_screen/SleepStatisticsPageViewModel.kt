package com.example.happyhabits.feature_application.feature_statistics.presentation.sleep_statistics.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        val formatter = DateTimeFormatter.ofPattern("dd-MM")
        println("Last Monday: ${lastMonday.format(formatter)}")
        println("Last Sunday:  ${lastSunday.format(formatter)}")

        // set them from the actual database
        setList(listOf(6.7f, 10.2f, 8.1f, 8.5f, 9f, 5.6f, 7f))
        setAverage(7.8f)
        setDif(0.2)
        setQuality("good")


        
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

    fun setList(list : List<Float>){
        averageList = list
    }

    fun setAverage(av : Float){
        average = av
    }

    fun setDif(dif : Double){
        difference= dif
    }

    fun setQuality(qual : String){
        quality= qual
    }


}