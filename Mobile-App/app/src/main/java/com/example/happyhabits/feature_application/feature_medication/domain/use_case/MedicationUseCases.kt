package com.example.happyhabits.feature_application.feature_medication.domain.use_case

data class MedicationUseCases (
    val addMedication: AddMedication,
    val removeMedication: RemoveMedication,
    val logMedication: LogMedication,
    val retrieveMedications: RetrieveMedications
)