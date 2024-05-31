package com.example.happyhabits.feature_application.feature_mood.presentation.mood_statistics_screen

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

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
import com.example.happyhabits.feature_application.presentation.util.Screen
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
    val rows = 31
    val columns = 12
    val scrollState = rememberScrollState()

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
            //.padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)

            //horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
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


            Spacer(modifier=Modifier.height(10.dp))
            Box(
                modifier=Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                Column() {
                    Text(
                        text = "Mood of",
                        fontFamily = font,
                        fontSize = 60.sp
                    )
                    Row() {
                        Spacer(modifier= Modifier.fillMaxWidth(0.4f))
                        Text(
                            text = "2024",
                            textAlign = TextAlign.End,
                            fontSize=20.sp
                        )
                    }
                }
            }
            Spacer(modifier=Modifier.height(20.dp))
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent){
                    Table(rows = rows, columns = columns, dynamicState )//, boxItems = viewModel.getList())
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Table(rows: Int, columns: Int, dynamicState: MoodStatisticsState) {
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
                    val dateKey = "$day/$month/$year" // Construct the date key

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
