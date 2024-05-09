package com.example.happyhabits.feature_application.feature_toilet.presentation.toilet_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.happyhabits.R
import com.example.happyhabits.feature_authentication.presentation.util.Screen
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
//@Preview
@Composable
fun ToiletPageView(
    navController: NavController,
    viewModel : ToiletViewModel = hiltViewModel()
) {
    val state by viewModel.state
    val typesOptions = listOf("Pee", "Poo")
    var expandedState by remember {
        mutableStateOf(false)
    }
    var selectedType by remember {
        mutableStateOf(state.type)
    }
    val mContext = LocalContext.current
    val colors = listOf(Color.White, Color(0xff64519A))
    var newNotification = true
    var pickedTime by remember {
        mutableStateOf(state.time)
    }

    var toiletNotes by remember {
        mutableStateOf(state.notes)
    }
    val timeDialogState = rememberMaterialDialogState()

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
                                    navController.navigate(Screen.HomePageScreen.route)
                                })//navController.navigate(Screen.HomePageScreen.route)})
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
                Spacer(modifier = Modifier.height(20.dp))
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
                        .height(70.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .clickable { timeDialogState.show() }

                ) {
                    Row(
                        modifier = Modifier
                            .padding(top = 11.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center

                    ) {

                        Text(
                            text = "time : ",
                            fontSize = 20.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(start = 30.dp)
                        )

                        Text(

                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .height(50.dp)
                                .width(270.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color(0xffD8DADE)),

                            text = pickedTime
                        )
                    }

                    MaterialDialog(
                        dialogState = timeDialogState,
                        buttons = {
                            positiveButton(text = "Ok") {
                            }
                            negativeButton(text = "Cancel")
                        },
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        timepicker(
                            initialTime = LocalTime.NOON,
                            title = "Pick a time",

                        ) {
                            pickedTime = it.toString()
                            viewModel.onEvent(ToiletPageEvent.TimeChanged(pickedTime))
                        }
                    }


                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)

                ) {
                    Row(
                        modifier = Modifier
                            .padding(top = 11.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center

                    ) {

                        Text(
                            text = "type : ",
                            fontSize = 20.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(start = 30.dp)
                        )
                        ExposedDropdownMenuBox(
                            expanded = expandedState,
                            onExpandedChange = { expandedState = !expandedState })
                        {
                            TextField(
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth()
                                    .shadow(4.dp),
                                value = selectedType,
                                shape = RoundedCornerShape(8.dp),
                                onValueChange = {},
                                readOnly = true,
                                colors = ExposedDropdownMenuDefaults.textFieldColors(
                                    cursorColor = Color.Gray,
                                    unfocusedLabelColor = Color.Gray,
                                    focusedLabelColor = Color.Gray,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    unfocusedContainerColor = Color.White,
                                    focusedContainerColor = Color.White,
                                    focusedTextColor = Color.Black
                                ),
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedState) })
                            ExposedDropdownMenu(
                                expanded = expandedState,
                                onDismissRequest = { expandedState = false },
                                modifier = Modifier
                                    .background(Color.White)
                                    .fillMaxWidth()
                            ) {
                                typesOptions.forEach { option ->
                                    DropdownMenuItem(
                                        onClick = {
                                            selectedType = option
                                            expandedState = false
                                            viewModel.onEvent(ToiletPageEvent.TypeChanged(option))
                                        },
                                        text = { Text(text=option,
                                            fontSize = 20.sp,
                                            modifier = Modifier
                                        ) },
                                        modifier = Modifier
                                            .background(Color.White)
                                            .fillMaxWidth()

                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)

                ) {
                    Row(
                        modifier = Modifier
                        .padding(top = 11.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center

                    ) {

                        TextField(
                            value = toiletNotes,
                            shape = RoundedCornerShape(20.dp),
                            onValueChange = {
                                toiletNotes = it
                                viewModel.onEvent(ToiletPageEvent.NoteChanged(toiletNotes))
                            },
                            maxLines = 3,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .fillMaxHeight(),
                            label = { Text(text = "Write notes here") },
                            colors = TextFieldDefaults.colors(
                                cursorColor = Color.Gray,
                                unfocusedLabelColor = Color.Gray,
                                focusedLabelColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                unfocusedContainerColor = Color.LightGray,
                                focusedContainerColor = Color.LightGray,
                                focusedTextColor = Color.Black
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))

                Button(onClick = {
                    viewModel.onEvent(
                        ToiletPageEvent.AddToiletLog(
                            time = pickedTime,
                            type = selectedType,
                            notes = toiletNotes
                        )
                    )
                    navController.navigate(Screen.HomePageScreen.route) // Μήπωσ εθ

                    },
                    modifier= Modifier
                        .fillMaxWidth(0.5f)
                    ) {
                    Text("OK",
                    fontSize = 20.sp)
                }

                }
            }
        }
    }


