package com.example.happyhabits.feature_application.feature_medication

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Medication (
    private var name: String,
    private var dosageQuantity: Float?,
    private var dosageUnitMeasurement: String?,
    private var startDay: String?,
    private var endDay: String?,
    private val successPerDay: Float?,
    private val timesShouldBeTakenToday: Int?,
    private var timesTakenToday: Int?,
    private var taken: Boolean,
    private var notes: String?,
    private var lastDateSuccesfullyTaken: String = "MMM dd yyyy"
){

    //Copy Constructor
    constructor(medication: Medication) : this(
        name = medication.name,
        dosageQuantity = medication.dosageQuantity,
        dosageUnitMeasurement = medication.dosageUnitMeasurement,
        startDay = medication.startDay,
        endDay = medication.endDay,
        successPerDay = medication.successPerDay,
        timesTakenToday = medication.timesTakenToday,
        timesShouldBeTakenToday = medication.timesShouldBeTakenToday,
        taken = medication.taken,
        notes = medication.notes,
        lastDateSuccesfullyTaken = medication.lastDateSuccesfullyTaken
    )

    // Getters

    fun getName(): String {
        return name
    }


    fun getDosageQuantity(): Float? {
        return dosageQuantity
    }

    fun getDosageUnitMeasurement(): String? {
        return dosageUnitMeasurement
    }

    fun getStartDay(): String? {
        return startDay
    }

    fun getEndDay(): String? {
        return endDay
    }

    fun getSuccessPerDay(): Float? {
        return successPerDay
    }

    fun getTimesTakenToday(): Int? {
        return timesTakenToday
    }

    fun getTimesShouldBeTakenToday(): Int? {
        return timesShouldBeTakenToday
    }

    fun isTaken(): Boolean {
        return taken
    }

    fun getNotes(): String? {
        return notes
    }

    fun getLastDateSuccesfullyTaken(): String {
        return lastDateSuccesfullyTaken
    }

    // Setters

    fun setName(name: String) {
        this.name = name
    }

    fun setDosageQuantity(dosageQuantity: Float?) {
        this.dosageQuantity = dosageQuantity
    }

    fun setDosageUnitMeasurement(dosageUnitMeasurement: String?) {
        this.dosageUnitMeasurement = dosageUnitMeasurement
    }

    fun setStartDay(startDay: String?) {
        this.startDay = startDay
    }

    fun setEndDay(endDay: String?) {
        this.endDay = endDay
    }

    fun setTaken(isTakenOrNot: Boolean) {
        this.taken = isTakenOrNot
    }

    fun setNotes(notes: String?) {
        this.notes = notes
    }

    fun updateTimesTakenToday(){
        this.timesTakenToday = this.timesTakenToday?.plus(1)
    }
    fun setLastDateSuccesfullyTaken(date: String) {
        this.lastDateSuccesfullyTaken = date
    }
    fun calculateSuccessPercentage(): Float {
        val timesShouldBeTakenToday = timesShouldBeTakenToday ?: return -1f
        val timesTakenToday = timesTakenToday ?: return -1f

        return if (timesShouldBeTakenToday > 0) {
            (timesTakenToday.toFloat() / timesShouldBeTakenToday.toFloat()) * 100
        } else {
            -1f
        }
    }
}

