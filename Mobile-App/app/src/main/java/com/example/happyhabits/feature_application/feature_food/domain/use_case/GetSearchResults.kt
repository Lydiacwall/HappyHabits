package com.example.happyhabits.feature_application.feature_food.domain.use_case

import com.example.happyhabits.feature_application.feature_food.domain.repository.IFoodRepository

class GetSearchResults (
    private val repository: IFoodRepository
){
    suspend operator fun invoke(searchInput: String): List<String> {
        try{
            return repository.getAutoCompleteSuggestions(searchInput)
        } catch (e: Exception) {
            throw e;
        }
    }
}