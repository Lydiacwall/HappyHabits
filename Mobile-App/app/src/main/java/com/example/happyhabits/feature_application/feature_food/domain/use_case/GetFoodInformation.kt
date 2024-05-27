package com.example.happyhabits.feature_application.feature_food.domain.use_case

import com.example.happyhabits.feature_application.feature_food.domain.model.DataBaseFood
import com.example.happyhabits.feature_application.feature_food.domain.repository.IFoodRepository

class GetFoodInformation(
    private val repository: IFoodRepository
){
    suspend operator fun invoke(foodName: String): DataBaseFood? {
        try{
            return repository.getFoodInformation(foodName)
        } catch (e: Exception) {
            throw e;
        }
    }
}