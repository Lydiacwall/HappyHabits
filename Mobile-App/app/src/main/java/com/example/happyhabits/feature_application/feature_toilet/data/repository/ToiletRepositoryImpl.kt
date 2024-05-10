package com.example.happyhabits.feature_application.feature_toilet.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.happyhabits.feature_application.feature_toilet.data.model.ToiletForm
import com.example.happyhabits.feature_application.feature_toilet.data.network.ApiHelper
import com.example.happyhabits.feature_application.feature_toilet.domain.model.Toilet
import com.example.happyhabits.feature_application.feature_toilet.domain.repository.IToiletRepository
import java.time.LocalDate

class ToiletRepositoryImpl(
    private val toiletApi: ApiHelper
): IToiletRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun addToiletHabit(
        userId: String,
        date: LocalDate,
        type: String,
        time: String,
        notes: String
    ){
        try {
            toiletApi.addToiletHabit(
                ToiletForm(
                    userId = userId,
                    date = date.toString(),
                    type = type,
                    time = time,
                    notes = notes
                )
            )
        } catch (e: Exception) {
            throw e;
        }
    }
}