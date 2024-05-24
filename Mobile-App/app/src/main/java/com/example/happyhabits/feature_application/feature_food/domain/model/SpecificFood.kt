package com.example.happyhabits.feature_application.feature_food.domain.model

import kotlinx.serialization.Serializable


@Serializable
class SpecificFood(
    private var foodId: String,
//    private var userId: Int=0,
    private var name: String,
    private var meal: String,
    private var calories: Float,
    private var protein: Float,
    private var fats: Float,
    private var carbs: Float,
    private var fiber: Float,
    private var quantity: Float,
    private var measurement: String,
) {

    // Copy Constructor
    constructor(food: SpecificFood) : this(
        food.foodId,
        food.name,
        food.meal,
        food.calories,
        food.protein,
        food.fats,
        food.carbs,
        food.fiber,
        food.quantity,
        food.measurement
    )
    fun makeSpecificFood(dataBaseFood: DataBaseFood, quantityGiven: Float, measurementGiven: Measurement, mealGiven:String): SpecificFood
    {
        foodId = ""
        name = dataBaseFood.getName()?:""
        meal = mealGiven
        calories = ((dataBaseFood.getCalories()?:0f)*quantityGiven*measurementGiven.weight)/100
        protein = ((dataBaseFood.getProtein()?:0f)*quantityGiven*measurementGiven.weight)/100
        fats = ((dataBaseFood.getFats()?:0f)*quantityGiven*measurementGiven.weight)/100
        carbs = ((dataBaseFood.getCarbs()?:0f)*quantityGiven*measurementGiven.weight)/100
        fiber = ((dataBaseFood.getFiber()?:0f)*quantityGiven*measurementGiven.weight)/100
        quantity = quantityGiven
        measurement = measurementGiven.label
        return this
    }
    override fun toString(): String {
        return "SpecificFood(name='$name', meal='$meal', calories=$calories, protein=$protein, " +
                "fats=$fats, carbs=$carbs, fiber=$fiber, quantity=$quantity, measurement='$measurement')"
    }

    // Getters and Setters
    fun getFoodId():String{
        return foodId
    }
    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getMeal(): String {
        return meal
    }

    fun setMeal(meal: String) {
        this.meal = meal
    }

    fun getFiber():Float{
        return fiber
    }

    fun getCalories(): Float {
        return calories
    }

    fun setCalories(calories: Float) {
        this.calories = calories
    }

    fun getProtein(): Float {
        return protein
    }

    fun setProtein(protein: Float) {
        this.protein = protein
    }

    fun getFats(): Float {
        return fats
    }

    fun setFats(fats: Float) {
        this.fats = fats
    }

    fun getCarbs(): Float {
        return carbs
    }

    fun setCarbs(carbs: Float) {
        this.carbs = carbs
    }

    fun getQuantity(): Float {
        return quantity
    }

    fun setQuantity(quantity: Float) {
        this.quantity = quantity
    }

    fun getMeasurement(): String {
        return measurement
    }

    fun setMeasurement(measurement: String) {
        this.measurement = measurement
    }

}