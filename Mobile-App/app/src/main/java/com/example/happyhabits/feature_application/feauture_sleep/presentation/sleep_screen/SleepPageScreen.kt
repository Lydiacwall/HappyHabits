package com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material3.Button
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
import com.example.happyhabits.feature_application.feature_toilet.presentation.toilet_screen.ToiletPageEvent
import com.example.happyhabits.feature_authentication.presentation.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun SleepPageView(
//    navController: NavController,
//    viewModel : SleepPageViewModel = hiltViewModel()
){
    
    //TODO GET THE SLEEP GOAL AND ITS BY MINUTES
    var sleepgoal= 8
    var newNotification = true
    //val state by viewModel.state
//    var selectedtime by remember{
//        mutableStateOf(state.time)
//    }
    val colors = listOf(Color.White, Color(0xff64519A))
//    var quality by remember {
//        mutableStateOf(state.quality)
//    }
    var sliderPosition by remember{
        mutableStateOf(0f)
    }

    Box(
        modifier=Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = colors)
            )
            .padding(0.dp)
    ) {
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
                            text = "Toilet",
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
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                        .height(IntrinsicSize.Min)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // TODO : SLEEP ICON
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "TOTAL SLEEP TIME",
                                modifier = Modifier
                                    .padding(end = 30.dp)
                            )
                            Text(
                                text = (sliderPosition / 60).toInt()
                                    .toString() + "h " + (sliderPosition % 60).toInt()
                                    .toString() + "m"
                            )
                        }

                        //TODO : ADD MINUS AND ADD ICON ON THE SLIDER
                        Slider(
                            modifier = Modifier
                                .padding(top = 20.dp,bottom=30.dp),
                            value = sliderPosition,
                            onValueChange = { sliderPosition = it },
                            valueRange = 0f..1440f,
                            onValueChangeFinished = {// TODO: UPDATE STATE}
                            }

                        )
                        if (sleepgoal < sliderPosition) {
                            //TODO ICON
                            Text(
                                "You achieved your goal!",

                                modifier = Modifier
                                    .padding(bottom = 30.dp)
                            )
                        } else {
                            //TODO ICON
                            Text(
                                "You did not achieve your goal !",

                                modifier = Modifier
                                    .padding(bottom = 30.dp)
                            )
                        }

                    }

                }
                Spacer(modifier = Modifier.height(70.dp))
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
                                .border(1.dp, Color.Red)
                                .clickable { }//TODO
                        ) {
                            Text("Awful")
                            //TODO : ADD ICON
                        }
                        Box(
                            modifier = Modifier
                                .height(60.dp)
                                .width(70.dp)
                                .padding(end = 10.dp)
                                .border(1.dp, Color.Yellow)
                                .clickable { }//TODO
                        ) {
                            //TODO : ADD ICON
                            Text("Poor")
                        }
                        Box(
                            modifier = Modifier
                                .height(60.dp)
                                .width(70.dp)
                                .padding(end = 10.dp)
                                .border(1.dp, Color.Blue)
                                .clickable { }//TODO
                        )
                        {
                            Text("Ok")
                            //TODO : ADD ICON
                        }
                        Box(
                            modifier = Modifier
                                .height(60.dp)
                                .width(70.dp)
                                .padding(end = 10.dp)
                                .border(1.dp, Color.Magenta)
                                .clickable { }//TODO
                        ) {
                            Text("Good")
                            //TODO : ADD ICON
                        }
                        Box(
                            modifier = Modifier
                                .height(60.dp)
                                .width(70.dp)
                                .padding(end = 10.dp)
                                .border(1.dp, Color.Green)
                                .clickable { }//TODO

                        ) {
                            Text("Great")
                            //TODO : ADD ICON
                        }

                    }
                }
                Spacer(modifier = Modifier.height(70.dp))
                Button(
                    onClick = {
                        //                        viewModel.onEvent(
                        //                            ToiletPageEvent.AddToiletLog(
                        //                                time = selectedType,
                        //                                type = selectedType,
                        //                                notes = toiletNotes
                        //                            )
                        //                        )
                        //                        navController.navigate(Screen.HomePageScreen.route) // Μήπωσ εθ
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                ) {
                    Text(
                        "OK",
                        fontSize = 20.sp
                    )

                }

            }

        }

    }
}
