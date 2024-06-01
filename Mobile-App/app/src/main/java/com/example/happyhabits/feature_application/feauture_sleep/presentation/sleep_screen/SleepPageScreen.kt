package com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_screen

import android.os.Build
import android.util.Log
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.happyhabits.R
import com.example.happyhabits.feature_application.feature_toilet.presentation.toilet_screen.ToiletPageEvent
import com.example.happyhabits.feature_authentication.presentation.util.Screen
import com.vanpra.composematerialdialogs.MaterialDialog
import kotlinx.serialization.json.JsonNull.content

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SleepPageView(
    navController: NavController,
    viewModel : SleepPageViewModel = hiltViewModel()
){
    val font = FontFamily(
        Font(R.font.inter_medium, FontWeight.Medium)
    )
    //TODO GET THE SLEEP GOAL AND ITS BY MINUTES
    var newsleepgoal by remember{
        mutableStateOf("")
    }
    val state by viewModel.state
    val dynamicState = viewModel.state.value
    var sleepgoal by remember {
        mutableStateOf(dynamicState.sleepgoal)
    }
    var newNotification = true
    var showPopUp by remember { mutableStateOf(false) }
    var showNotification by remember {
        mutableStateOf(false)
    }
    var showSleepMessage by remember {
        mutableStateOf(false)
    }

    var selectedtime by remember{
        mutableStateOf(state.time)
    }
    val colors = listOf(Color.White, Color(0xff64519A))
    var quality by remember {
        mutableStateOf(state.quality)
    }
    var sliderPosition by remember{
        mutableStateOf(0f)
    }
    var border_red by remember{
        mutableStateOf(0.dp)
    }
    var border_yellow by remember{
        mutableStateOf(0.dp)
    }
    var border_blue by remember{
        mutableStateOf(0.dp)
    }
    var border_purple by remember{
        mutableStateOf(0.dp)
    }
    var border_green by remember{
        mutableStateOf(0.dp)
    }

    Box(
        modifier= Modifier
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
                                navController.navigate(Screen.HomePageScreen.route)
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
                            text = "Sleep",
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
                                    )
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
                        .padding(top = 10.dp, start = 5.dp, end = 10.dp)
                        .height(IntrinsicSize.Min)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                        ) {
                            Box(
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.sleep_icon_purple),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier

                                        .size(30.dp)

                                )
                            }
                            Spacer(modifier=Modifier.width(8.dp))
                            Text(
                                text = "Total Sleep Time : ",
                                style = TextStyle(
                                    fontFamily = font,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 20.sp
                                ),

                            )
                            Text(
                                text=(sliderPosition/60).toInt().toString(),
                                fontSize = 30.sp,
                                color= Color(0xff64519A),
                                fontWeight= FontWeight.Bold
                            )
                            Text(
                                text="h ",
                                fontSize=20.sp,
                                modifier= Modifier.padding(top=7.dp)
                            )
                            Text(text=(sliderPosition % 60).toInt().toString(),
                                fontSize = 30.sp,
                                color= Color(0xff64519A),
                                fontWeight= FontWeight.Bold)
                            Text(text="m",
                                fontSize=20.sp,
                                modifier= Modifier.padding(top=7.dp)
                            )
                        }
                       Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                           ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .padding(top = 15.dp, end = 3.dp)
                                    .clickable {
                                        sliderPosition -= 1
                                        selectedtime = sliderPosition.toString()
                                        viewModel.onEvent(SleepPageEvent.TimeChanged(sliderPosition.toString()))
                                    }
                            ){
                            Image(
                                painter = painterResource(R.drawable.minus_purple),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .size(20.dp)

                            )

                            }
                           Slider(
                               modifier = Modifier
                                   .padding(top = 20.dp, bottom = 5.dp)
                                   .fillMaxWidth(0.95f),
                               value = sliderPosition,
                               onValueChange = {
                                   sliderPosition = it
                                   selectedtime = sliderPosition.toString()
                                   viewModel.onEvent(SleepPageEvent.TimeChanged(sliderPosition.toString()))
                               },
                               valueRange = 0f..1440f,
                               onValueChangeFinished = {
                                   viewModel.onEvent(SleepPageEvent.TimeChanged(sliderPosition.toString()))
                               },
                               thumb = {

                                   Image(painterResource(
                                       id = R.drawable.plus_minus_drawed,

                                   ),
                                       "contentDescription",
                                   modifier = Modifier
                                       .size(40.dp)
                                   )
                               }

                           )
                           Box(
                               contentAlignment = Alignment.Center,
                               modifier = Modifier
                                   .padding(top = 14.dp)
                                   .clickable {
                                       sliderPosition += 1
                                       selectedtime = sliderPosition.toString()
                                       viewModel.onEvent(SleepPageEvent.TimeChanged(sliderPosition.toString()))
                                   }
                           ) {
                               Image(
                                   painter = painterResource(R.drawable.plus_purple),
                                   contentDescription = null,
                                   modifier = Modifier
                                       .size(30.dp),


                               )
                           }
                       }
                            if (dynamicState.sleepgoal.toInt() < sliderPosition.toInt()/60) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center)
                                {
                                    Image(
                                        painter = painterResource(R.drawable.purple_heart),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(30.dp),
                                    )
                                    Text(
                                        "Over your Goal > $sleepgoal  hours",
                                        color= Color(0xff64519A),
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 17.sp

                                    )
                                }
                            } else {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center

                                )
                                {
                                    Image(
                                        painter = painterResource(R.drawable.warning_purple_icon),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(30.dp)
                                            .padding(end = 5.dp),
                                    )
                                    Text(
                                        "Under Your Goal < ${dynamicState.sleepgoal} hours",
                                        color= Color(0xff64519A),
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 17.sp

                                    )
                                 }
                            }

                    }

                }
                Spacer(modifier = Modifier.height(70.dp))
                Box(
                    modifier = Modifier

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
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
                                    .border(border_red, Color.Red, RoundedCornerShape(15.dp))

                                    .clickable {
                                        quality = "awful"
                                        border_red = 2.dp
                                        border_blue = 0.dp
                                        border_green = 0.dp
                                        border_purple = 0.dp
                                        border_yellow = 0.dp
                                        viewModel.onEvent(SleepPageEvent.QualityChanged(quality))
                                    }
                            ) {

                                Image(painter = painterResource(R.drawable.red_angry_face),
                                    contentDescription = "Awful",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(5.dp),)
                            }
                            Text("Awful",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(end = 9.dp, top = 5.dp)
                                    .align(Alignment.CenterHorizontally),
                                fontSize = 16.sp)
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
                                    .border(border_yellow, Color.Yellow, RoundedCornerShape(15.dp))

                                    .clickable {
                                        quality = "poor"
                                        border_red = 0.dp
                                        border_blue = 0.dp
                                        border_green = 0.dp
                                        border_purple = 0.dp
                                        border_yellow = 2.dp
                                        viewModel.onEvent(SleepPageEvent.QualityChanged(quality))
                                    }
                            ) {

                                Image(painter = painterResource(R.drawable.yellow_poor_face),
                                    contentDescription = "Poor",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(5.dp),)
                            }
                            Text("Poor",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(end = 9.dp, top = 5.dp)
                                    .align(Alignment.CenterHorizontally),
                                fontSize = 16.sp)
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
                                    .border(border_blue, Color.Blue, RoundedCornerShape(15.dp))

                                    .clickable {
                                        quality = "okay"
                                        border_red = 0.dp
                                        border_blue = 2.dp
                                        border_green = 0.dp
                                        border_purple = 0.dp
                                        border_yellow = 0.dp
                                        viewModel.onEvent(SleepPageEvent.QualityChanged(quality))
                                    }
                            ) {

                                Image(painter = painterResource(R.drawable.blue_okay_face),
                                    contentDescription = "Okay",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(5.dp),)
                            }
                            Text("Okay",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(end = 9.dp, top = 5.dp)
                                    .align(Alignment.CenterHorizontally),
                                fontSize = 16.sp)
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
                                    .border(border_purple, Color.Magenta, RoundedCornerShape(15.dp))

                                    .clickable {
                                        quality = "good"
                                        border_red = 0.dp
                                        border_blue = 0.dp
                                        border_green = 0.dp
                                        border_purple = 2.dp
                                        border_yellow = 0.dp
                                        viewModel.onEvent(SleepPageEvent.QualityChanged(quality))
                                    }
                            ) {

                                Image(painter = painterResource(R.drawable.purple_good_face),
                                    contentDescription = "Good",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(5.dp),)
                            }
                            Text("Good",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(end = 9.dp, top = 5.dp)
                                    .align(Alignment.CenterHorizontally),
                                fontSize = 16.sp)
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
                                    .border(border_green, Color.Green, RoundedCornerShape(15.dp))

                                    .clickable {
                                        quality = "great"
                                        border_red = 0.dp
                                        border_blue = 0.dp
                                        border_green = 2.dp
                                        border_purple = 0.dp
                                        border_yellow = 0.dp
                                        viewModel.onEvent(SleepPageEvent.QualityChanged(quality))
                                    }
                            ) {

                                Image(painter = painterResource(R.drawable.green_great_face),
                                    contentDescription = "Great",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(5.dp),)
                            }
                            Text("Great",
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(end = 9.dp, top = 5.dp)
                                    .align(Alignment.CenterHorizontally),
                                fontSize = 16.sp)
                        }


                    }
                }
                Spacer(modifier = Modifier.height(70.dp))
                Button(
                    onClick={ showPopUp = true},
                    modifier = Modifier.fillMaxWidth(0.8f)
                ){
                Text("Set your sleeping goal ")
                }

                Spacer(modifier = Modifier.height(70.dp))
                Button(
                    onClick = {

                        if(quality!="") {
                            if(selectedtime != "") {
                                viewModel.onEvent(
                                    SleepPageEvent.AddSleepLog(
                                        time = selectedtime,
                                        quality = quality
                                    )
                                )
                                navController.navigate(Screen.HomePageScreen.route)
                            }else{
                                showSleepMessage = true // the user did not input its sleep
                            }
                        }
                        else{
                            showNotification= true // The user did not select quality

                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                ) {
                    Text(
                        "OK",
                        fontSize = 20.sp
                    )

                }
                if(showNotification) {
                    AlertDialog(
                        onDismissRequest = {showNotification= false},
                        title = { Text("Please choose the quality of your sleep!")},
                        confirmButton = {
                            Button(
                                onClick={ showNotification=false},

                            ){
                                Text("OK")
                            }
                        }
                    )
                }
                if(showSleepMessage){
                    AlertDialog(
                        onDismissRequest = {showSleepMessage= false},
                        title = { Text("Please input the duration of your sleep!")},
                        confirmButton = {
                            Button(
                                onClick={ showSleepMessage=false},

                                ){
                                Text("OK")
                            }
                        }
                    )
                }

                if(showPopUp){
                    AlertDialog(onDismissRequest={
                        showPopUp=false
                    },
                        title={
                            Text(
                                "Set your new sleeping goal",
                                textAlign = TextAlign.Center
                                )
                              },
                        text={
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                TextField(
                                    value = newsleepgoal,
                                    onValueChange = {
                                        newsleepgoal = it
                                        viewModel.onEvent(SleepPageEvent.UpdateSleepGoal(newsleepgoal))},
                                    label = { Text("Click here") },
                                    singleLine = true,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    showPopUp = false
                                    viewModel.onEvent(SleepPageEvent.UpdateSleepGoal(newsleepgoal))
                                    sleepgoal=viewModel.getSleepGoal()
                                },
                                modifier= Modifier.padding(horizontal= 20.dp)
                            ) {
                                Text("OK")
                            }

                            Spacer(Modifier.width(8.dp))

                        },
                        dismissButton = {
                            Button(onClick = { showPopUp = false }) {
                                Text("Cancel")
                            }


                        }

                    )
                }
            }

        }

    }
}


