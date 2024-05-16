package com.example.happyhabits.feature_application.feature_medication

class Medicine (
    private var medId: String?,
    private var userId: String?,
    private var name: String,
    private var dosageQuantity: Float?,
    private var dosageUnitMeasurement: String?,
    private var startDay: String,
    private var endDay: String,
    private val timesShouldBeTakenToday: Int,
    private var timesTakenToday: Int?,
    private var notes: String?
){

    //Copy Constructor
//    constructor(medication: Medicine) : this(
//        medId = medication.medId,
//        userId = medication.userId,
//        name = medication.name,
//        dosageQuantity = medication.dosageQuantity,
//        dosageUnitMeasurement = medication.dosageUnitMeasurement,
//        startDay = medication.startDay,
//        endDay = medication.endDay,
//        timesTakenToday = medication.timesTakenToday,
//        timesShouldBeTakenToday = medication.timesShouldBeTakenToday,
//        notes = medication.notes
//    )

    // Getters

    fun getName(): String {
        return name
    }

    fun getUserId(): String?{
        return userId
    }


    fun getDosageQuantity(): Float? {
        return dosageQuantity
    }

    fun getDosageUnitMeasurement(): String? {
        return dosageUnitMeasurement
    }

    fun getStartDay(): String {
        return startDay
    }

    fun getEndDay(): String {
        return endDay
    }

    fun getTimesTakenToday(): Int? {
        return timesTakenToday
    }

    fun getTimesShouldBeTakenToday(): Int {
        return timesShouldBeTakenToday
    }

    fun getNotes(): String? {
        return notes
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

    fun setStartDay(startDay: String) {
        this.startDay = startDay
    }

    fun setEndDay(endDay: String) {
        this.endDay = endDay
    }
    fun setNotes(notes: String?) {
        this.notes = notes
    }

    fun updateTimesTakenToday(){
        this.timesTakenToday = this.timesTakenToday?.plus(1)
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

    fun isTaken(): Boolean {
        return timesTakenToday==timesShouldBeTakenToday
    }
    class InvalidMedicationException(message: String): Exception(message)

}

