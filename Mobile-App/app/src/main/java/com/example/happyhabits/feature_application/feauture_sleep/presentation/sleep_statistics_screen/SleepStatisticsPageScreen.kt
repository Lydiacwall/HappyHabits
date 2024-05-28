package com.example.happyhabits.feature_application.feature_statistics.presentation.sleep_statistics.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import kotlin.math.pow
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.example.happyhabits.R
import com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_statistics_screen.SleepStatisticsPageEvent
import com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_statistics_screen.SleepStatisticsPageViewModel
import com.example.happyhabits.feature_application.presentation.util.Screen
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SleepStatisticsPageView(
    navController: NavController,
    viewModel: SleepStatisticsPageViewModel = hiltViewModel()
) {
    var selectedWeek by remember { mutableStateOf<Pair<LocalDate, LocalDate>?>(null) }
    val dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val dialogState = rememberMaterialDialogState()
    val colors = listOf(Color.White, Color(0xff64519A))
    val scrollState = rememberScrollState()
    val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

    val dynamicState = viewModel.state.value



    val maxDataPoint = 24
    val customFontFamily = FontFamily(
        Font(R.font.inter_medium, FontWeight.Medium)
    )
    val weekPickerDialogState = remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = androidx.compose.ui.graphics.Brush.verticalGradient(colors = colors)
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        ) {
            Row(
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.HomePageScreen.route)
                    }
            ) {
                Text(
                    text = "<",
                    color = Color(0xFF544C4C),
                    fontSize = 22.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Normal,
                )
                Text(
                    text = "Back",
                    color = Color(0xFF544C4C),
                    fontSize = 22.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Normal,
                )
            }

            Text(
                text = " Sleep ",
                fontSize = 32.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            // Chart Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(650.dp)
                    .background(Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier.weight(1f)
                    ) {
                        // Scale
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(end = 8.dp, top = 5.dp)
                        ) {
                            for (i in maxDataPoint downTo 0 step 1) {
                                Text(
                                    text = i.toString(),
                                    fontSize = 15.sp,
                                    color = Color(0xff837979),
                                )
                            }
                        }
                        // Chart
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp, end = 10.dp)
                        ) {
                            Canvas(modifier = Modifier.fillMaxSize()) {
                                val path = Path()
                                dynamicState.sleepDurations.forEachIndexed { index, y ->
                                    val x = (size.width / (dynamicState.sleepDurations.size - 1)) * index
                                    val correctTime= y.toInt()
                                    val time = (correctTime/60).toInt() + ((correctTime%60)*10.0.pow(-((correctTime%60)/10))).toInt()
                                    //val time = BigDecimal(((correctTime / 60) +((correctTime % 60))*0.1).toString()).setScale(2, RoundingMode.HALF_EVEN).toInt()
                                    val yOffset = size.height - (time / maxDataPoint.toFloat() * size.height)
                                    if (index == 0) {
                                        path.moveTo(x, yOffset)
                                    } else {
                                        path.lineTo(x, yOffset)
                                    }
                                }
                                drawPath(
                                    path,
                                    color = Color(0xFFA687FF),
                                    style = Stroke(width = 3.dp.toPx())
                                )

                                dynamicState.sleepDurations.forEachIndexed { index, y ->
                                    val x = (size.width / (dynamicState.sleepDurations.size - 1)) * index
                                    val correctTime= y.toInt()
                                    val time = (correctTime/60).toInt() + ((correctTime%60)*10.0.pow(-((correctTime%60)/10))).toInt()
                                    //val time = BigDecimal(((correctTime / 60) +((correctTime % 60)*0.1)).toString()).setScale(2, RoundingMode.HALF_EVEN).toInt()
                                    val yOffset = size.height - (time / maxDataPoint.toFloat() * size.height)
                                    drawCircle(
                                        color = Color(0xFFA687FF),
                                        radius = 6.dp.toPx(),
                                        center = Offset(x, yOffset)
                                    )
                                }
                            }
                        }
                    }

                    // Labels for x-axis
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(start = 15.dp, end = 0.dp)
                    ) {
                        daysOfWeek.forEach {
                            Text(
                                text = it,
                                fontSize = 17.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Additional information
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Daily Average Circle
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(Color.White, shape = CircleShape)
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "Daily Average",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 6.dp),
                            style = TextStyle(
                                fontFamily = customFontFamily,
                                fontSize = 16.sp,
                            )
                        )
                        Row {
                            Text(
                                text = dynamicState.average.toString(),
                                style = TextStyle(
                                    fontFamily = customFontFamily,
                                    fontSize = 24.sp,
                                    color = Color(0xff64519A)
                                )
                            )
                            Text(
                                text = "h",
                                style = TextStyle(
                                    fontFamily = customFontFamily,
                                    fontSize = 20.sp,
                                ),
                                modifier = Modifier.padding(top = 5.dp)
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier.weight(0.5f),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Average Difference Box
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .background(Color.White, shape = RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Average Difference",
                                fontSize = 16.sp,
                                style = TextStyle(
                                    fontFamily = FontFamily.Default,
                                )
                            )
                            Text(
                                text = "from Sleeping Goal",
                                fontSize = 16.sp,
                                style = TextStyle(
                                    fontFamily = FontFamily.Default,
                                )
                            )
                            Row {
                                Text(
                                    text = dynamicState.difference.toString(),
                                    style = TextStyle(
                                        fontFamily = FontFamily.Default
                                    ),
                                    fontSize = 24.sp,
                                    color = Color(0xff64519A)
                                )
                                Text(
                                    text = " h",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(top = 2.dp),
                                    style = TextStyle(
                                        fontFamily = FontFamily.Default
                                    )
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(10.dp))

                    // Average Quality Box
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .background(Color.White, shape = RoundedCornerShape(10.dp))
                            .padding(16.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Average Quality:",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )

                            var image = R.drawable.red_angry_face
                            if (dynamicState.quality == "good") {
                                image = R.drawable.purple_good_face
                            }
                            if (dynamicState.quality == "okay") {
                                image = R.drawable.blue_okay_face
                            }
                            if (dynamicState.quality == "great") {
                                image = R.drawable.green_great_face
                            }
                            if (dynamicState.quality == "poor") {
                                image = R.drawable.yellow_poor_face
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Image(
                                painter = painterResource(id = image),
                                contentDescription = "Emoji Icon",
                                Modifier.size(30.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
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
            Spacer(modifier = Modifier.height(10.dp))
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
                        .height(48.dp)
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

                selectedWeek?.let { week ->
                    Text(
                        text = "Selected Week: ${week.first.format(dateFormatter)} - ${week.second.format(dateFormatter)}",
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }

        MaterialDialog(dialogState = dialogState, buttons = {
            positiveButton("OK")
            negativeButton("Cancel")
        }) {
            // Custom title
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Select the Monday of the week you want ",
                    fontSize = 20.sp, // Adjust the font size as needed
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                datepicker(
                    initialDate = LocalDate.now().with(DayOfWeek.MONDAY),
                    allowedDateValidator = { it.dayOfWeek == DayOfWeek.MONDAY }
                ) { date ->
                    val selectedMonday = date.with(DayOfWeek.MONDAY)
                    val selectedSunday = selectedMonday.plusDays(6)

                    viewModel.onEvent(SleepStatisticsPageEvent.WeekhasChanged(selectedMonday.toString(),selectedSunday.toString()))
                    dialogState.hide()
                }
            }
        }
    }
}






