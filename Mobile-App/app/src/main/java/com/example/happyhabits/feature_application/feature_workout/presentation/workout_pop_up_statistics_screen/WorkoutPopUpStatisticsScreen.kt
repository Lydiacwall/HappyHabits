package com.example.happyhabits.feature_application.feature_workout.presentation.workout_pop_up_statistics_screen

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.happyhabits.feature_application.presentation.util.Screen
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WorkoutPopUpStatisticsView(
    navController: NavController,
    viewModel: WorkoutPopUpStatisticsViewmodel = hiltViewModel(),
){
    val state by viewModel.state
    val colors = listOf(Color(0xffF8F7FA), Color(0xffA687FF))
    var monthInput by remember{
        mutableStateOf("")
    }
    var yearInput by remember{
        mutableStateOf("")
    }
    var isExpandedMonth by remember {
        mutableStateOf(false)
    }
    var isExpandedYear by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = colors)
            )
    )
    {
        Column (
            modifier = Modifier
                .fillMaxSize()
        ){
            Row (
                Modifier
                    .fillMaxHeight(0.13f))
            {
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
                            Row(modifier = Modifier.clickable { navController.navigate(Screen.WorkoutStatisticsPageScreen.route) })
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
                            text = state.type+" Statistics",
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
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                )
                {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .background(Color.White, RoundedCornerShape(20.dp))
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp), contentAlignment = Alignment.Center
                        )
                        {
                            Text(
                                text = "Pick Date:",
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        ExposedDropdownMenuBox(
                            expanded = isExpandedMonth,
                            onExpandedChange = { isExpandedMonth = !isExpandedMonth })
                        {
                            TextField(
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth(0.9f),
                                value = monthInput,
                                shape = RoundedCornerShape(20.dp),
                                label = { Text(text = "Pick Month") },
                                onValueChange = {
                                },
                                readOnly = true,
                                colors = ExposedDropdownMenuDefaults.textFieldColors(
                                    cursorColor = Color.Gray,
                                    unfocusedLabelColor = Color.Gray,
                                    focusedLabelColor = Color.Gray,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    unfocusedContainerColor = Color.LightGray,
                                    focusedContainerColor = Color.LightGray,
                                    focusedTextColor = Color.Black
                                ),
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = isExpandedMonth
                                    )
                                })
                            ExposedDropdownMenu(
                                expanded = isExpandedMonth,
                                onDismissRequest = { isExpandedMonth = false },
                                modifier = Modifier
                                    .background(Color.White)
                                    .fillMaxWidth()
                            ) {
                                for(month in 0..<state.monthList.size) {
                                    DropdownMenuItem(
                                        onClick = {
                                            monthInput = state.monthList[month]
                                            viewModel.onEvent(WorkoutPopUpStatisticsEvent.UpdatedMonth(month+1))
                                            isExpandedMonth = false
                                        },
                                        text = { Text( state.monthList[month], fontSize = 20.sp) },
                                        modifier = Modifier
                                            .background(Color.White)
                                            .fillMaxWidth()
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        ExposedDropdownMenuBox(
                            expanded = isExpandedYear,
                            onExpandedChange = { isExpandedYear = !isExpandedYear })
                        {
                            TextField(
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth(0.9f),
                                value = yearInput,
                                shape = RoundedCornerShape(20.dp),
                                label = { Text(text = "Pick Year") },
                                onValueChange = {
                                },
                                readOnly = true,
                                colors = ExposedDropdownMenuDefaults.textFieldColors(
                                    cursorColor = Color.Gray,
                                    unfocusedLabelColor = Color.Gray,
                                    focusedLabelColor = Color.Gray,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    unfocusedContainerColor = Color.LightGray,
                                    focusedContainerColor = Color.LightGray,
                                    focusedTextColor = Color.Black
                                ),
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = isExpandedYear
                                    )
                                })
                            ExposedDropdownMenu(
                                expanded = isExpandedYear,
                                onDismissRequest = { isExpandedYear = false },
                                modifier = Modifier
                                    .background(Color.White)
                                    .fillMaxWidth()
                            ) {
                                state.yearList.forEach { year ->
                                    DropdownMenuItem(
                                        onClick = {
                                            yearInput = year.toString()
                                            viewModel.onEvent(WorkoutPopUpStatisticsEvent.UpdatedYear(year))
                                            isExpandedYear = false
                                        },
                                        text = { Text(year.toString(), fontSize = 20.sp) },
                                        modifier = Modifier
                                            .background(Color.White)
                                            .fillMaxWidth()
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Button(
                                    onClick = {
                                        if((state.monthSelected!=-1)&&(state.yearSelected!=-1))
                                        {
                                            viewModel.onEvent(WorkoutPopUpStatisticsEvent.DateSelected(""))
                                        }
                                    },
                                    shape = RoundedCornerShape(50),
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(60.dp),

                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFA687FF)
                                    ),
                                    elevation = ButtonDefaults.buttonElevation(
                                        defaultElevation = 5.dp,
                                        pressedElevation = 5.dp,
                                    )
                                ) {
                                    Text(
                                        text = "Set",
                                        color = Color.White,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                if(state.dateSelected){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .aspectRatio(1f) // Maintain a 1:1 aspect ratio
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .shadow(8.dp, CircleShape)
                                    .background(Color.White, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Average\nDuration\nPer Workout",
                                        textAlign = TextAlign.Center,
                                        color = Color(0xFF776A9C),
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Text(
                                        text = state.averageDurationPerWorkout?:"null",
                                        textAlign = TextAlign.Center,
                                        color = Color.Black,
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f) // Maintain a 1:1 aspect ratio
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .shadow(8.dp, CircleShape)
                                    .background(Color.White, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Total\nWorkouts\nThis Month",
                                        textAlign = TextAlign.Center,
                                        color = Color(0xFF776A9C),
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    Text(
                                        text = state.totalNumOfWorkouts.toString(),
                                        textAlign = TextAlign.Center,
                                        color = Color.Black,
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                    if(state.type=="Running"||state.type=="Biking")
                    {
                        Spacer(Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start=16.dp,end=16.dp,bottom=16.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .aspectRatio(1f) // Maintain a 1:1 aspect ratio
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .shadow(8.dp, CircleShape)
                                        .background(Color.White, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "Average\nKilometers\nPer Workout",
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF776A9C),
                                            fontSize = 17.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        val textAvgKms = String.format("%.2f", state.averageKilometersPerWorkout)
                                        Spacer(Modifier.height(5.dp))
                                        Text(
                                            text = "$textAvgKms km",
                                            textAlign = TextAlign.Center,
                                            color = Color.Black,
                                            fontSize = 25.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f) // Maintain a 1:1 aspect ratio
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .shadow(8.dp, CircleShape)
                                        .background(Color.White, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "Average\nElevation\nPer Workout",
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF776A9C),
                                            fontSize = 17.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Spacer(Modifier.height(5.dp))
                                        val textElevation = String.format("%.2f", state.averageElevationPerWorkout)
                                        Text(
                                            text = "$textElevation m",
                                            textAlign = TextAlign.Center,
                                            color = Color.Black,
                                            fontSize = 25.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .background(Color.White, RoundedCornerShape(20.dp))
                                .padding(20.dp),
                        contentAlignment = Alignment.Center
                        )
                        {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Total Kilometers:",
                                    textAlign = TextAlign.Center,
                                    color = Color(0xFF776A9C),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Spacer(Modifier.width(10.dp))
                                val textTotalKms = String.format("%.2f", state.totalNumOfKilometers)
                                Text(
                                    text = "$textTotalKms km",
                                    textAlign = TextAlign.Center,
                                    color = Color.Black,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    if(state.type=="Weights")
                    {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .aspectRatio(1f),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .shadow(8.dp, CircleShape)
                                        .background(Color.White, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = "Average\nLifted Kgs\nPer Workout",
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF776A9C),
                                            fontSize = 17.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Spacer(Modifier.height(5.dp))
                                        val textKgs = String.format("%.2f", state.averageKgsPerWorkout)
                                        Text(
                                            text = textKgs,
                                            textAlign = TextAlign.Center,
                                            color = Color.Black,
                                            fontSize = 25.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    }
                    if((state.type=="Yoga"||state.type=="Swimming"||state.type=="Weights")&&(state.topFiveExercise.isNotEmpty())) {
                        Spacer(Modifier.height(20.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .background(Color.White, RoundedCornerShape(20.dp))
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        )
                        {
                            Text(
                                text = "Average number of\nexercises per workout:",
                                textAlign = TextAlign.Center,
                                color = Color(0xFF776A9C),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(Modifier.height(10.dp))
                            Text(
                                text = state.averageNumOfExercisesPerWorkout.toString(),
                                textAlign = TextAlign.Center,
                                color = Color.Black,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .background(Color.White, RoundedCornerShape(20.dp))
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        )
                        {
                            Text(
                                text = "Month's top 5 exercises:",
                                textAlign = TextAlign.Center,
                                color = Color(0xFF776A9C),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Spacer(Modifier.height(10.dp))
                            for (i in 0 until 5) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            Color.LightGray,
                                            RoundedCornerShape(10.dp)
                                        )
                                        .padding(vertical = 7.dp, horizontal=20.dp),
                                    contentAlignment = Alignment.Center
                                )
                                {
                                    Text(
                                        text=state.topFiveExercise[i],
                                        textAlign = TextAlign.Center,
                                        color = Color.Black,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                }
                                Spacer(Modifier.height(5.dp))
                            }
                        }
                    }
                    Spacer(Modifier.height(20.dp))
                }
            }
        }
    }
}
