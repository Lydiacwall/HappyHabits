package com.example.happyhabits.feature_toilet

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)

@Composable
fun SignUpDoctorView(
    navController: NavController,
    viewModel : ToiletViewModel = hiltViewModel()
) {
    val colors = listOf(Color.White, Color(0xff64519A))
    var pickedTime by remember {
        mutableStateOf(LocalTime.NOON)
    }
    val timeDialogState = rememberMaterialDialogState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = colors)
            )
    ){
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xffF5F5F5))
        ){ MaterialDialog(
            dialogState = timeDialogState,
            buttons = {
                positiveButton(text = "Ok") {
                }
                negativeButton(text = "Cancel")
            }
        ) {
            timepicker(
                initialTime = LocalTime.NOON,
                title = "Pick a time",
                timeRange = LocalTime.MIDNIGHT..LocalTime.NOON
            ) {
                pickedTime = it
            }
        }

        }
    }















}