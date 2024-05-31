package com.example.happyhabits.feature_application.feature_workout.data.network

import com.example.happyhabits.feature_application.feature_workout.data.model.ExerciseWorkoutForm
import com.example.happyhabits.feature_application.feature_workout.data.model.ExercisesWorkoutStatistics
import com.example.happyhabits.feature_application.feature_workout.data.model.FastActivityForm
import com.example.happyhabits.feature_application.feature_workout.data.model.FastActivityStatistics
import com.example.happyhabits.feature_application.feature_workout.data.model.WeightsForm
import com.example.happyhabits.feature_application.feature_workout.data.model.WeightsStatistics
import com.example.happyhabits.feature_application.feature_workout.domain.model.ExercisesWorkout
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("api/Workout/FastActivity/AddHabit")
    suspend fun addFastActivity(@Body fastActivityForm: FastActivityForm): Response<String?>

    @POST("api/Workout/Weights/AddHabit")
    suspend fun addWeightsActivity(@Body weightsForm: WeightsForm): Response<String>

    @POST("api/Workout/ExercisesWorkout/AddHabit")
    suspend fun addExerciseWorkoutActivity(@Body exercisesWorkoutForm: ExerciseWorkoutForm): Response<String>

    @GET("api/Workout/FastActivity/GetStatistics")
    suspend fun getFastWorkoutStatistics(@Query("userId") userId: String, @Query("month") month: Int, @Query("year") year: Int, @Query("type") type: String ): Response<FastActivityStatistics>
    @GET("api/Workout/ExercisesWorkout/GetStatistics")
    suspend fun getExercisesWorkoutStatistics(@Query("userId") userId: String, @Query("month") month: Int, @Query("year") year: Int, @Query("type") type: String ): Response<ExercisesWorkoutStatistics>
    @GET("api/Workout/Weights/GetStatistics")
    suspend fun getWeightsWorkoutStatistics(@Query("userId") userId: String, @Query("month") month: Int, @Query("year") year: Int): Response<WeightsStatistics>

}
