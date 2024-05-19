package com.example.happyhabits.feature_application.feature_statistics.presentation.sleep_statistics.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happyhabits.feature_application.presentation.util.Screen
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*


import androidx.compose.ui.geometry.Offset

import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.abs

@Preview
@Composable
fun SleepStatisticsPageView(
    //view model and navigator

){
    val colors = listOf(Color.White, Color(0xff64519A))
    val scrollState = rememberScrollState()
    val averageList = listOf(6.7f,10.2f,8.1f,8.5f,9f,5.6f,7f)
    val average = 7.8
    val difference = 0.2
    val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    var selectedPoint by remember { mutableStateOf<Pair<Int, Float>?>(null) }
    val maxDataPoint = 14

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = colors)
            )
            .padding(0.dp)
    )
    {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        )
        {
            Row(
                Modifier
                    .fillMaxHeight(0.2f)
            )
            {
                Box(
                    Modifier
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight()
                )
                {
                    Column(modifier = Modifier.fillMaxSize())
                    {
                        Box()
                        {
                            Row(modifier = Modifier.clickable {
                                //navController.navigate(Screen.HomePageScreen.route)
                            })
                            {
                                Text(
                                    text = "<",
                                    color = Color(0xFF544C4C),
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.padding(start = 20.dp, top = 24.dp)
                                )
                                Text(
                                    text = "Back",
                                    color = Color(0xFF544C4C),
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.padding(top = 31.dp)
                                )
                            }
                        }
                        Text(
                            text = "Symptoms",
                            color = Color.Black,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                    }
                }


            }
            //TODO : ADD UPLOAD ICON


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(20.dp))
            ) {
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        //.height(200.dp)
                ) {
                    val path = Path()
                    averageList.forEachIndexed { index, y ->
                        val x = (size.width / (averageList.size - 1)) * index
                        if (index == 0) {
                            path.moveTo(x, size.height - (y / 14 * size.height))
                        } else {
                            path.lineTo(x, size.height - (y / 14 * size.height))
                        }
                    }
                    drawPath(path, color = Color(0xFF64519A), style = Stroke(width = 4.dp.toPx()))

                    averageList.forEachIndexed { index, y ->
                        val x = (size.width / (averageList.size - 1)) * index
                        //val yOffset = size.height - (y / maxDataPoint.toFloat() * size.height)
                        drawCircle(
                            color = Color(0xFF64519A),
                            radius = 8.dp.toPx(),
                            center = Offset(x, size.height - (y / 14 * size.height))
                        )
                    }
                }
            }

            // Labels for x-axis
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                daysOfWeek.forEach {
                    Text(text = it, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}




