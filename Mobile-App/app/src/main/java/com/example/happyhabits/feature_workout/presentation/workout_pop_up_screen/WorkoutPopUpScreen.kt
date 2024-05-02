package com.example.happyhabits.feature_workout.presentation.workout_pop_up_screen


import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.happyhabits.R
import com.example.happyhabits.feature_authentication.domain.model.Type
import com.example.happyhabits.feature_authentication.presentation.login.LoginViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.delay
import org.w3c.dom.Text
import com.example.happyhabits.feature_workout.presentation.util.Screen
import com.example.happyhabits.feature_authentication.domain.model.User
import com.example.happyhabits.feature_authentication.presentation.sign_up_user.SignUpUserEvent
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import com.example.happyhabits.feature_workout.domain.model.Weights
import com.example.happyhabits.feature_workout.domain.model.Workout
import com.example.happyhabits.feature_workout.presentation.workout_screen.WorkoutPageEvent
import com.example.happyhabits.feature_workout.presentation.workout_screen.WorkoutPageViewmodel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.time.LocalTime
import com.example.happyhabits.feature_workout.presentation.workout_pop_up_screen.WorkoutPopUpState

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WorkoutPopUpView(
    navController: NavController,
    viewModel: WorkoutPopUpViewmodel = hiltViewModel()
){
    val context = LocalContext.current
    val state by viewModel.state
    val workoutType by remember { mutableStateOf(state.type) }
    var workoutTime by remember { mutableStateOf(state.time) }
    var workoutNotes by remember { mutableStateOf(state.notes) }
    var unitMeasurement by remember { mutableStateOf(state.unitMeasurement) }
    var quantity by remember { mutableStateOf(state.quantity) }
    var elevation by remember { mutableStateOf(state.elevation) }
    var muscleGroup by remember { mutableStateOf(state.muscleGroup) }
    var exercises by remember { mutableStateOf(state.exercises) }
    var newNotification = true
    var exerciseInput by remember{
        mutableStateOf("")
    }
    var isExpanded by remember {
        mutableStateOf(false)
    }
    val weights = Weights(
        time = "some time",
        notes = "some notes",
        quantity = 10f
    )

//    val navBackStackEntry = navController.currentBackStackEntry
//        ?: error("WorkoutPopUpView must be called from a NavHost")
//
//    val workoutType = remember {
//        navBackStackEntry.arguments?.getString("type")
//            ?: error("Type must be provided for workout_pop_up_page destination")
//    }


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
        ) {
            Row(
                Modifier
                    .fillMaxHeight(0.13f)
            ) {
                Box(
                    Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight()
                )
                {
                    Column(modifier = Modifier.fillMaxSize())
                    {
                        Box()
                        {
                            Row(modifier = Modifier.clickable {
                                viewModel.onEvent(
                                    WorkoutPopUpEvent.ChangePage(
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
                            text = workoutType,
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
                        .padding(bottom = 12.dp, end = 13.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Absolute.Right
                ) {
                    Image(
                        painter = painterResource(R.drawable.barcode_icon),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(33.dp)
                    )
                    Box {
                        Image(
                            painter = painterResource(R.drawable.notification_icon),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(33.dp)
                        )
                        Row(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(end = 3.dp, top = 4.dp)
                        ) {
                            if (newNotification) {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .background(
                                            Color(0xffff8c14),
                                            shape = MaterialTheme.shapes.small
                                        )
                                )
                            }
                        }
                    }
                    Image(
                        painter = painterResource(R.drawable.settings_icon),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(33.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            ///////////////////////////DURATION///////////////////////////
            Box(modifier = Modifier
                .fillMaxWidth(),
                contentAlignment = Alignment.Center)
            {
                Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(100.dp)
                    .background(Color.White, RoundedCornerShape(20.dp))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
                ) {Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Duration:",
                            color = Color.Black,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Box(
                        Modifier
                            .fillMaxWidth(1f)
                            .fillMaxHeight()
                            .padding(start = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .fillMaxHeight()
                                .background(Color.LightGray, RoundedCornerShape(20.dp))
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ){}
                    }
                }
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            if(workoutType=="Running" || workoutType=="Biking")
            {
                ///////////////////////////KILOMETERS///////////////////////////
                Box(modifier = Modifier
                    .fillMaxWidth(),
                    contentAlignment = Alignment.Center)
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(100.dp)
                            .background(Color.White, RoundedCornerShape(20.dp))
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {Row(
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
                            Text(
                                text = "Kilometers:",
                                color = Color.Black,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Box(
                            Modifier
                                .fillMaxWidth(1f)
                                .fillMaxHeight()
                                .padding(start = 20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight()
                                    .background(Color.LightGray, RoundedCornerShape(20.dp))
                                    .padding(start = 8.dp, end = 8.dp),
                                contentAlignment = Alignment.Center
                            )
                            {
                                Row(Modifier
                                    .fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically)
                                {
                                    TextField(
                                        value = quantity?.toString() ?: "",
                                        onValueChange = {
                                            quantity = if (it.isNotEmpty()) it.toFloat() else null
//                                viewModel.onEvent()
                                        },
                                        maxLines = 1,
                                        modifier = Modifier
                                            .fillMaxWidth(0.6f)
                                            .fillMaxHeight(),
                                        label = { Text(text = "Type here") },
                                        colors = TextFieldDefaults.colors(
                                            cursorColor = Color.Gray,
                                            unfocusedLabelColor = Color.Gray,
                                            focusedLabelColor = Color.Transparent,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                            unfocusedContainerColor = Color.Transparent,
                                            focusedContainerColor = Color.Transparent,
                                            focusedTextColor = Color.Black
                                        ),
                                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(1f)
                                            .height(50.dp)
                                            .background(Color.White, RoundedCornerShape(20.dp)),
                                        contentAlignment = Alignment.Center
                                    )
                                    {
                                        Text(
                                            text = "km",
                                            color = Color.Black,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.SemiBold)
                                    }
                                }
                            }
                        }

                    }
                    }
                }
                Spacer(modifier = Modifier.height(25.dp))
                ///////////////////////////ELEVATION///////////////////////////
                Box(modifier = Modifier
                    .fillMaxWidth(),
                    contentAlignment = Alignment.Center)
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(100.dp)
                            .background(Color.White, RoundedCornerShape(20.dp))
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {Row(
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
                            Text(
                                text = "Elevation:",
                                color = Color.Black,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Box(
                            Modifier
                                .fillMaxWidth(1f)
                                .fillMaxHeight()
                                .padding(start = 20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight()
                                    .background(Color.LightGray, RoundedCornerShape(20.dp))
                                    .padding(start = 8.dp, end = 8.dp),
                                contentAlignment = Alignment.Center
                            )
                            {
                                Row(Modifier
                                    .fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically)
                                {
                                    TextField(
                                        value = elevation?.toString() ?: "",
                                        onValueChange = {
                                            elevation = if (it.isNotEmpty()) it.toFloat() else null
                                            // Handle value change here
                                        },
                                        maxLines = 1,
                                        modifier = Modifier
                                            .fillMaxWidth(0.6f)
                                            .fillMaxHeight(),
                                        label = { Text(text = "Type here") },
                                        colors = TextFieldDefaults.colors(
                                            cursorColor = Color.Gray,
                                            unfocusedLabelColor = Color.Gray,
                                            focusedLabelColor = Color.Transparent,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                            unfocusedContainerColor = Color.Transparent,
                                            focusedContainerColor = Color.Transparent,
                                            focusedTextColor = Color.Black
                                        ),
                                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(1f)
                                            .height(50.dp)
                                            .background(Color.White, RoundedCornerShape(20.dp)),
                                        contentAlignment = Alignment.Center
                                    )
                                    {
                                        Text(
                                            text = "m",
                                            color = Color.Black,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.SemiBold)
                                    }
                                }
                            }
                        }

                    }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
            if(workoutType=="Weights") {
                ///////////////////////////WEIGHTS///////////////////////////
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                )
                {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(300.dp)
                            .background(Color.White, RoundedCornerShape(20.dp))
                            .padding(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp), contentAlignment = Alignment.Center,
                        )
                        {
                            Text(
                                text = "Exercises:",
                                color = Color.Black,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                        )
                        {
                            ExposedDropdownMenuBox(
                                expanded = isExpanded,
                                onExpandedChange = { isExpanded = !isExpanded })
                            {
                                TextField(
                                    modifier = Modifier
                                        .menuAnchor()
                                        .fillMaxWidth(0.9f),
                                    value = exerciseInput,
                                    shape = RoundedCornerShape(20.dp),
                                    label = { Text(text = "Choose Exercise") },
                                    onValueChange = {},
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
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) })
                                ExposedDropdownMenu(
                                    expanded = isExpanded,
                                    onDismissRequest = { isExpanded = false },
                                    modifier = Modifier
                                        .background(Color.White)
                                        .fillMaxWidth()
                                ) {
                                    weights.weightExercises.forEach { exercise ->
                                        DropdownMenuItem(
                                            onClick = {
                                                exerciseInput = exercise
//                                                viewModel.onEvent(SignUpUserEvent.SpecialtyChanged(exercise))
                                                isExpanded = false
                                            },
                                            text = { Text(exercise, fontSize = 20.sp) },
                                            modifier = Modifier
                                                .background(Color.White)
                                                .fillMaxWidth()
                                        )
                                    }
                                }
                            }
                        }
                        Row (modifier = Modifier
                            .fillMaxWidth(1f)
                            .padding(start = 19.dp, top = 10.dp))
                        {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.45f)
                                    .height(70.dp)
                                    .background(Color.LightGray, RoundedCornerShape(20.dp))
                                    .padding(start = 0.dp, end = 3.dp),
                                contentAlignment = Alignment.Center
                            )
                            {
                                Row(Modifier
                                    .fillMaxWidth(0.9f),
                                    verticalAlignment = Alignment.CenterVertically)
                                {
                                    TextField(
                                        value = elevation?.toString() ?: "",
                                        onValueChange = {
                                            elevation = if (it.isNotEmpty()) it.toFloat() else null
                                            // Handle value change here
                                        },
                                        maxLines = 1,
                                        modifier = Modifier
                                            .fillMaxWidth(0.5f)
                                            .fillMaxHeight(),
                                        label = { Text(text = "Type Here", fontSize = 12.sp) },
                                        colors = TextFieldDefaults.colors(
                                            cursorColor = Color.Gray,
                                            unfocusedLabelColor = Color.Gray,
                                            focusedLabelColor = Color.Transparent,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                            unfocusedContainerColor = Color.Transparent,
                                            focusedContainerColor = Color.Transparent,
                                            focusedTextColor = Color.Black
                                        ),
                                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(1f)
                                            .height(50.dp)
                                            .background(Color.White, RoundedCornerShape(20.dp)),
                                        contentAlignment = Alignment.Center
                                    )
                                    {
                                        Text(
                                            text = "sets",
                                            color = Color.Black,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.SemiBold)
                                    }
                                }

                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .height(70.dp)
                                    .background(Color.LightGray, RoundedCornerShape(20.dp))
                                    .padding(start = 0.dp, end = 10.dp),
                                contentAlignment = Alignment.Center
                            )
                            {
                                Row(Modifier
                                    .fillMaxWidth(1f),
                                    verticalAlignment = Alignment.CenterVertically)
                                {
                                    TextField(
                                        value = elevation?.toString() ?: "",
                                        onValueChange = {
                                            elevation = if (it.isNotEmpty()) it.toFloat() else null
                                            // Handle value change here
                                        },
                                        maxLines = 1,
                                        modifier = Modifier
                                            .fillMaxWidth(0.5f)
                                            .fillMaxHeight(),
                                        label = { Text(text = "Type Here", fontSize = 12.sp) },
                                        colors = TextFieldDefaults.colors(
                                            cursorColor = Color.Gray,
                                            unfocusedLabelColor = Color.Gray,
                                            focusedLabelColor = Color.Transparent,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent,
                                            unfocusedContainerColor = Color.Transparent,
                                            focusedContainerColor = Color.Transparent,
                                            focusedTextColor = Color.Black
                                        ),
                                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                                    )
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(1f)
                                            .height(50.dp)
                                            .background(Color.White, RoundedCornerShape(20.dp)),
                                        contentAlignment = Alignment.Center
                                    )
                                    {
                                        Text(
                                            text = "reps",
                                            color = Color.Black,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.SemiBold)
                                    }
                                }

                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
                                .padding(top = 15.dp), contentAlignment = Alignment.Center
                        )
                        {
                            Button(
                                onClick = {
                                },
                                shape = RoundedCornerShape(50),
                                modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .width(150.dp)
                                    .height(60.dp),

                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFA687FF)

                                ),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 5.dp,
                                    pressedElevation = 5.dp,
                                )

                            )
                            {
                                Text(
                                    text = "add",
                                    color = Color.White,
                                    fontSize = 27.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
            ///////////////////////////NOTE///////////////////////////
            Box(modifier = Modifier
                .fillMaxWidth(),
                contentAlignment = Alignment.Center)
            {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(120.dp)
                        .background(Color.White, RoundedCornerShape(20.dp))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Notes:",
                            color = Color.Black,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(start = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        TextField(
                            value = workoutNotes,
                            shape = RoundedCornerShape(20.dp),
                            onValueChange = {
                                workoutNotes = it
//                                viewModel.onEvent()
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
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Box( modifier = Modifier
                .fillMaxSize()) {
                Button(
                    onClick = {
                    },
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth(0.5f)
                        .height(75.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF64519A)

                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 10.dp,
                        pressedElevation = 10.dp,
                    )

                )
                {
                    Text(
                        text = "save workout",
                        color = Color.White,
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}