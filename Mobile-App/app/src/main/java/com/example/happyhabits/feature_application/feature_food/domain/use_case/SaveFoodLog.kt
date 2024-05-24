package com.example.happyhabits.feature_application.feature_food.domain.use_case

import com.example.happyhabits.feature_application.feature_food.domain.model.SpecificFood
import com.example.happyhabits.feature_application.feature_food.domain.repository.IFoodRepository

class SaveFoodLog (
    private val repository: IFoodRepository
){
    suspend operator fun invoke(userId: String, date: String, foods: List<SpecificFood>): String? {
        try{
            return repository.saveLog(userId, date, foods)
        } catch (e: Exception) {
            throw e;
        }
    }
}