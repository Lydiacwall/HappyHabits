package com.example.happyhabits.feature_application.feature_mood.presentation.mood_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.happyhabits.R
import com.example.happyhabits.feature_application.feature_medication.presentation.medication_screen.MedicationPageEvent
import com.example.happyhabits.feature_authentication.presentation.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MoodPageView(
    navController: NavController,
    viewModel : MoodViewModel = hiltViewModel()
){
    val font = FontFamily(
        Font(R.font.inter_medium, FontWeight.Medium)
    )
    val state by viewModel.state
    var moodLevel by remember {mutableStateOf(state.mood) }
    var moodColor by remember {
        mutableStateOf(Color.White) // Default color
    }
    var diary by remember{
        mutableStateOf(state.diary)
   }
    var borderGreen by remember {
        mutableStateOf(1.dp)
    }
    var borderYellow by remember {
        mutableStateOf(1.dp)
    }
    var borderRed by remember {
        mutableStateOf(1.dp)
    }
    var borderBlue by remember {
        mutableStateOf(1.dp)
    }

    val newNotification = true

    var sliderPosition by remember{
        mutableFloatStateOf(0f)
    }
    val maxLines = 5
    val colors = listOf(Color.White, Color(0xff64519A))

    var showMessage by remember{
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = colors)
            )
    ) {
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
                        .fillMaxHeight(0.13f))
                {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )
                    {
                        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center)
                        {
                            Box()
                            {
                                Row(modifier = Modifier.clickable {
                                    navController.navigate(com.example.happyhabits.feature_application.presentation.util.Screen.HomePageScreen.route)
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
                            Text(
                                text = "Medication",
                                color = Color.Black,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 20.dp)
                            )
                        }

                    }
                }
                Column (
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Box(
                            modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .background(Color.White)

                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, top = 5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.rating_purple),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .size(30.dp)

                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Rate your Mood",
                                    modifier = Modifier
                                        .padding(end = 30.dp),
                                    style = TextStyle(
                                        fontFamily = font,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 20.sp
                                    ),
                                    color = Color(0xff64519A)
                                )
                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = moodLevel, //+ sliderPosition.toInt().toString(),
                                    Modifier
                                        .background(moodColor)
                                        .padding(5.dp)
                                        .clip(RoundedCornerShape(20.dp))

                                )

                            }

                            Slider(
                                modifier = Modifier
                                    .padding(top = 20.dp, bottom = 10.dp, start = 10.dp, end = 10.dp),
                                value = sliderPosition,
                                onValueChange = { newPosition ->
                                    sliderPosition = newPosition
                                    when (newPosition.toInt()) {
                                        1 -> {
                                            moodLevel = "Terrible"
                                            viewModel.onEvent(MoodPageEvent.MoodChanged(moodLevel))
                                            moodColor = Color.Red
                                            borderGreen = 1.dp
                                            borderYellow = 1.dp
                                            borderRed = 2.dp
                                            borderBlue = 1.dp
                                        }

                                        2 -> {
                                            moodLevel = "Meh"
                                            viewModel.onEvent(MoodPageEvent.MoodChanged(moodLevel))
                                            moodColor = Color.Yellow
                                            borderGreen = 1.dp
                                            borderYellow = 2.dp
                                            borderRed = 1.dp
                                            borderBlue = 1.dp
                                        }

                                        3 -> {
                                            moodLevel = "Fine"
                                            viewModel.onEvent(MoodPageEvent.MoodChanged(moodLevel))
                                            moodColor = Color.Blue
                                            borderGreen = 1.dp
                                            borderYellow = 1.dp
                                            borderRed = 1.dp
                                            borderBlue = 2.dp
                                        }

                                        4 -> {
                                            moodLevel = "Great"
                                            viewModel.onEvent(MoodPageEvent.MoodChanged(moodLevel))
                                            moodColor = Color.Green
                                            borderGreen = 2.dp
                                            borderYellow = 1.dp
                                            borderRed = 1.dp
                                            borderBlue = 1.dp
                                        }

                                        else -> {
                                            moodLevel = " "
                                            viewModel.onEvent(MoodPageEvent.MoodChanged(moodLevel))
                                            moodColor = Color.White

                                        }
                                    }


                                },
                                steps = 3,
                                valueRange = 0f..4f,
                                onValueChangeFinished = {
                                    viewModel.onEvent(MoodPageEvent.MoodChanged(moodLevel))

                                }

                            )

                        }

                        Spacer(modifier = Modifier.height(0.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, start = 10.dp, end = 10.dp)

                        )
                        {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 20.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier.weight(1f)
                                )
                                {
                                    Box(
                                        modifier = Modifier
                                            .height(60.dp)
                                            .width(70.dp)
                                            .padding(end = 10.dp)
                                            .clip(RoundedCornerShape(15.dp))
                                            .background(Color.White)
                                            .border(borderRed, Color.Red, RoundedCornerShape(15.dp))

                                            .clickable {
                                                moodLevel = "Terrible"
                                                viewModel.onEvent(MoodPageEvent.MoodChanged(moodLevel))
                                                borderGreen = 1.dp
                                                borderYellow = 1.dp
                                                borderRed = 4.dp
                                                borderBlue = 1.dp
                                                sliderPosition = 1.0f
                                                moodColor = Color.Red

                                            }
                                    ) {

                                        Image(
                                            painter = painterResource(R.drawable.red_angry_face),
                                            contentDescription = "Terrible",
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(5.dp),
                                        )
                                    }
                                    Text(
                                        "Terrible",
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .padding(end = 9.dp, top = 5.dp)
                                            .align(Alignment.CenterHorizontally),
                                        fontSize = 16.sp
                                    )

                                }
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier.weight(1f)
                                )
                                {
                                    Box(
                                        modifier = Modifier
                                            .height(60.dp)
                                            .width(70.dp)
                                            .padding(end = 10.dp)
                                            .clip(RoundedCornerShape(15.dp))
                                            .background(Color.White)
                                            .border(
                                                borderYellow,
                                                Color.Yellow,
                                                RoundedCornerShape(15.dp)
                                            )

                                            .clickable {
                                                moodLevel = "Meh"
                                                viewModel.onEvent(MoodPageEvent.MoodChanged(moodLevel))
                                                borderGreen = 1.dp
                                                borderYellow = 2.dp
                                                borderRed = 1.dp
                                                borderBlue = 1.dp
                                                sliderPosition = 1.0f
                                                moodColor = Color.Yellow

                                            }
                                    ) {

                                        Image(
                                            painter = painterResource(R.drawable.yellow_poor_face),
                                            contentDescription = "Meh",
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(5.dp),
                                        )
                                    }
                                    Text(
                                        "Meh",
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .padding(end = 9.dp, top = 5.dp)
                                            .align(Alignment.CenterHorizontally),
                                        fontSize = 16.sp
                                    )

                                }
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier.weight(1f)
                                )
                                {
                                    Box(
                                        modifier = Modifier
                                            .height(60.dp)
                                            .width(70.dp)
                                            .padding(end = 10.dp)
                                            .clip(RoundedCornerShape(15.dp))
                                            .background(Color.White)
                                            .border(borderBlue, Color.Blue, RoundedCornerShape(15.dp))

                                            .clickable {
                                                moodLevel = "fine"
                                                sliderPosition = 3.0f
                                                borderGreen = 1.dp
                                                borderYellow = 4.dp
                                                borderRed = 1.dp
                                                borderBlue = 2.dp
                                                moodColor = Color.Blue
                                                viewModel.onEvent(MoodPageEvent.MoodChanged(moodLevel))
                                            }
                                    ) {

                                        Image(
                                            painter = painterResource(R.drawable.blue_okay_face),
                                            contentDescription = "Fine",
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(5.dp),
                                        )
                                    }
                                    Text(
                                        "Fine",
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .padding(end = 9.dp, top = 5.dp)
                                            .align(Alignment.CenterHorizontally),
                                        fontSize = 16.sp
                                    )
                                }
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier.weight(1f)
                                )
                                {
                                    Box(
                                        modifier = Modifier
                                            .height(60.dp)
                                            .width(70.dp)
                                            .padding(end = 10.dp)
                                            .clip(RoundedCornerShape(15.dp))
                                            .background(Color.White)
                                            .border(borderGreen, Color.Green, RoundedCornerShape(15.dp))

                                            .clickable {
                                                moodLevel = "Great"
                                                sliderPosition = 4.0f
                                                borderGreen = 4.dp
                                                borderYellow = 1.dp
                                                borderRed = 1.dp
                                                borderBlue = 1.dp
                                                moodColor = Color.Green
                                                viewModel.onEvent(MoodPageEvent.MoodChanged(moodLevel))
                                            }
                                    ) {

                                        Image(
                                            painter = painterResource(R.drawable.green_great_face),
                                            contentDescription = "Great",
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(5.dp),
                                        )
                                    }
                                    Text(
                                        "Great",
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .padding(end = 9.dp, top = 5.dp)
                                            .align(Alignment.CenterHorizontally),
                                        fontSize = 16.sp
                                    )
                                }


                            }

                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(start = 20.dp, end = 20.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color.White)

                        ) {
                            Column(

                            )
                            {

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 20.dp, top = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.diary_purple),
                                        contentDescription = null,
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier
                                            .size(30.dp)

                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Write today's thoughts ",
                                        modifier = Modifier
                                            .padding(end = 30.dp),
                                        style = TextStyle(
                                            fontFamily = font,
                                            fontWeight = FontWeight.Medium,
                                            fontSize = 20.sp
                                        ),
                                        color = Color(0xff64519A)
                                    )

                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(250.dp)
                                        .background(Color.White)
                                ) {
                                    Canvas(modifier = Modifier.fillMaxSize()) {

                                        val lineHeight =
                                            40.dp.toPx()
                                        var y = lineHeight
                                        while (y < size.height) {
                                            drawLine(
                                                color = Color.LightGray,
                                                start = Offset(0f, y),
                                                end = Offset(size.width, y),
                                                strokeWidth = 1.dp.toPx()
                                            )
                                            y += lineHeight
                                        }
                                    }
                                    TextField(
                                        value = diary,
                                        shape = RoundedCornerShape(20.dp),
                                        onValueChange = { newText ->
                                            val lines = newText.split("\n")
                                            diary = if (lines.size <= maxLines) {
                                                newText
                                            } else {
                                                lines.take(maxLines).joinToString("\n")
                                            }
                                            viewModel.onEvent(MoodPageEvent.DiaryChanged(diary))
                                        },

                                Image(
                                    painter = painterResource(R.drawable.red_angry_face),
                                    contentDescription = "Terrible",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(5.dp),
                                )
                            }
                            Text(
                                "Terrible",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(end = 9.dp, top = 5.dp)
                                    .align(Alignment.CenterHorizontally),
                                fontSize = 16.sp,
                                color = Color.Black
                            )

                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.weight(1f)
                        )
                        {
                            Box(
                                modifier = Modifier
                                    .height(60.dp)
                                    .width(70.dp)
                                    .padding(end = 10.dp)
                                    .clip(RoundedCornerShape(15.dp))
                                    .background(Color.White)
                                    .border(borderYellow, Color.Yellow, RoundedCornerShape(15.dp))

                                    .clickable {
                                        moodLevel = "Meh"
                                        viewModel.onEvent(MoodPageEvent.MoodChanged(moodLevel))
                                        borderGreen = 1.dp
                                        borderYellow = 2.dp
                                        borderRed = 1.dp
                                        borderBlue = 1.dp
                                        sliderPosition = 1.0f
                                        moodColor = Color.Yellow

                                    }
                            ) {

                                Image(
                                    painter = painterResource(R.drawable.yellow_poor_face),
                                    contentDescription = "Meh",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(5.dp),
                                )
                            }
                            Text(
                                "Meh",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(end = 9.dp, top = 5.dp)
                                    .align(Alignment.CenterHorizontally),
                                fontSize = 16.sp,
                                color = Color.Black
                            )

                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.weight(1f)
                        )
                        {
                            Box(
                                modifier = Modifier
                                    .height(60.dp)
                                    .width(70.dp)
                                    .padding(end = 10.dp)
                                    .clip(RoundedCornerShape(15.dp))
                                    .background(Color.White)
                                    .border(borderBlue, Color.Blue, RoundedCornerShape(15.dp))

                                    .clickable {
                                        moodLevel = "Fine"
                                        sliderPosition = 3.0f
                                        borderGreen = 1.dp
                                        borderYellow = 4.dp
                                        borderRed = 1.dp
                                        borderBlue = 2.dp
                                        moodColor = Color.Blue
                                        viewModel.onEvent(MoodPageEvent.MoodChanged(moodLevel))
                                    }
                            ) {

                                Image(
                                    painter = painterResource(R.drawable.blue_okay_face),
                                    contentDescription = "Fine",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(5.dp),
                                )
                            }
                            Text(
                                "Fine",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(end = 9.dp, top = 5.dp)
                                    .align(Alignment.CenterHorizontally),
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.weight(1f)
                        )
                        {
                            Box(
                                modifier = Modifier
                                    .height(60.dp)
                                    .width(70.dp)
                                    .padding(end = 10.dp)
                                    .clip(RoundedCornerShape(15.dp))
                                    .background(Color.White)
                                    .border(borderGreen, Color.Green, RoundedCornerShape(15.dp))

                                    .clickable {
                                        moodLevel = "Great"
                                        sliderPosition = 4.0f
                                        borderGreen = 4.dp
                                        borderYellow = 1.dp
                                        borderRed = 1.dp
                                        borderBlue = 1.dp
                                        moodColor = Color.Green
                                        viewModel.onEvent(MoodPageEvent.MoodChanged(moodLevel))
                                    }
                            ) {

                                Image(
                                    painter = painterResource(R.drawable.green_great_face),
                                    contentDescription = "Great",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(5.dp),

                                )
                            }
                            Text(
                                "Great",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(end = 9.dp, top = 5.dp)
                                    .align(Alignment.CenterHorizontally),
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        }


                    }

                }
                Spacer(modifier = Modifier.height(30.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 20.dp, end = 20.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)

                ) {
                    Column(

                    )
                    {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, top = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.diary_purple),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .size(30.dp)

                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Write today's thoughts ",
                                modifier = Modifier
                                    .padding(end = 30.dp),
                                style = TextStyle(
                                    fontFamily = font,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 20.sp
                                ),
                                color = Color(0xff64519A)
                            )

                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .background(Color.White)
                        ) {
                            Canvas(modifier = Modifier.fillMaxSize()) {

                                val lineHeight =
                                    40.dp.toPx()
                                var y = lineHeight
                                while (y < size.height) {
                                    drawLine(
                                        color=Color.LightGray,
                                        start = Offset(0f, y),
                                        end = Offset(size.width, y),
                                        strokeWidth =1.dp.toPx()
                                    )
                                }
                            }
                            TextField(
                                value = diary,
                                shape = RoundedCornerShape(20.dp),
                                onValueChange = { newText ->
                                    val lines = newText.split("\n")
                                    diary = if (lines.size <= maxLines) {
                                        newText
                                    } else {
                                        lines.take(maxLines).joinToString("\n")
                                    }
                                    viewModel.onEvent(MoodPageEvent.DiaryChanged(diary))
                                },

                                maxLines = 5,
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                textStyle = TextStyle(
                                    fontSize = 16.sp,
                                    lineHeight = 40.sp,
                                    color = Color.Black
                                ),
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        Spacer(modifier = Modifier.height(70.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Button(
                                onClick = {
                                    if (moodLevel != "") {
                                        viewModel.onEvent(
                                            MoodPageEvent.AddMoodLog(
                                                diary = diary,
                                                mood = moodLevel
                                            )
                                        )
                                        navController.navigate(Screen.HomePageScreen.route)
                                    } else {
                                        showMessage = true
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .align(Alignment.Center),
                            ) {
                                Text(
                                    "OK",
                                    fontSize = 20.sp
                                )

                            }

                        }
                        if (showMessage) {
                            AlertDialog(
                                onDismissRequest = { showMessage = false },
                                title = { Text("Please choose your mood!") },
                                confirmButton = {
                                    Button(
                                        onClick = { showMessage = false },

                                        ) {
                                        Text("OK")
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }


