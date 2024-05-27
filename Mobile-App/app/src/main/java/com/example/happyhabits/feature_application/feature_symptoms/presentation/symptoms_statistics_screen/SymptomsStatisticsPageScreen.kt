package com.example.happyhabits.feature_application.feature_symptoms.presentation.symptoms_statistics_screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.happyhabits.R
import com.example.happyhabits.feature_application.presentation.util.Screen
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.delay
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.Year
import java.time.YearMonth

@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SymptomsStatisticsPageView(
    navController: NavController,
    viewModel : SymptomsStatisticsPageViewModel = hiltViewModel()
) {
    var fill by remember { mutableStateOf(false) }

    var selectedMonth by remember { mutableStateOf<YearMonth?>(null) }
        // when the screen will load
    LaunchedEffect(Unit) {
        delay(500)
        fill = true

    }
    val dialogState = rememberMaterialDialogState()
    val colors = listOf(Color.White, Color(0xff64519A))


    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(colors = colors))
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Row(
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.HomePageScreen.route)
                    }
                ) {
                    Text(
                        text = "<",
                        color = Color(0xFF544C4C),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Normal,
                    )
                    Text(
                        text = "Back",
                        color = Color(0xFF544C4C),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }

                Text(
                    text = "Last Month ",// TODO : CHANGE IT
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 20.dp, top = 10.dp)
                )

                Row {
                    Text(
                        text = "Top ",
                        fontSize = 30.sp,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif
                    )
                    Text(
                        text = "5",
                        color = Color(0xff64519A),
                        fontSize = 33.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif
                    )
                    Text(
                        text = " Symptoms",
                        fontSize = 30.sp,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif
                    )
                }

                Spacer(modifier = Modifier.fillMaxWidth(0.5f))
                Spacer(Modifier.height(10.dp))


                viewModel.getList().forEachIndexed { index, item ->
                    val percentage = 1f - (index * 0.2f)
                    CategoryBox(
                        fill = fill,
                        symptom = item,
                        image = viewModel.getImage(item),
                        percentage = percentage
                    )

                }
                Spacer(Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        thickness = 2.dp,
                        color = Color.LightGray
                    )
                    Text(
                        text = "or",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        style = TextStyle(
                            fontFamily = FontFamily.Default,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        thickness = 2.dp,
                        color = Color.LightGray
                    )
                }

                Spacer(Modifier.height(20.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { dialogState.show() },
                        colors = ButtonDefaults.buttonColors(Color(0XFFEBE8F4)),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .padding(8.dp)
                            .height(60.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.calender_logo_purple), // Replace with your calendar icon resource
                            contentDescription = "Calendar Icon",
                            tint = Color(0xff64519A),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Pick a Week",
                            color = Color(0xff64519A),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                selectedMonth?.let { month ->
                    Text(
                        text = "Selected Month: $month",
                        modifier = Modifier.padding(top = 16.dp),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }

        }
    }
    MonthPickerDialog(dialogState, selectedMonth= mutableStateOf(selectedMonth))
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MonthPickerDialog(dialogState: MaterialDialogState, selectedMonth: MutableState<YearMonth?>) {

    val currentMonth = YearMonth.now().monthValue
    val months = Month.values().take(currentMonth)
    var selectedOption by remember { mutableStateOf<Month?>(null) }

    MaterialDialog(dialogState = dialogState, buttons = {
        positiveButton("OK"){
            selectedOption?.let { month ->
                selectedMonth.value = YearMonth.of(Year.now().value, month)
            }
        }
        negativeButton("Cancel")
    }) {
        Column (
            modifier = Modifier
            .background(Color(0xFFE6E6FA)) // Soft purple background
            .padding(16.dp)
        ) {
            Text(
                text = "Select a Month",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xff64519A),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)

            )
            Divider(color = Color.Gray)
            LazyColumn {
                items(months) { month ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { selectedOption = month }
                    ) {
                        RadioButton(
                            selected = selectedOption == month,
                            onClick = { selectedOption = month },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xff64519A) // Dark purple bullet
                            )
                        )
                        Text(text = month.name, modifier = Modifier.padding(start = 8.dp), color = Color(0xff64519A))
                    }
                }
            }
        }
    }
}



    @Composable
    fun CategoryBox(fill: Boolean, symptom: String, image: Int, percentage: Float) {
        val fillFraction by animateFloatAsState(
            targetValue = if (fill) percentage else 0f,
            animationSpec = tween(durationMillis = 1000), label = ""
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color.White)
                    .clickable { /* Handle click if needed */ }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Red)
                        .align(Alignment.BottomStart)
                        .fillMaxHeight(fillFraction)

                )
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "Category Icon",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(10.dp)

                )


            }
            Spacer(Modifier.width(20.dp))
            Text(
                text = symptom,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
