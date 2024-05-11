package com.example.happyhabits.feature_application.feauture_sleep.data.repository

import com.example.happyhabits.feature_application.feauture_sleep.data.model.SleepForm
import com.example.happyhabits.feature_application.feauture_sleep.data.network.ApiHelper
import com.example.happyhabits.feature_application.feauture_sleep.domain.repository.ISleepRepository
import java.time.LocalDate

class SleepRepository(
    private val userApi: ApiHelper
): ISleepRepository {
    override suspend fun addSleepHabit(
        userId: String,
        date: LocalDate,
        time: String,
        quality: String
    ) {
        try {
            return userApi.addSleepHabit(
                SleepForm(
                    userId= userId,
                    date= date.toString(),
                    time= time,
                    quality= quality
                )
            )
        } catch (e: Exception) {
            throw e;
        }
    }

}