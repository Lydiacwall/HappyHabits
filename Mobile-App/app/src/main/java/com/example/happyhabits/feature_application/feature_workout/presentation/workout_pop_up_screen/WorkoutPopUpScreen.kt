package com.example.happyhabits.feature_application.feature_workout.presentation.workout_pop_up_screen


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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.happyhabits.R
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.happyhabits.feature_application.feature_workout.domain.model.ExercisesWorkout
import com.example.happyhabits.feature_application.feature_workout.domain.model.Weights
import java.time.format.DateTimeFormatter
import java.time.LocalTime
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import java.time.LocalDate

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
    var workoutDuration by remember { mutableStateOf(state.duration) }
    var workoutNotes by remember { mutableStateOf(state.notes) }
    var unitMeasurement by remember { mutableStateOf(state.unitMeasurement) }
    var quantity by remember { mutableStateOf(state.quantity) }
    var activityElevation by remember { mutableStateOf(state.elevation) }
    var exercises by remember { mutableStateOf(state.exercises) }
    var currentExercise by remember { mutableStateOf(state.currentExercise) }
    var workoutDurationHours by remember { mutableStateOf(state.hoursDuration)}
    var workoutDurationMinutes by remember { mutableStateOf(state.minutesDuration)}
    var workoutTimeHours by remember { mutableStateOf(state.hoursTime)}
    var workoutTimeMinutes by remember { mutableStateOf(state.minutesTime)}

    var newNotification = true
    var quantityText by remember{ mutableStateOf("") }
    var elevationText by remember{ mutableStateOf("") }
    var repsText by remember{ mutableStateOf("") }
    var setsText by remember{ mutableStateOf("") }
    var kgsText by remember { mutableStateOf("") }
    var hoursText by remember{ mutableStateOf("") }
    var minutesText by remember{ mutableStateOf("") }
    var exerciseInput by remember{
        mutableStateOf("")
    }
    var isExpanded by remember {
        mutableStateOf(false)
    }
    val weights = Weights(
        "", "", LocalDate.now(),
        time = "some time",
        duration= "some duration",
        notes = "some notes"
    )
    val swimmingWorkout = ExercisesWorkout(
        "", "", LocalDate.now(),
        type = "Swimming",
        time = "Morning",
        duration = "1 hour",
        notes = "Freestyle strokes",
        simpleExercises = listOf("Freestyle", "Backstroke", "Breaststroke")
    )

    val yogaWorkout = ExercisesWorkout(
        "", "", LocalDate.now(),
        type = "Yoga",
        time = "Evening",
        duration = "45 minutes",
        notes = "Focus on flexibility",
        simpleExercises = listOf("Downward Dog", "Warrior Pose", "Child's Pose")
    )

    var pickedTime by remember {
        mutableStateOf(LocalTime.now())
    }

    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("hh : mm")
                .format(pickedTime)
        }
    }
    val timeDialogState = rememberMaterialDialogState()
    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton(text = "Ok") {
            }
            negativeButton(text = "Cancel")
        }
    ) {
        timepicker(
            initialTime = LocalTime.NOON,
            title = "Pick a time"
        ) {
            pickedTime = it
            viewModel.onEvent(WorkoutPopUpEvent.TimeHoursChanged(pickedTime.hour))
            viewModel.onEvent(WorkoutPopUpEvent.TimeMinutesChanged(pickedTime.minute))
        }
    }

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
        ){
            Row (
                Modifier
                    .fillMaxHeight(0.13f)) {
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
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(25.dp))
                ///////////////////////////TIME///////////////////////////
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                )
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(100.dp)
                            .background(Color.White, RoundedCornerShape(20.dp))
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Time:",
                                    color = Color.Black,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Box(
                                Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight()
                                    .clickable { timeDialogState.show()}
                                    .background(Color.LightGray, shape = RoundedCornerShape(20.dp))
                                    .padding(start = 20.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = formattedTime,
                                    color = Color.Black,
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(25.dp))
                ///////////////////////////DURATION///////////////////////////
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                )
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(100.dp)
                            .background(Color.White, RoundedCornerShape(20.dp))
                            .padding(start=20.dp, end=10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
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
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .padding(start = 19.dp)
                            )
                            {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(0.45f)
                                        .height(70.dp)
                                        .background(
                                            Color.LightGray,
                                            RoundedCornerShape(20.dp)
                                        )
                                        .padding(start = 0.dp, end = 3.dp),
                                    contentAlignment = Alignment.Center
                                )
                                {
                                    Row(
                                        Modifier
                                            .fillMaxWidth(1f),
                                        verticalAlignment = Alignment.CenterVertically
                                    )
                                    {
                                        TextField(
                                            value = hoursText,
                                            onValueChange = {
                                                hoursText = it
                                                val hours = hoursText.toInt()
                                                viewModel.onEvent(WorkoutPopUpEvent.DurationHoursChanged(hours))
                                            },
                                            maxLines = 1,
                                            modifier = Modifier
                                                .fillMaxWidth(0.7f)
                                                .fillMaxHeight(),
                                            label = {
                                                Text(
                                                    text = "Type Here",
                                                    fontSize = 12.sp
                                                )
                                            },
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
                                            keyboardOptions = KeyboardOptions.Default.copy(
                                                keyboardType = KeyboardType.Number
                                            )
                                        )
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth(1f)
                                                .height(40.dp)
                                                .background(
                                                    Color.White,
                                                    RoundedCornerShape(20.dp)
                                                ),
                                            contentAlignment = Alignment.Center
                                        )
                                        {
                                            Text(
                                                text = "h",
                                                color = Color.Black,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        }
                                    }

                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(0.92f)
                                        .height(70.dp)
                                        .background(
                                            Color.LightGray,
                                            RoundedCornerShape(20.dp)
                                        )
                                        .padding(start = 0.dp, end = 4.dp),
                                    contentAlignment = Alignment.Center
                                )
                                {
                                    Row(
                                        Modifier
                                            .fillMaxWidth(1f),
                                        verticalAlignment = Alignment.CenterVertically
                                    )
                                    {
                                        TextField(
                                            value = minutesText,
                                            onValueChange ={
                                                minutesText = it
                                                val minutes= minutesText.toInt()
                                                viewModel.onEvent(WorkoutPopUpEvent.DurationMinutesChanged(minutes))
                                            },
                                            maxLines = 1,
                                            modifier = Modifier
                                                .fillMaxWidth(0.7f)
                                                .fillMaxHeight(),
                                            label = {
                                                Text(
                                                    text = "Type Here",
                                                    fontSize = 12.sp
                                                )
                                            },
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
                                            keyboardOptions = KeyboardOptions.Default.copy(
                                                keyboardType = KeyboardType.Number
                                            )
                                        )
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth(1f)
                                                .height(40.dp)
                                                .background(
                                                    Color.White,
                                                    RoundedCornerShape(20.dp)
                                                ),
                                            contentAlignment = Alignment.Center
                                        )
                                        {
                                            Text(
                                                text = "m",
                                                color = Color.Black,
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(25.dp))
                if (workoutType == "Running" || workoutType == "Biking") {
                    ///////////////////////////KILOMETERS///////////////////////////
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(100.dp)
                                .background(Color.White, RoundedCornerShape(20.dp))
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
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
                                        fontSize = 20.sp,
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
                                            .background(Color.LightGray, RoundedCornerShape(20.dp)),
                                        contentAlignment = Alignment.Center
                                    )
                                    {
                                        Row(
                                            Modifier
                                                .fillMaxSize()
                                                .padding(start = 8.dp, end = 8.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        )
                                        {
                                            TextField(
                                                value = quantityText ,
                                                onValueChange = {
                                                    quantity = it.toFloat()
                                                    quantityText = quantity.toString()
                                                    viewModel.onEvent(
                                                        WorkoutPopUpEvent.QuantityChanged(
                                                            quantity
                                                        )
                                                    )
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
                                                keyboardOptions = KeyboardOptions.Default.copy(
                                                    keyboardType = KeyboardType.Number
                                                )
                                            )
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth(1f)
                                                    .height(50.dp)
                                                    .background(
                                                        Color.White,
                                                        RoundedCornerShape(20.dp)
                                                    ),
                                                contentAlignment = Alignment.Center
                                            )
                                            {
                                                Text(
                                                    text = "km",
                                                    color = Color.Black,
                                                    fontSize = 15.sp,
                                                    fontWeight = FontWeight.SemiBold
                                                )
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    ///////////////////////////ELEVATION///////////////////////////
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(100.dp)
                                .background(Color.White, RoundedCornerShape(20.dp))
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
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
                                        fontSize = 20.sp,
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
                                        Row(
                                            Modifier
                                                .fillMaxSize(),
                                            verticalAlignment = Alignment.CenterVertically
                                        )
                                        {
                                            TextField(
                                                value =  elevationText,
                                                onValueChange = {
                                                    activityElevation = it.toFloat()
                                                    elevationText = activityElevation.toString()
                                                    viewModel.onEvent(
                                                        WorkoutPopUpEvent.ElevationChanged(
                                                            activityElevation
                                                        )
                                                    )
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
                                                keyboardOptions = KeyboardOptions.Default.copy(
                                                    keyboardType = KeyboardType.Number
                                                )
                                            )
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth(1f)
                                                    .height(50.dp)
                                                    .background(
                                                        Color.White,
                                                        RoundedCornerShape(20.dp)
                                                    ),
                                                contentAlignment = Alignment.Center
                                            )
                                            {
                                                Text(
                                                    text = "m",
                                                    color = Color.Black,
                                                    fontSize = 15.sp,
                                                    fontWeight = FontWeight.SemiBold
                                                )
                                            }
                                        }
                                    }
                                }

                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
                if (workoutType == "Weights") {
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
                                .height(360.dp)
                                .background(Color.White, RoundedCornerShape(20.dp))
                                .padding(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp), contentAlignment = Alignment.Center
                            )
                            {
                                Text(
                                    text = "Exercises:",
                                    color = Color.Black,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
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
                                                    expanded = isExpanded
                                                )
                                            })
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
                                                        viewModel.onEvent(WorkoutPopUpEvent.ExerciseNameChanged(exerciseInput))
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
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth(0.9f)
                                            .background(Color.LightGray, RoundedCornerShape(20.dp)),
                                        contentAlignment = Alignment.Center
                                    )
                                    {
                                        Row(
                                            Modifier
                                                .fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically
                                        )
                                        {
                                            TextField(
                                                value = kgsText,
                                                onValueChange = {
                                                    kgsText = it
                                                    viewModel.onEvent(
                                                        WorkoutPopUpEvent.ExerciseKgsChanged(kgsText.toFloat())
                                                    )
                                                },
                                                maxLines = 1,
                                                modifier = Modifier
                                                    .fillMaxWidth(0.8f),
                                                label = {
                                                    Text(
                                                        text = "Type Here",
                                                        fontSize =12.sp
                                                    )
                                                },
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
                                                keyboardOptions = KeyboardOptions.Default.copy(
                                                    keyboardType = KeyboardType.Number
                                                )
                                            )
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth(1f)
                                                    .height(50.dp)
                                                    .padding(8.dp)
                                                    .background(
                                                        Color.White,
                                                        RoundedCornerShape(20.dp)
                                                    ),
                                                contentAlignment = Alignment.Center
                                            )
                                            {
                                                Text(
                                                    text = "kg",
                                                    color = Color.Black,
                                                    fontSize = 15.sp,
                                                    fontWeight = FontWeight.SemiBold
                                                )
                                            }
                                        }

                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(1f)
                                            .padding(start = 19.dp, top = 10.dp)
                                    )
                                    {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth(0.45f)
                                                .height(70.dp)
                                                .background(
                                                    Color.LightGray,
                                                    RoundedCornerShape(20.dp)
                                                )
                                                .padding(start = 0.dp, end = 6.dp),
                                            contentAlignment = Alignment.Center
                                        )
                                        {
                                            Row(
                                                Modifier
                                                    .fillMaxWidth(0.9f),
                                                verticalAlignment = Alignment.CenterVertically
                                            )
                                            {
                                                TextField(
                                                    value = setsText,
                                                    onValueChange = {
                                                        setsText = it
                                                        viewModel.onEvent(
                                                            WorkoutPopUpEvent.ExerciseSetsChanged(setsText.toInt())
                                                        )
                                                    },
                                                    maxLines = 1,
                                                    modifier = Modifier
                                                        .fillMaxWidth(0.5f)
                                                        .fillMaxHeight(),
                                                    label = {
                                                        Text(
                                                            text = "Type Here",
                                                            fontSize = 12.sp
                                                        )
                                                    },
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
                                                    keyboardOptions = KeyboardOptions.Default.copy(
                                                        keyboardType = KeyboardType.Number
                                                    )
                                                )
                                                Box(
                                                    modifier = Modifier
                                                        .fillMaxWidth(1f)
                                                        .height(50.dp)
                                                        .background(
                                                            Color.White,
                                                            RoundedCornerShape(20.dp)
                                                        ),
                                                    contentAlignment = Alignment.Center
                                                )
                                                {
                                                    Text(
                                                        text = "sets",
                                                        color = Color.Black,
                                                        fontSize = 15.sp,
                                                        fontWeight = FontWeight.SemiBold
                                                    )
                                                }
                                            }

                                        }
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth(0.9f)
                                                .height(70.dp)
                                                .background(
                                                    Color.LightGray,
                                                    RoundedCornerShape(20.dp)
                                                )
                                                .padding(start = 0.dp, end = 10.dp),
                                            contentAlignment = Alignment.Center
                                        )
                                        {
                                            Row(
                                                Modifier
                                                    .fillMaxWidth(1f),
                                                verticalAlignment = Alignment.CenterVertically
                                            )
                                            {
                                                TextField(
                                                    value = repsText,
                                                    onValueChange = {
                                                        repsText = it
                                                        viewModel.onEvent(
                                                            WorkoutPopUpEvent.ExerciseRepsChanged(repsText.toInt())
                                                        )
                                                    },
                                                    maxLines = 1,
                                                    modifier = Modifier
                                                        .fillMaxWidth(0.5f)
                                                        .fillMaxHeight(),
                                                    label = {
                                                        Text(
                                                            text = "Type Here",
                                                            fontSize = 12.sp
                                                        )
                                                    },
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
                                                    keyboardOptions = KeyboardOptions.Default.copy(
                                                        keyboardType = KeyboardType.Number
                                                    )
                                                )
                                                Box(
                                                    modifier = Modifier
                                                        .fillMaxWidth(1f)
                                                        .height(50.dp)
                                                        .background(
                                                            Color.White,
                                                            RoundedCornerShape(20.dp)
                                                        ),
                                                    contentAlignment = Alignment.Center
                                                )
                                                {
                                                    Text(
                                                        text = "reps",
                                                        color = Color.Black,
                                                        fontSize = 15.sp,
                                                        fontWeight = FontWeight.SemiBold
                                                    )
                                                }
                                            }

                                        }
                                    }
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .padding(top = 15.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Button(
                                        onClick = {
//                                            viewModel.onEvent(WorkoutPopUpEvent.TimeChanged(formattedTime,navController))
                                            viewModel.onEvent(WorkoutPopUpEvent.AddCurrentExercise(""))
                                            exerciseInput = state.currentExercise.name.toString()
                                            quantityText = ""
                                            repsText = ""
                                            setsText = ""
                                            kgsText = ""
                                        },
                                        shape = RoundedCornerShape(50),
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(60.dp)
                                            .padding(start = 20.dp, end = 5.dp),

                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFFA687FF)
                                        ),
                                        elevation = ButtonDefaults.buttonElevation(
                                            defaultElevation = 5.dp,
                                            pressedElevation = 5.dp,
                                        )
                                    ) {
                                        Text(
                                            text = "Add",
                                            color = Color.White,
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }
                                    Button(
                                        onClick = {
                                        },
                                        shape = RoundedCornerShape(50),
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(60.dp)
                                            .padding(start = 5.dp, end = 20.dp),

                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFFFC8686)
                                        ),
                                        elevation = ButtonDefaults.buttonElevation(
                                            defaultElevation = 5.dp,
                                            pressedElevation = 5.dp,
                                        )
                                    ) {
                                        Text(
                                            text = "Remove",
                                            color = Color.White,
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
                ///////////////////////////NOTE///////////////////////////
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                )
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .height(120.dp)
                            .background(Color.White, RoundedCornerShape(20.dp))
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
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
                                    fontSize = 20.sp,
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
                                        viewModel.onEvent(
                                            WorkoutPopUpEvent.NotesChanged(
                                                workoutNotes
                                            )
                                        )
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
                if (workoutType == "Swimming" || workoutType=="Yoga") {
                    Spacer(modifier = Modifier.height(20.dp))
                    ///////////////////////////SWIMMING OR YOGA///////////////////////////
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(250.dp)
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
                                    text = "Exercises:",
                                    color = Color.Black,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
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
                                            expanded = isExpanded
                                        )
                                    })
                                ExposedDropdownMenu(
                                    expanded = isExpanded,
                                    onDismissRequest = { isExpanded = false },
                                    modifier = Modifier
                                        .background(Color.White)
                                        .fillMaxWidth()
                                ) {
                                    if (workoutType == "Swimming") {
                                        swimmingWorkout.swimmingExercises.forEach { exercise ->
                                            DropdownMenuItem(
                                                onClick = {
                                                    exerciseInput = exercise
                                                    viewModel.onEvent(
                                                        WorkoutPopUpEvent.SimpleExerciseNameChanged(
                                                            exerciseInput
                                                        )
                                                    )
                                                    isExpanded = false
                                                },
                                                text = { Text(exercise, fontSize = 20.sp) },
                                                modifier = Modifier
                                                    .background(Color.White)
                                                    .fillMaxWidth()
                                            )
                                        }
                                    }else if(workoutType=="Yoga"){
                                        yogaWorkout.yogaPoses.forEach { exercise ->
                                            DropdownMenuItem(
                                                onClick = {
                                                    exerciseInput = exercise
                                                    viewModel.onEvent(
                                                        WorkoutPopUpEvent.SimpleExerciseNameChanged(
                                                            exerciseInput
                                                        )
                                                    )
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
                            Spacer(modifier = Modifier.height(10.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .padding(top = 15.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Button(
                                        onClick = {
//                                            viewModel.onEvent(WorkoutPopUpEvent.TimeChanged(formattedTime,navController))
                                            viewModel.onEvent(WorkoutPopUpEvent.AddCurrentSimpleExercise(""))
                                            exerciseInput = state.currentExercise.name.toString()
                                        },
                                        shape = RoundedCornerShape(50),
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(60.dp)
                                            .padding(start = 20.dp, end = 5.dp),

                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFFA687FF)
                                        ),
                                        elevation = ButtonDefaults.buttonElevation(
                                            defaultElevation = 5.dp,
                                            pressedElevation = 5.dp,
                                        )
                                    ) {
                                        Text(
                                            text = "Add",
                                            color = Color.White,
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }
                                    Button(
                                        onClick = {
                                        },
                                        shape = RoundedCornerShape(50),
                                        modifier = Modifier
                                            .weight(1f)
                                            .height(60.dp)
                                            .padding(start = 5.dp, end = 20.dp),

                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFFFC8686)
                                        ),
                                        elevation = ButtonDefaults.buttonElevation(
                                            defaultElevation = 5.dp,
                                            pressedElevation = 5.dp,
                                        )
                                    ) {
                                        Text(
                                            text = "Remove",
                                            color = Color.White,
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
                Spacer(modifier = Modifier.height(25.dp))
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Button(
                        onClick = {
                            viewModel.onEvent(WorkoutPopUpEvent.TimeChanged(""))
                            viewModel.onEvent(WorkoutPopUpEvent.SaveWorkout("", navController))
                            pickedTime= LocalTime.now()
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
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}
