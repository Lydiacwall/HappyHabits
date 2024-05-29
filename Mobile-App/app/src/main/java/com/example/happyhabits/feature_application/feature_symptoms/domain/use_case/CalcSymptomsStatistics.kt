package com.example.happyhabits.feature_application.feature_symptoms.domain.use_case

import com.example.happyhabits.feature_application.feature_symptoms.data.model.SymptomStatistics
import com.example.happyhabits.feature_application.feature_symptoms.data.repository.SymptomRepository

class CalcSymptomsStatistics (
    private val symptomRepository: SymptomRepository
){
    suspend  operator fun invoke(userId: String, monthNumber : String) : SymptomStatistics?{
        try{
            return symptomRepository.calcSymptomStatistics(userId,monthNumber)
        }
        catch (e : Exception){
            throw e;
        }
    }
}