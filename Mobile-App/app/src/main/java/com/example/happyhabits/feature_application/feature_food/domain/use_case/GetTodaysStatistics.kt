package com.example.happyhabits.feature_application.feature_food.domain.use_case

import com.example.happyhabits.feature_application.feature_food.domain.model.Macros
import com.example.happyhabits.feature_application.feature_food.domain.repository.IFoodRepository

class GetTodaysStatistics(
private val repository: IFoodRepository
){
    suspend operator fun invoke(userId: String, date:String): List<Float> {
        try{
            return repository.getMacros(userId, date)
        } catch (e: Exception) {
            throw e;
        }
    }
}