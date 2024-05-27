package com.example.happyhabits.core.data.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.happyhabits.core.domain.model.User
import com.example.happyhabits.feature_application.feature_food.data.model.FoodDto
import com.example.happyhabits.feature_application.feature_food.data.model.FoodStatistics
import com.example.happyhabits.feature_application.feature_food.domain.model.Macros
import com.example.happyhabits.feature_application.feature_food.domain.model.SpecificFood
import com.example.happyhabits.feature_application.feature_medication.Medicine
import com.example.happyhabits.feature_application.feature_medication.data.model.MedicineDto
import com.example.happyhabits.feature_application.feature_toilet.data.model.ToiletDto
import com.example.happyhabits.feature_application.feature_toilet.domain.model.Toilet
import com.example.happyhabits.feature_authentication.data.model.UserDto
import java.time.LocalDate

object Mapper {
    fun UserDto.toDomain(): User {
        return User(
            id = this.id,
            firstName = this.firstName,
            lastName = this.lastName,
            password = this.password,
            email = this.email,
            type = this.type,
            birthDate = this.birthdate,
            streak = this.streak,
            sleepGoal = this.sleepGoal
        )
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun ToiletDto.toDomain(): Toilet {
        return Toilet(
            id = this.id,
            userId = this.userId,
            date = LocalDate.parse(this.date),
            type = this.type,
            time = this.time,
            notes = this.notes
        )
    }

    fun MedicineDto.toDomain(): Medicine {
        return Medicine(
            medId = this.id,
            userId = this.userId,
            name = this.name,
            dosageQuantity = this.dosageQuantity,
            dosageUnitMeasurement = this.dosageUnitMeasurement,
            startDay = this.startDay?: "MMM dd yyyy",
            endDay = this.endDay?: "MMM dd yyyy",
            timesShouldBeTakenToday= this.timesShouldBeTaken,
            timesTakenToday = this.timesTaken,
            notes= this.notes
        )
    }
    fun FoodDto.toDomain(): SpecificFood{
        return SpecificFood(
            foodId= this.id,
            name=this.name,
            meal=this.meal,
            calories=this.calories,
            protein=this.protein,
            fats=this.fats,
            carbs=this.carbs,
            fiber=this.fiber,
            quantity=this.quantity,
            measurement=this.measurement
        )
    }
    fun FoodStatistics.toDomain(): List<Float>{
        return listOf(this.proteinPercentage, this.carbsPercentage,this.fiberPercentage, this.fatsPercentage)
    }
}