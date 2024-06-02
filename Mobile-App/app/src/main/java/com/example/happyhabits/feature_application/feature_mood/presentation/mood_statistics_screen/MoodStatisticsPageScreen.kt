package com.example.happyhabits.feature_application.feature_mood.presentation.mood_statistics_screen

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.happyhabits.R
import com.example.happyhabits.feature_application.feature_food.presentation.statistics_food.FoodStatisticsEvent
import com.example.happyhabits.feature_application.presentation.util.Screen
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale

data class BoxItem(val color: Color, val date: Any)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoodStatisticsPageView(
    navController: NavController,
    viewModel: MoodStatisticsPageViewModel = hiltViewModel()
){
    val colors = listOf(Color.White, Color(0xff64519A))
    val state by viewModel.state
    val rows = 31
    val columns = 12
    val scrollState = rememberScrollState()
    val sendStatistics = rememberMaterialDialogState()
    val font = FontFamily(
        Font(R.font.lobster_normal, FontWeight.Bold)
    )

    val dynamicState = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = androidx.compose.ui.graphics.Brush.verticalGradient(colors = colors)
            )
    ) {
        Column(
                modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                Modifier
                    .fillMaxHeight(0.13f)
            )
            {
                Box(
                    Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight()
                )
                {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(top=10.dp),
                        verticalArrangement = Arrangement.Top)
                    {
                        Row(
                            modifier = Modifier
                                .clickable { navController.navigate(Screen.StatisticsPageScreen.route)
                                })
                        {
                            Text(
                                text = "<",
                                color = Color(0xFF544C4C),
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(start = 20.dp, top = 15.dp)
                            )
                            Text(
                                text = "Back",
                                color = Color(0xFF544C4C),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(top = 22.dp)
                            )
                        }
                    }
                }
                Box(
                    Modifier
                        .fillMaxWidth(1f)
                        .fillMaxHeight()
                        .padding(top=15.dp),
                    contentAlignment = Alignment.Center
                )
                {
                    Box(
                        modifier = Modifier
                            .size(45.dp)
                            .background(Color.LightGray, shape = CircleShape)
                            .clickable(onClick = {
                                sendStatistics.show()
                            }),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.share_icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            )
            {
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column() {
                        Text(
                            text = "Mood of",
                            fontFamily = font,
                            fontSize = 60.sp
                        )
                        Row() {
                            Spacer(modifier = Modifier.fillMaxWidth(0.4f))
                            Text(
                                text = LocalDate.now().year.toString(),
                                textAlign = TextAlign.End,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                MaterialTheme {
                    Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent) {
                        Table(
                            rows = rows,
                            columns = columns,
                            dynamicState
                        )//, boxItems = viewModel.getList())
                    }
                }
            }
        }
    }
    MaterialDialog(
        dialogState = sendStatistics,
        shape = RoundedCornerShape(20.dp)
    ) {
        val friendsList = state.clientsList
        var selectedItemIndex by remember { mutableStateOf(-1) }
        var sendButtonBackground by remember { mutableStateOf(Color.LightGray) }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
            {
                Text(
                    text = "Chose Receiver",
                    color = Color.Black,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            LazyColumn (Modifier.height(200.dp)){
                items(friendsList) { friend ->
                    val index = friendsList.indexOfFirst { it.friendUsername == friend.friendUsername }
                    val borderColor = if (index == selectedItemIndex) Color(0xFF776A9C) else Color.LightGray

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray, RoundedCornerShape(10.dp))
                        .border(5.dp, borderColor, RoundedCornerShape(10.dp))
                        .padding(top = 3.dp, bottom = 3.dp)
                        .clickable(onClick = {
                            selectedItemIndex = index
                            sendButtonBackground = Color(0xFF776A9C)
                        }),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Text(
                            text = friend.friendUsername,
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center)
            {
                Button(
                    onClick = {sendStatistics.hide()},
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .height(60.dp)
                        .weight(0.9f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 5.dp,
                        pressedElevation = 5.dp,
                    )
                ) {
                    Text(
                        text = "Cancel",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(
                    onClick = {
                        if(selectedItemIndex!=-1)
                        {
//                            viewModel.onEvent(FoodStatisticsEvent.SendStatistics(selectedItemIndex))
                            sendStatistics.hide()
                        }},
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .height(60.dp)
                        .weight(0.9f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = sendButtonBackground
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 5.dp,
                        pressedElevation = 5.dp,
                    )
                ) {
                    Text(
                        text = "Send",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Table(rows: Int, columns: Int, dynamicState: MoodStatisticsState) {
    println("Mood List: ${dynamicState.moodList}")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 20.dp, bottom = 20.dp)
    ) {
        // Header Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(26.dp)) // Empty cell for the top-left corner
            val months = listOf("J", "F", "M", "A", "M", "J", "J", "A", "S", "O", "N", "D")
            for (month in months) {
                Box(
                    modifier = Modifier
                        .size(26.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(month, fontSize = 23.sp)
                }
            }
        }

        for (row in 1..rows) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Number Column
                Box(
                    modifier = Modifier
                        .size(25.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$row",
                        style = TextStyle(fontSize = 14.sp, color = Color.Black)
                    )
                }

                for (column in 1..columns) {
                    val day = row
                    val month = column
                    val year = LocalDate.now().year
                    val dateKey = "$month/$day/$year" // Construct the date key

                    val mood = dynamicState.moodList[dateKey]

                    val color = when (mood) {
                        "Terrible" -> Color.Red
                        "Meh" -> Color.Yellow
                        "Fine" -> Color.Blue
                        "Great" -> Color.Green
                        else -> Color.White
                    }

                    TableCell(row = row, column = column, color = color)
                }
            }
        }
    }
}


@Composable
fun TableCell(row: Int, column: Int, color: Color) {
    Box(
        modifier = Modifier
            .size(26.dp)
            .border(1.dp, Color(0xff7F7F7F)) // Internal border
            .background(color),
            //.padding(8.dp),
        contentAlignment = Alignment.Center
    ) {}
}
