package com.example.happyhabits.feature_application.feature_mood.presentation.mood_screen

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.happyhabits.R
import com.example.happyhabits.feature_application.feature_toilet.presentation.toilet_screen.ToiletViewModel
import com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_screen.SleepPageEvent
import com.example.happyhabits.feature_authentication.presentation.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun MoodPageView(
//    navController: NavController,
//    viewModel : ToiletViewModel = hiltViewModel()
){
    var moodLevel by remember { mutableStateOf(" ") }
    var moodColor by remember {
        mutableStateOf(Color.Gray) // Default color
    }
    var border_green by remember {
        mutableStateOf(1.dp)
    }
    var border_yellow by remember {
        mutableStateOf(1.dp)
    }
    var border_red by remember {
        mutableStateOf(1.dp)
    }
    var border_orange by remember {
        mutableStateOf(1.dp)
    }
    // val state by viewModel.state
    var mood = ""//by remember{
    //mutableStateOf(state.mood)}
    var newNotification = true
    var sliderPosition by remember{
        mutableStateOf(0f)
    }

    val colors = listOf(Color.White, Color(0xff64519A))
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
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    Modifier
                        .fillMaxHeight(0.2f)
                ) {
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

                                }
                                )
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
                                text = "Mood",
                                color = Color.Black,
                                fontSize = 35.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 20.dp)
                            )
                        }

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(8.dp)
                    )
                    {
                        Box(
                            modifier = Modifier
                                .weight(0.9f)
                                .fillMaxHeight()
                                .padding(top = 45.dp)
                        )
                        {
                            Image(
                                painter = painterResource(R.drawable.barcode_icon),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .fillMaxSize()
                                    .size(50.dp)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .weight(0.9f)
                                .fillMaxHeight()
                                .padding(top = 53.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.notification_icon),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .fillMaxSize()
                                    .size(35.dp)
                            )
                            if (newNotification) {
                                Box(
                                    modifier = Modifier
                                        .size(15.dp)
                                        .background(
                                            Color(0xffff8c14),
                                            shape = MaterialTheme.shapes.small
                                        )
                                        .align(Alignment.TopEnd)
                                        .padding(
                                            end = 16.dp,
                                            top = 16.dp
                                        ) // Adjust padding as needed
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .weight(0.9f)
                                .fillMaxHeight()
                                .padding(top = 53.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.settings_icon),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(35.dp)
                            )
                        }
                    }

                }

            }

        }
        Spacer(modifier = Modifier.height(100.dp))
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 10.dp, end = 10.dp)
        ) {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Rate your Mood",
                        modifier = Modifier
                            .padding(end = 30.dp)
                    )

                    Text(
                        text = moodLevel + sliderPosition.toInt().toString(),
                        Modifier
                            .background(moodColor)
                            .padding(5.dp)
                            .clip(RoundedCornerShape(20.dp))

                    )

                }
                Slider(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 30.dp, start = 10.dp, end = 10.dp),
                    value = sliderPosition,
                    onValueChange = { newPosition ->
                        sliderPosition = newPosition
                        when (newPosition.toInt()) {
                            1 -> {
                                moodLevel = "Terrible "
                                moodColor = Color.Red
                                border_green= 1.dp
                                border_yellow =1.dp
                                border_red =4.dp
                                border_orange =1.dp
                            }

                            2 -> {
                                moodLevel = "Meh "
                                moodColor = Color(0xFFFFA500)
                                border_green= 1.dp
                                border_yellow =1.dp
                                border_red =1.dp
                                border_orange =4.dp
                            }

                            3 -> {
                                moodLevel = "Fine "
                                moodColor = Color.Yellow
                                border_green= 1.dp
                                border_yellow =4.dp
                                border_red =1.dp
                                border_orange =1.dp
                            }

                            4 -> {
                                moodLevel = "Great "
                                moodColor = Color.Green
                                border_green= 4.dp
                                border_yellow =1.dp
                                border_red =1.dp
                                border_orange =1.dp
                            }

                            else -> {
                                moodLevel = " "
                                moodColor = Color.Gray

                            }
                        }


                    },
                    steps = 3,
                    valueRange = 0f..4f,
                    onValueChangeFinished = {// TODO: SHOW THE EMOJIS

                    }

                )



                Spacer(modifier = Modifier.height(0.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        //.clip(RoundedCornerShape(20.dp))
                        //.background(Color.White)
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                        .height(70.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .height(60.dp)
                                .width(70.dp)
                                .padding(end = 10.dp)
                                .border(border_red, Color.Red)
                        .clickable {
                            moodLevel = "Terrible "
                            border_green= 1.dp
                            border_yellow =1.dp
                            border_red =4.dp
                            border_orange =1.dp
                            sliderPosition= 1.0f
                            moodColor = Color.Red
                            //viewModel.onEvent(SleepPageEvent.QualityChanged(quality))
                        }//TODO
                        ) {
                            Text("Terrible")
                            //TODO : ADD ICON
                        }
                        Box(
                            modifier = Modifier
                                .height(60.dp)
                                .width(70.dp)
                                .padding(end = 10.dp)
                                .border(border_orange, Color(0xFFFFA500))
                        .clickable {
                            moodLevel= "Meh "
                            border_green= 1.dp
                            border_yellow =1.dp
                            border_red =1.dp
                            border_orange =4.dp
                            sliderPosition= 2.0f
                            moodColor = Color(0xFFFFA500)
                            //viewModel.onEvent(SleepPageEvent.QualityChanged(quality))
                        }//TODO
                        ) {
                            //TODO : ADD ICON
                            Text("Meh")
                        }
                        Box(
                            modifier = Modifier
                                .height(60.dp)
                                .width(70.dp)
                                .padding(end = 10.dp)
                                .border(border_yellow, Color.Yellow)
                                .clickable {
                                    moodLevel = "Fine "
                                    sliderPosition= 3.0f
                                    border_green= 1.dp
                                    border_yellow =4.dp
                                    border_red =1.dp
                                    border_orange =1.dp
                                    moodColor= Color.Yellow
                                    //viewModel.onEvent(SleepPageEvent.QualityChanged(quality))
                                }//TODO
                        )
                        {
                            Text("Fine")
                            //TODO : ADD ICON
                        }
                        Box(
                            modifier = Modifier
                                .height(60.dp)
                                .width(70.dp)
                                .padding(end = 10.dp)
                                .border(border_green, Color.Green)
                                .clickable {
                                    moodLevel = "Great "
                                    sliderPosition= 4.0f
                                    border_green= 4.dp
                                    border_yellow =1.dp
                                    border_red =1.dp
                                    border_orange =1.dp
                                    moodColor= Color.Green
                                    //viewModel.onEvent(SleepPageEvent.QualityChanged(quality))
                                }//TODO
                        ) {
                            Text("Great")
                            //TODO : ADD ICON
                        }


                    }
                }
            }
        }

    }
}