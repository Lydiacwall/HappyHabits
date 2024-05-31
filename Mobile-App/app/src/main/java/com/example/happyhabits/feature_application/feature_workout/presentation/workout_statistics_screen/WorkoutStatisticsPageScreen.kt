package com.example.happyhabits.feature_application.feature_workout.presentation.workout_statistics_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.happyhabits.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WorkoutStatisticsPageView(
    navController: NavController,
    viewModel: WorkoutStatisticsPageViewmodel = hiltViewModel()
){
    val context = LocalContext.current

    var newNotification = true

    val colors = listOf(Color(0xffF8F7FA), Color(0xffA687FF))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = colors)
            )
            .padding(0.dp)
    )
    {
        Column (
            modifier = Modifier
                .fillMaxSize()
        ){
            Row (
                Modifier
                    .fillMaxHeight(0.13f)) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
                {
                    Column(modifier = Modifier.fillMaxSize())
                    {
                        Box()
                        {
                            Row(modifier = Modifier.clickable {
                                viewModel.onEvent(
                                    WorkoutStatisticsPageEvent.ChangePage(
                                        "back",
                                        navController
                                    )
                                )
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
                            text = "Workout Statistics",
                            color = Color.Black,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                    }

                }
            }
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth().fillMaxSize(0.2f)
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .fillMaxWidth(0.7f)
                            .height(140.dp)
                            .shadow(6.dp)
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
                            )
                            .clickable {
                                viewModel.onEvent(
                                    WorkoutStatisticsPageEvent.ChangePage(
                                        "running",
                                        navController
                                    )
                                )
                            }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.4f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.running_workout),
                                    contentDescription = null, // Add appropriate content description
                                    modifier = Modifier
                                        .size(70.dp)
                                )
                            }
                            Box(
                                Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Running\nStatistics",
                                    color = Color.Black,
                                    fontSize = 27.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth().fillMaxSize(0.2f)
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .fillMaxWidth(0.7f)
                            .height(140.dp)
                            .shadow(6.dp)
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)
                            )
                            .clickable {
                                viewModel.onEvent(
                                    WorkoutStatisticsPageEvent.ChangePage(
                                        "weights",
                                        navController
                                    )
                                )
                            }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Box(
                                Modifier
                                    .fillMaxWidth(0.6f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Weights\nStatistics",
                                    color = Color.Black,
                                    fontSize = 27.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.weights_workout),
                                    contentDescription = null, // Add appropriate content description
                                    modifier = Modifier
                                        .size(70.dp)
                                )
                            }

                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth().fillMaxSize(0.2f)
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .fillMaxWidth(0.7f)
                            .height(140.dp)
                            .shadow(6.dp)
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
                            )
                            .clickable {
                                viewModel.onEvent(
                                    WorkoutStatisticsPageEvent.ChangePage(
                                        "biking",
                                        navController
                                    )
                                )
                            }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.4f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.biking_workout),
                                    contentDescription = null, // Add appropriate content description
                                    modifier = Modifier
                                        .size(70.dp)
                                )
                            }
                            Box(
                                Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Biking\nStatistics",
                                    color = Color.Black,
                                    fontSize = 27.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth().fillMaxSize(0.2f)
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .fillMaxWidth(0.7f)
                            .height(140.dp)
                            .shadow(6.dp)
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)
                            )
                            .clickable {
                                viewModel.onEvent(
                                    WorkoutStatisticsPageEvent.ChangePage(
                                        "yoga",
                                        navController
                                    )
                                )
                            }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Box(
                                Modifier
                                    .fillMaxWidth(0.6f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Yoga\nStatistics",
                                    color = Color.Black,
                                    fontSize = 27.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.yoga_workout),
                                    contentDescription = null, // Add appropriate content description
                                    modifier = Modifier
                                        .size(70.dp)
                                )
                            }

                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth().fillMaxSize(0.2f)
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .fillMaxWidth(0.7f)
                            .height(140.dp)
                            .shadow(6.dp)
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
                            )
                            .clickable {
                                viewModel.onEvent(
                                    WorkoutStatisticsPageEvent.ChangePage(
                                        "swimming",
                                        navController
                                    )
                                )
                            }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.4f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.swimming_workout),
                                    contentDescription = null, // Add appropriate content description
                                    modifier = Modifier
                                        .size(70.dp)
                                )
                            }
                            Box(
                                Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Swimming\nStatistics",
                                    color = Color.Black,
                                    fontSize = 27.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}