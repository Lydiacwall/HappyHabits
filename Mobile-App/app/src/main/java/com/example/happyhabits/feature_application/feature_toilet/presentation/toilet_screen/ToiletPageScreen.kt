package com.example.happyhabits.feature_application.feature_toilet.presentation.toilet_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
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
import androidx.compose.material3.TimePickerDefaults
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(colors = colors)
                )
                .padding(0.dp)
        )
        {
            Column(
            ) {
                Row(
                    Modifier
                        .fillMaxHeight(0.13f)
                ) {
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
                                    navController.navigate(Screen.HomePageScreen.route)
                                })
                                {
                                    Text(
                                        text = "<",
                                        color = Color(0xFF544C4C),
                                        fontSize = 30.sp,
                                        fontWeight = FontWeight.Normal,
                                        modifier = Modifier.padding(start = 20.dp, top = 15.dp)
                                    )
                                    Text(
                                        text = "Back",
                                        color = Color(0xFF544C4C),
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Normal,
                                        modifier = Modifier.padding(top = 22.dp)
                                    )
                                }
                            }
                            Text(
                                text = "Toilet",
                                color = Color.Black,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 20.dp)
                            )
                        }

                    }

                }
                Spacer(modifier = Modifier.height(5.dp))
            }
            Box(
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp)
            ) {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White)
                            .clickable { timeDialogState.show() },
                        contentAlignment = Alignment.CenterStart

                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                //.padding(start = 30.dp)
                                .clickable { timeDialogState.show() },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start

                        ) {

                            Text(
                                text = "time : ",
                                fontSize = 17.sp,
                                color = Color.Black,
                                modifier = Modifier
                                    .padding(start = 15.dp)
                            )

                            Text(
                                fontSize = 24.sp,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(end = 30.dp),
                                color = Color(0xff64519A),
                                text = pickedTime
                            )
                        }
                    }
                    MaterialDialog(
                        dialogState = timeDialogState,
                        buttons = {
                            positiveButton(
                                text = "Ok",
                            )

                            negativeButton(
                                text = "Cancel",
                            )
                        },
                        shape = RoundedCornerShape(20.dp),

                        ) {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFFD8DADE)) // Dark purple background color
                            //.padding(16.dp)
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
                            .height(100.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White)
                            .padding(15.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(top = 11.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center

                        ) {

                            Text(
                                text = "type : ",
                                fontSize = 17.sp,
                                color = Color.Black,
                                modifier = Modifier
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
                                        focusedTextColor = Color.Black,
                                        unfocusedTextColor = Color.Black
                                    ),
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(
                                            expanded = expandedState
                                        )
                                    })
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
                                            text = {
                                                Text(
                                                    text = option,
                                                    fontSize = 20.sp,
                                                    color = Color.Black
                                                )
                                            },
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
                            .height(150.dp)
                            .clip(RoundedCornerShape(20.dp))
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
                            value = toiletNotes,
                            shape = RoundedCornerShape(20.dp),
                            onValueChange = { newText ->
                                val lines = newText.split("\n")
                                if (lines.size <= 3) {
                                    toiletNotes = newText
                                } else {
                                    toiletNotes = lines.take(3).joinToString("\n")
                                }
                                viewModel.onEvent(ToiletPageEvent.NoteChanged(toiletNotes))
                            },

                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .fillMaxHeight(),
                            label = { Text("Write notes here") },
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            maxLines = 3,
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 40.sp,
                                color = Color.Black
                            )
                        )

                    }
                    Spacer(modifier = Modifier.height(50.dp))

                    Button(
                        onClick = {
                            viewModel.onEvent(
                                ToiletPageEvent.AddToiletLog(
                                    time = pickedTime,
                                    type = selectedType,
                                    notes = toiletNotes
                                )
                            )
                            navController.navigate(Screen.HomePageScreen.route)

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
}


