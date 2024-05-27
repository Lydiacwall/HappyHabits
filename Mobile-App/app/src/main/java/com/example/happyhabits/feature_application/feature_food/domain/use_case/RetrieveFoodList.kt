package com.example.happyhabits.feature_application.feature_food.domain.use_case

import com.example.happyhabits.feature_application.feature_food.domain.model.SpecificFood
import com.example.happyhabits.feature_application.feature_food.domain.repository.IFoodRepository

class RetrieveFoodList(
    private val repository: IFoodRepository
){
    suspend operator fun invoke(userId: String, date:String): List<SpecificFood> {
        try {
            return repository.retrieveFoodList(userId, date)
        } catch (e: Exception) {
            throw e;
        }
    }
}