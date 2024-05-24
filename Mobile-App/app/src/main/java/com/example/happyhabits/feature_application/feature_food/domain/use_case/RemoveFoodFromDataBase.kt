package com.example.happyhabits.feature_application.feature_food.domain.use_case

import com.example.happyhabits.feature_application.feature_food.domain.repository.IFoodRepository

class RemoveFoodFromDataBase (
    private val repository: IFoodRepository
){
    suspend operator fun invoke(foodId: String): String? {
        try{
            return repository.removeFoodFromDataBase(foodId)
        } catch (e: Exception) {
            throw e;
        }
    }
}