package com.example.happyhabits.feature_application.feature_symptoms.presentation.syptoms_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.core.data.model.Manager
import com.example.happyhabits.feature_application.feature_mood.presentation.mood_screen.MoodPageEvent
import com.example.happyhabits.feature_application.feature_symptoms.domain.use_case.SymptomUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class SymptomsPageViewModel @Inject constructor(
    private val symptomUseCases: SymptomUseCases
) : ViewModel(){

    private val hAndNList = listOf("Fever","Headaches","Dizziness","Vision Disturbances","Earaches","Nasal Congestion","Sore Throats","Swollen Lymph Nodes")
    private val pelvList = listOf("Urinary Urgency","Painful Urination","Pelvic Pain","Menstrual Cramps","Erectile Dysfunction")
    private val cAnBdList = listOf("Chest Pain","Shortness of Breath","Coughing","Heart Palpitations","Back Pain")
    private val abdomList = listOf("Abdominal Pain","Nausea","Vomiting","Diarrhea","Constipation","Bloating","Gas","Cramping")
    private val neuroList = listOf("Seizure","Loss of Consciousness","Memory Loss","Tremor","Involuntary movements","Balance Issues")
    private val limbsList = listOf("Joint Pain","Swelling","Muscle Pain","Weakness","Numbness","Tingling Sensation")
    private val skinList = listOf("Rash","Bruising","Itchiness","Dryness","Color Change")

    @RequiresApi(Build.VERSION_CODES.O)
    private val _state = mutableStateOf(SymptomsState())
    @RequiresApi(Build.VERSION_CODES.O)
    val state : State<SymptomsState> = _state

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: SymptomsPageEvent) {
        when (event) {
            is  SymptomsPageEvent.NotesChanged -> {
                _state.value = _state.value.copy(notes = event.notes)
            }
            is  SymptomsPageEvent.SymptomChanged-> {
                _state.value = _state.value.copy(symptom = event.symptom)
            }
            is SymptomsPageEvent.AddSymptomLog->{

                viewModelScope.launch {
                    Manager.currentUser?.let { symptomUseCases.addSymptomUseCases(userId = it.id, date = LocalDate.now(), name = event.symptom, notes = event.notes) }
                }
            }
            is SymptomsPageEvent.ShowErrorMessage->{
                _state.value =_state.value.copy(showErrorMessage = event.bool)
            }
        }

    }

    fun getHandList(): List<String> {
        return hAndNList
    }
    fun getPelvList(): List<String> {
        return pelvList
    }
    fun getChestList(): List<String> {
        return cAnBdList
    }
    fun getAbdList(): List<String> {
        return abdomList
    }
    fun getNeuroList(): List<String> {
        return neuroList
    }
    fun getLimbList(): List<String> {
        return limbsList
    }
    fun getSkinList(): List<String> {
        return skinList
    }

}

