package com.example.happyhabits.feature_application.feature_mood.data.repository

import com.example.happyhabits.feature_application.feature_mood.data.model.MoodForm
import com.example.happyhabits.feature_application.feature_mood.data.model.MoodStatistics
import com.example.happyhabits.feature_application.feature_mood.data.network.ApiHelper
import com.example.happyhabits.feature_application.feature_mood.domain.repository.IMoodRepository
import java.time.LocalDate

class MoodRepository(
    private val moodApi: ApiHelper
): IMoodRepository {
    override suspend fun addMoodHabit(
        userId: String,
        date: LocalDate,
        diary: String,
        scale: String
    ) {
        try {
            moodApi.addMoodHabit(
                MoodForm(
                    userId = userId,
                    date = date.toString(),
                    diary = diary,
                    scale = scale
                )
            )
        } catch (e: Exception) {
            throw e;
        }
    }

    override suspend fun calcMoodStatistics(userId: String): MoodStatistics? {
      try{
          return moodApi.calcMoodStatistics(userId)
      }
      catch (e : Exception){
          throw e;
      }
    }

    
}