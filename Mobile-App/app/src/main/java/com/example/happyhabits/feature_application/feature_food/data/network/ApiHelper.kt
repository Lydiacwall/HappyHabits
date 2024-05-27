package com.example.happyhabits.feature_application.feature_food.data.network

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.happyhabits.core.data.model.Mapper.toDomain
import com.example.happyhabits.feature_application.feature_food.data.model.FoodForm
import com.example.happyhabits.feature_application.feature_food.domain.model.DataBaseFood
import com.example.happyhabits.feature_application.feature_food.data.model.SpecificFoodForm
import com.example.happyhabits.feature_application.feature_food.domain.model.Macros
import com.example.happyhabits.feature_application.feature_food.domain.model.Measurement
import com.example.happyhabits.feature_application.feature_food.domain.model.SpecificFood

class ApiHelper(private val autoCompleteApiService: AutoCompleteApiService, private val parserApiService: ParserApiService, private val apiService: ApiService) {
    suspend fun getSearchSuggestion(searchInput:String): List<String>{
        val response = autoCompleteApiService.getAutoCompleteSuggestions(searchInput)
        return response.body()?: listOf("")
    }

    suspend fun getFoodInformation(foodNameInput:String): DataBaseFood?{
        val response = parserApiService.getFoodInformation(foodNameInput)
        val responseBody = response.body()
        return if (response.isSuccessful && responseBody!=null)
        {
            var databaseFood = DataBaseFood("", null, null, null, null, null, listOf(Measurement("",0f)))
            databaseFood = databaseFood.parseJson((response.body())?:"")?:  DataBaseFood("", null, null, null, null, null, emptyList())
            return databaseFood
        } else {
            null
        }
    }

    suspend fun removeFoodFromDataBase(foodId: String): String{
        val response = apiService.removeFood(id = foodId)
        return response.toString()
    }
    suspend fun saveLog(userId: String, date: String, foods: List<SpecificFood>): String{
        try {
            val newSpecificFoodFormList: MutableList<SpecificFoodForm> = mutableListOf()
            for (food in foods) {
                val newSpecificFoodForm = SpecificFoodForm(
                    name = food.getName(),
                    meal = food.getMeal(),
                    calories = food.getCalories(),
                    protein = food.getProtein(),
                    fats = food.getFats(),
                    fiber = food.getFiber(),
                    carbs = food.getCarbs(),
                    quantity = food.getQuantity(),
                    measurement = food.getMeasurement()
                )
                newSpecificFoodFormList.add(newSpecificFoodForm)
            }
            val newFoodForm = FoodForm(userId= userId, date=date, specificFoodsForms = newSpecificFoodFormList)
            val response = apiService.saveLog(newFoodForm)
            return response.toString()
        } catch (e: Exception) {
            throw e;
        }
    }

    suspend fun retrieveFoodList(userId: String, date:String): List<SpecificFood> {
        try {
            val response = apiService.retrieveFoodActivities(userId,date)
            val foodsList: MutableList<SpecificFood> = mutableListOf()
            if(response.isSuccessful)
            {
                val foodDtos = response.body()
                if (foodDtos != null) {
                    for(foodDto in foodDtos){
                        val newMed = foodDto.toDomain()
                        foodsList.add(newMed)
                    }
                }
            }
            return foodsList
        } catch (e: Exception) {
            throw e;
        }
    }
    suspend fun getMacros(userId: String, date:String): List<Float> {
        try {
            val response = apiService.getMacros(userId,date)
            if(response.isSuccessful)
            {
                val floatList = (response.body()?.toDomain())?:listOf(1f,1f,1f,1f)
                return floatList
            }
            return listOf(1f,1f,1f,1f)
        } catch (e: Exception) {
            throw e;
        }
    }

}

