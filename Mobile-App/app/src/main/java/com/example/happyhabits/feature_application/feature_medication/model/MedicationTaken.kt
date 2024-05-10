package com.example.happyhabits.feature_application.feature_medication.model

class MedicationTaken (
    private var name: String,
    private var date: String,
    private var successOfDay: Float
){
    constructor(medicationTaken: MedicationTaken) : this(
        name = medicationTaken.name,
        date = medicationTaken.date,
        successOfDay = medicationTaken.successOfDay
    )
    // Getters
    fun getName(): String {
        return name
    }

    fun getDate(): String {
        return date
    }

    fun getSuccessOfDay(): Float {
        return successOfDay
    }

    // Setters
    fun setName(name: String) {
        this.name = name
    }

    fun setDate(date: String) {
        this.date = date
    }

    fun setSuccessOfDay(successOfDay: Float) {
        this.successOfDay = successOfDay
    }

    override fun toString(): String {
        return "MedicationTaken(name='$name', date='$date', successOfDay=$successOfDay)"
    }

}
