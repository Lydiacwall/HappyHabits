package com.example.happyhabits.feature_application.feature_symptoms.presentation.symptoms_statistics_screen

import androidx.lifecycle.ViewModel
import com.example.happyhabits.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SymptomsStatisticsPageViewModel @Inject constructor(

) : ViewModel() {

    private val symptoms = listOf("Chest Pain", "Fever", "Seizure", "Headaches", "Nausea")

    init {
        //TODO:GET ACTUAL LIST with default month
        val symptoms = listOf("Chest Pain", "Fever", "Seizure", "Headaches", "Nausea")
    }

    fun getList(): List<String> {
        return symptoms
    }

    fun getImage(name: String): Int {
        if (name == "Fever" || name == "Headaches" || name == "Dizziness" || name == "Vision Disturbances" || name == "Earaches"
            || name == "Nasal Congestion" || name == "Sore Throats" || name == "Swollen Lymph Nodes"
        ) {
            return R.drawable.head_neck
        }
        if (name == "Urinary Urgency" || name == "Painful Urination" || name == "Pelvic Pain" || name == "Menstrual Cramps" || name == "Erectile Dysfunction") {
            return R.drawable.pelvic_icon
        }
        if (name == "Chest Pain" || name == "Shortness of Breath" || name == "Coughing" || name == "Heart Palpitations" || name == "Back Pain") {
            return R.drawable.back_icon
        }
        if (name == "Abdominal Pain" || name == "Nausea" || name == "Vomiting" || name == "Diarrhea" || name == "Constipation" || name == "Bloating" || name == "Gas" || name == "Cramping") {
            return R.drawable.abdomen
        }
        if (name == "Seizure" || name == "Loss of Consciousness" || name == "Memory Loss" || name == "Tremor" || name == "Involuntary movements" || name == "Balance Issues") {
            return R.drawable.neuro_icon
        }
        if (name == "Joint Pain" || name == "Swelling" || name == "Muscle Pain" || name == "Weakness" || name == "Numbness" || name == "Tingling Sensation") {
            return R.drawable.limbs_icon
        }

        return R.drawable.skin_icon

    }
}




