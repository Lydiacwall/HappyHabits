package com.example.happyhabits.feature_application.feature_toilet.data.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.happyhabits.core.data.model.Mapper.toDomain
import com.example.happyhabits.feature_application.feature_toilet.data.model.ToiletForm
import com.example.happyhabits.feature_application.feature_toilet.domain.model.Toilet

class ApiHelper(private val apiService: ApiService) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun addToiletHabit(toiletForm: ToiletForm) {
        try {
            val response = apiService.addToiletHabit(toiletForm)
            println("Here")
            if (response.isSuccessful) {
                println(response.message());
            }
        } catch (e: Exception) {
            println("Network error: ${e.localizedMessage}")
            throw e;
        }
    }
}