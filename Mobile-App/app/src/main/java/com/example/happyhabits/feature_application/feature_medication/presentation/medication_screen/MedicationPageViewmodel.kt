package com.example.happyhabits.feature_application.feature_medication.presentation.medication_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.feature_application.feature_medication.Medicine
import com.example.happyhabits.feature_application.feature_medication.model.MedicationTaken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MedicationPageViewmodel @Inject constructor(
): ViewModel() {
    private val _state = mutableStateOf(MedicationScreenState())
    val state: State<MedicationScreenState> = _state;
    fun onEvent(event: MedicationPageEvent) {

            when (event) {
                is MedicationPageEvent.ChangePage -> {
                    event.navController.navigate("home_page_screen")
                    /////////MEDICATIONS TAKEN TO BE SENT TO BACKEND
                }
                is MedicationPageEvent.MedicationTaken -> {
                    viewModelScope.launch {
                        val currentDate = SimpleDateFormat(
                            "MMM dd yyyy",
                            Locale.GERMANY
                        ).format(Date()) //Germany and Greece have similar timezones
                        val copyOfTakenMedication =
                            Medicine(_state.value.usersMedications[event.idOfMedication])
                        copyOfTakenMedication.updateTimesTakenToday()
                        if (copyOfTakenMedication.getTimesTakenToday() == copyOfTakenMedication.getTimesShouldBeTakenToday()) {
                            copyOfTakenMedication.setTaken(true)
                            copyOfTakenMedication.setLastDateSuccesfullyTaken(currentDate)
                        }
                        //Update Medications Taken
                        val newMedicationsTaken: MutableList<MedicationTaken> = mutableListOf()
                        for (medicationTaken in _state.value.medicationsTaken) {
                            val medicationNew = MedicationTaken(medicationTaken)
                            newMedicationsTaken.add(medicationNew)
                        }
                        val newMedicationTaken = MedicationTaken(copyOfTakenMedication.getName(),currentDate,copyOfTakenMedication.calculateSuccessPercentage())
                        newMedicationsTaken.removeAll { it.getName() == newMedicationTaken.getName() }
                        newMedicationsTaken.add(newMedicationTaken)
                        println("Medications taken before update:")
                        for (medicationTaken in _state.value.medicationsTaken) {
                            println(medicationTaken)
                        }
                        _state.value = _state.value.copy(medicationsTaken = newMedicationsTaken)
                        println("Medications taken after update:")
                        for (medicationTaken in newMedicationsTaken) {
                            println(medicationTaken)
                        }                        //Update user's medications
                        val newMedications: MutableList<Medicine> = mutableListOf()
                        var i = 0
                        for (medication in _state.value.usersMedications) {
                            if (medication.getName() != copyOfTakenMedication.getName()) {
                                val medicationToBeAdded = Medicine(medication)
                                newMedications.add(medicationToBeAdded)
                            } else {
                                newMedications.add(copyOfTakenMedication)
                            }
                        }
                        _state.value = _state.value.copy(usersMedications = newMedications)
                    }
                }
                is MedicationPageEvent.RemoveMedication -> {
                    viewModelScope.launch {
                        val copyOfTakenMedication = Medicine(_state.value.usersMedications[event.idOfMedication])
                        val newMedications: MutableList<Medicine> = mutableListOf()
                        var i = 0
                        for (medication in _state.value.usersMedications) {
                            if (medication.getName() != copyOfTakenMedication.getName()) {
                                val medicationToBeAdded = Medicine(medication)
                                newMedications.add(medicationToBeAdded)
                            }
                        }
                        _state.value = _state.value.copy(usersMedications = newMedications)
                        val newNumOfPages =if((_state.value.usersMedications.size)%9==0){(_state.value.usersMedications.size)/9}else{((_state.value.usersMedications.size)/9)+1}
                        _state.value = _state.value.copy(numOfPages = newNumOfPages)
                    }
                }
                is MedicationPageEvent.AddMedication -> {
                    viewModelScope.launch {
                        val newMedication = Medicine(
                            name = _state.value.nameToBeAdded,
                            dosageQuantity = _state.value.dosageQuantityToBeAdded,
                            dosageUnitMeasurement = _state.value.dosageUnitMeasurementToBeAdded,
                            startDay = _state.value.startDayToBeAdded,
                            endDay = _state.value.endDayToBeAdded,
                            successPerDay = _state.value.successPerDayToBeAdded,
                            timesTakenToday = _state.value.timesTakenTodayToBeAdded,
                            timesShouldBeTakenToday = _state.value.timesShouldBeTakenTodayToBeAdded,
                            taken = _state.value.takenToBeAdded,
                            notes = _state.value.notesToBeAdded
                        )
                        val newMedications: MutableList<Medicine> = mutableListOf()
                        for (medication in _state.value.usersMedications) {
                            val medicationNew = Medicine(medication)
                            newMedications.add(medicationNew)
                        }
                        newMedications.add(newMedication)
                        _state.value = _state.value.copy(usersMedications = newMedications)
                        val newNumOfPages = if ((_state.value.usersMedications.size) % 9 == 0) {
                            (_state.value.usersMedications.size) / 9
                        } else {
                            ((_state.value.usersMedications.size) / 9) + 1
                        }
                        _state.value = _state.value.copy(numOfPages = newNumOfPages)
                    }
                }
                is MedicationPageEvent.UpdatedAddMedication -> {
                    viewModelScope.launch {
                        when (event.typeChanged) {
                            "name" -> {
                                _state.value =
                                    _state.value.copy(nameToBeAdded = event.newValueString ?: "")
                            }

                            "dosage" -> {
                                _state.value =
                                    _state.value.copy(dosageQuantityToBeAdded = event.newValueFloat)
                            }

                            "unitMeasurement" -> {
                                _state.value =
                                    _state.value.copy(dosageUnitMeasurementToBeAdded = event.newValueString)
                            }

                            "startDate" -> {
                                _state.value =
                                    _state.value.copy(startDayToBeAdded = event.newValueString)
                            }

                            "endDate" -> {
                                _state.value =
                                    _state.value.copy(endDayToBeAdded = event.newValueString)
                            }

                            "perDay" -> {
                                _state.value =
                                    _state.value.copy(timesShouldBeTakenTodayToBeAdded = event.newValueInt)
                            }

                            "notes" -> {
                                _state.value =
                                    _state.value.copy(notesToBeAdded = event.newValueString)
                            }
                        }
                    }
                }
                is MedicationPageEvent.NextPage -> {
                    viewModelScope.launch {
                        if (_state.value.currentPage < _state.value.numOfPages - 1) {
                            val newCurrentPage = _state.value.currentPage + 1
                            _state.value = _state.value.copy(currentPage = newCurrentPage)
                        }
                    }
                }
                is MedicationPageEvent.PrevPage -> {
                    viewModelScope.launch {
                        if (_state.value.currentPage != 0) {
                            val newCurrentPage = _state.value.currentPage - 1
                            _state.value = _state.value.copy(currentPage = newCurrentPage)
                        }
                    }
                }
        }
    }
}
