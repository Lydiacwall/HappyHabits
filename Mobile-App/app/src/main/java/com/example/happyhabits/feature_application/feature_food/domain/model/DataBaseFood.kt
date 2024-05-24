package com.example.happyhabits.feature_application.feature_food.domain.model

import org.json.JSONArray
import org.json.JSONObject
class DataBaseFood(
    private var name: String?,
    private var calories: Float?,
    private var protein: Float?,
    private var fats: Float?,
    private var carbs: Float?,
    private var fiber: Float?,
    private var measurements: List<Measurement>?
) {
    constructor(original: DataBaseFood) : this(
        original.name,
        original.calories,
        original.protein,
        original.fats,
        original.carbs,
        original.fiber,
        original.measurements?.map { it.copy() }
    )
    fun parseJson(jsonString: String): DataBaseFood? {
        val jsonObject = JSONObject(jsonString)
        val hintsArray = jsonObject.optJSONArray("hints") ?: return null

        if (hintsArray.length() > 0) {
            val foodObject = hintsArray.getJSONObject(0).getJSONObject("food")
            val name = foodObject.optString("label", "")
            val nutrientsObject = foodObject.optJSONObject("nutrients") ?: JSONObject()
            val calories = nutrientsObject.optDouble("ENERC_KCAL", 0.0).toFloat()
            val protein = nutrientsObject.optDouble("PROCNT", 0.0).toFloat()
            val fats = nutrientsObject.optDouble("FAT", 0.0).toFloat()
            val carbs = nutrientsObject.optDouble("CHOCDF", 0.0).toFloat()
            val fiber = nutrientsObject.optDouble("FIBTG", 0.0).toFloat()

            val measuresArray = hintsArray.getJSONObject(0).optJSONArray("measures") ?: JSONArray()
            val measurementsList = mutableListOf<Measurement>()

            for (i in 0 until measuresArray.length()) {
                val measureObject = measuresArray.getJSONObject(i)
                val label = measureObject.optString("label", "")
                val weight = measureObject.optDouble("weight", 0.0).toFloat()
                measurementsList.add(Measurement(label, weight))
            }

            return DataBaseFood(name, calories, protein, fats, carbs, fiber, measurementsList)
        } else {
            return null
        }
    }
    // Getters
    fun getName(): String? = name
    fun getCalories(): Float? = calories
    fun getProtein(): Float? = protein
    fun getFats(): Float? = fats
    fun getCarbs(): Float? = carbs
    fun getFiber(): Float? = fiber
    fun getMeasurements(): List<Measurement>? = measurements

    // Setters
    fun setName(value: String?) {
        name = value
    }

    fun setCalories(value: Float?) {
        calories = value
    }

    fun setProtein(value: Float?) {
        protein = value
    }

    fun setFats(value: Float?) {
        fats = value
    }

    fun setCarbs(value: Float?) {
        carbs = value
    }

    fun setFiber(value: Float?) {
        fiber = value
    }

    fun setMeasurements(value: List<Measurement>?) {
        measurements = value
    }
    override fun toString(): String {
        val measurementsStr = measurements?.joinToString(", ") { it.toString() } ?: "None"
        return "DataBaseFood(name=$name, calories=$calories, protein=$protein, fats=$fats, carbs=$carbs, fiber=$fiber, measurements=$measurementsStr)"
    }
}