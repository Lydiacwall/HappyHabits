package com.example.happyhabits.di

import com.example.happyhabits.feature_application.feature_food.data.network.AutoCompleteApiService
import com.example.happyhabits.feature_application.feature_food.data.network.ParserApiService
import com.example.happyhabits.feature_application.feature_food.data.repository.FoodRepositoryImpl
import com.example.happyhabits.feature_application.feature_food.domain.repository.IFoodRepository
import com.example.happyhabits.feature_application.feature_food.domain.use_case.FoodUseCases
import com.example.happyhabits.feature_application.feature_food.domain.use_case.GetSearchResults
import com.example.happyhabits.feature_application.feature_food.data.network.ApiHelper
import com.example.happyhabits.feature_application.feature_food.data.network.ApiService
import com.example.happyhabits.feature_application.feature_food.domain.use_case.GetFoodInformation
import com.example.happyhabits.feature_application.feature_food.domain.use_case.GetTodaysStatistics
import com.example.happyhabits.feature_application.feature_food.domain.use_case.RemoveFoodFromDataBase
import com.example.happyhabits.feature_application.feature_food.domain.use_case.RetrieveFoodList
import com.example.happyhabits.feature_application.feature_food.domain.use_case.SaveFoodLog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FoodModule {
    @Provides
    @Singleton
    fun provideApiService(@Named("BaseRetrofit") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideAutoCompleteService(@Named("autocompleteRetrofit") retrofit: Retrofit): AutoCompleteApiService
    {
        return retrofit.create(AutoCompleteApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideParserService(@Named("parserRetrofit") retrofit: Retrofit): ParserApiService
    {
        return retrofit.create(ParserApiService::class.java)
    }
    @Provides
    @Singleton
    fun provideFoodHelper( autoCompleteApiService: AutoCompleteApiService, parserApiService: ParserApiService, apiService: ApiService): ApiHelper {
        return ApiHelper(autoCompleteApiService, parserApiService, apiService);
    }
    @Provides
    @Singleton
    fun provideFoodRepository(foodApi: ApiHelper): IFoodRepository {
        return FoodRepositoryImpl(foodApi)
    }

    @Provides
    @Singleton
    fun provideFoodUseCases(repository: IFoodRepository): FoodUseCases
    {
        return FoodUseCases(
            getSearchResults = GetSearchResults(repository),
            getFoodInformation = GetFoodInformation(repository),
            removeFoodFromDataBase = RemoveFoodFromDataBase(repository),
            saveLog = SaveFoodLog(repository),
            retrieveFoodList = RetrieveFoodList(repository),
            getTodaysStatistics = GetTodaysStatistics(repository)
        )
    }
}