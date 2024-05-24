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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
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

import com.example.happyhabits.R

@Preview
@Composable
fun SleepStatisticsPageView(
    //view model and navigator

) {
    val colors = listOf(Color.White, Color(0xff64519A))
    val scrollState = rememberScrollState()
    val averageList = listOf(6.7f, 10.2f, 8.1f, 8.5f, 9f, 5.6f, 7f)
    val average = 7.8
    val difference = 0.2
    val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    var selectedPoint by remember { mutableStateOf<Pair<Int, Float>?>(null) }
    val maxDataPoint = 14
    val customFontFamily = FontFamily(
        Font(R.font.inter_medium, FontWeight.Medium)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = androidx.compose.ui.graphics.Brush.verticalGradient(colors = colors)
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            //horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .clickable {
                        //navController.navigate(Screen.HomePageScreen.route)
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
                    .height(400.dp)
                    .background(Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
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
                                    //fontWeight = FontWeight.Bold
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
                                averageList.forEachIndexed { index, y ->
                                    val x = (size.width / (averageList.size - 1)) * index
                                    val yOffset =
                                        size.height - (y / maxDataPoint.toFloat() * size.height)
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

                                averageList.forEachIndexed { index, y ->
                                    val x = (size.width / (averageList.size - 1)) * index
                                    val yOffset =
                                        size.height - (y / maxDataPoint.toFloat() * size.height)
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
                        Row() {
                            Text(
                                text = "6.57",
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
                            //.padding(2.dp)
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
                            Row() {
                                Text(
                                    text = "2",//TODO:GET
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

                    // Average Quality Box
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(8.dp)
                            .background(Color.White, shape = RoundedCornerShape(10.dp))
                            .padding(16.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Average Quality:",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = "🙂", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
               modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
            {
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
                    onClick = { /* Handle date pick */ },
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
            }

        }
    }
}




