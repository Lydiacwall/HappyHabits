package com.example.happyhabits.feature_application.feature_medication.presentation.medication_screen

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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.happyhabits.R
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.KeyboardType
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.example.happyhabits.feature_application.presentation.util.Screen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MedicationPageView(
    navController: NavController,
    viewModel: MedicationPageViewmodel = hiltViewModel()
){
    val state by viewModel.state

    var newName by remember { mutableStateOf(state.nameToBeAdded)}
    var newNameText by remember { mutableStateOf("")}
    var newDosage by remember { mutableStateOf(state.dosageQuantityToBeAdded)}
    var newDosageText by remember { mutableStateOf("")}
    var newTimesPerDayText by remember { mutableStateOf("") }
    var newTimesPerDay by remember { mutableStateOf(state.timesShouldBeTakenTodayToBeAdded) }
    var newNotesText by remember { mutableStateOf("")}
    var newNotes by remember { mutableStateOf(state.notesToBeAdded)}
    var newDosageUnitMeasurementText by remember { mutableStateOf("")}
    var newDosageUnitMeasurement by remember { mutableStateOf(state.dosageUnitMeasurementToBeAdded)}
    val pillDialogState = rememberMaterialDialogState()
    val infoDialogState = rememberMaterialDialogState()
    val removeMedication = rememberMaterialDialogState()
    val addMedication = rememberMaterialDialogState()
    var lastClickedMed by remember { mutableStateOf(-1) }
    var pickedStartDate by remember {
        mutableStateOf(LocalDate.now())
    }
    var pickedEndDate by remember {
        mutableStateOf(LocalDate.now())
    }
    val formattedStartDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(pickedStartDate)
        }
    }
    val formattedEndDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(pickedEndDate)
        }
    }
    val startDateDialogState = rememberMaterialDialogState()
    val endDateDialogState = rememberMaterialDialogState()
    var dayInput by remember {
        mutableStateOf(-1)
    }
    var monthInput by remember {
        mutableStateOf(-1)
    }
    var yearInput by remember {
        mutableStateOf(-1)
    }
    var startDayButtonText by remember {
        mutableStateOf("DD/MM/YY")
    }
    var endDayButtonText by remember {
        mutableStateOf("DD/MM/YY")
    }

    val colors = listOf(Color.White, Color(0xff64519A))
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
                                    MedicationPageEvent.ChangePage(
                                        "back"
                                    )
                                )
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
                            text = "Medication",
                            color = Color.Black,
                            fontSize = 35.sp,
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ){
                        if(state.usersMedications.size >= 1+(state.currentPage*9)){
                            Text(
                                text = state.usersMedications[0+(state.currentPage*9)].getName(), // Your 3-letter text
                                color = Color.Black,
                                fontSize = 16.sp,
                                modifier = Modifier.align(Alignment.TopEnd)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ){
                        if(state.usersMedications.size >= 2+(state.currentPage*9)){
                            Text(
                                text = state.usersMedications[1+(state.currentPage*9)].getName(), // Your 3-letter text
                                color = Color.Black,
                                fontSize = 16.sp,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ){
                        if(state.usersMedications.size >= 7+(state.currentPage*9)){
                            Text(
                                text = state.usersMedications[6+(state.currentPage*9)].getName(), // Your 3-letter text
                                color = Color.Black,
                                fontSize = 16.sp,
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.medication_shelf),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth(0.9f)
                            .padding(top = 70.dp),
                        contentScale = ContentScale.FillWidth
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .aspectRatio(1f)
                        ){
                            if(state.usersMedications.size >= 1+(state.currentPage*9)) {
                                Image(
                                    painter = painterResource(id = R.drawable.pills_container),
                                    contentDescription = "Pills Container Image",
                                    modifier = Modifier
                                        .height(100.dp)
                                        .align(Alignment.TopEnd)
                                        .pointerInput(Unit) {
                                            detectTapGestures(
                                                onLongPress = {
                                                    lastClickedMed = 0 + (state.currentPage * 9)
                                                    infoDialogState.show()
                                                },
                                                onTap = {
                                                    if (!state.usersMedications[0 + (state.currentPage * 9)].isTaken()) {
                                                        lastClickedMed = 0 + (state.currentPage * 9)
                                                        pillDialogState.show()
                                                    }
                                                }
                                            )
                                        }
                                )
                                if(state.usersMedications[0+(state.currentPage*9)].isTaken()) {
                                    Image(
                                        painter = painterResource(id = R.drawable.medication_taken_check),
                                        contentDescription = "Check Icon Image",
                                        modifier = Modifier
                                            .align(Alignment.CenterEnd)
                                            .offset(y = (-20).dp)
                                            .size(50.dp)
                                    )
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .aspectRatio(1f)
                        ){
                            if(state.usersMedications.size >= 2+(state.currentPage*9)) {
                                Image(
                                    painter = painterResource(id = R.drawable.pills_container),
                                    contentDescription = "Pills Container Image",
                                    modifier = Modifier
                                        .height(100.dp)
                                        .align(Alignment.TopCenter)
                                        .pointerInput(Unit) {
                                            detectTapGestures(
                                                onTap = {
                                                    if (!state.usersMedications[1 + (state.currentPage * 9)].isTaken()) {
                                                        lastClickedMed = 1 + (state.currentPage* 9)
                                                        pillDialogState.show()
                                                    }
                                                },
                                                onLongPress = {
                                                    lastClickedMed = 1 + (state.currentPage * 9)
                                                    infoDialogState.show()
                                                }
                                            )
                                        }
                                )
                                if(state.usersMedications[1+(state.currentPage*9)].isTaken()) {
                                    Image(
                                        painter = painterResource(id = R.drawable.medication_taken_check),
                                        contentDescription = "Check Icon Image",
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                            .offset(y = (-20).dp)
                                            .size(50.dp)
                                    )
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .aspectRatio(1f)
                        ){
                            if(state.usersMedications.size >= 7+(state.currentPage*9)){
                                Image(
                                    painter = painterResource(id = R.drawable.pills_container),
                                    contentDescription = "Pills Container Image",
                                    modifier = Modifier
                                        .height(100.dp)
                                        .align(Alignment.TopStart)
                                        .pointerInput(Unit) {
                                            detectTapGestures(
                                                onTap = {
                                                    if (!state.usersMedications[6 + (state.currentPage * 9)].isTaken()) {
                                                        lastClickedMed = 6 + (state.currentPage * 9)
                                                        pillDialogState.show()
                                                    }
                                                },
                                                onLongPress = {
                                                    lastClickedMed = 6 + (state.currentPage * 9)
                                                    infoDialogState.show()
                                                }
                                            )
                                        }
                                )
                                if(state.usersMedications[6+(state.currentPage*9)].isTaken()) {
                                    Image(
                                        painter = painterResource(id = R.drawable.medication_taken_check),
                                        contentDescription = "Check Icon Image",
                                        modifier = Modifier
                                            .align(Alignment.CenterStart)
                                            .offset(y = (-20).dp)
                                            .size(50.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ){
                        if(state.usersMedications.size >= 8+(state.currentPage*9)){
                            Text(
                                text = state.usersMedications[7+(state.currentPage*9)].getName(), // Your 3-letter text
                                color = Color.Black,
                                fontSize = 16.sp,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ){
                        if(state.usersMedications.size >= 3+(state.currentPage*9)){
                            Text(
                                text = state.usersMedications[2+(state.currentPage*9)].getName(), // Your 3-letter text
                                color = Color.Black,
                                fontSize = 16.sp,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ){
                        if(state.usersMedications.size >= 4+(state.currentPage*9)){
                            Text(
                                text = state.usersMedications[3+(state.currentPage*9)].getName(), // Your 3-letter text
                                color = Color.Black,
                                fontSize = 16.sp,
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.medication_shelf),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth(0.9f)
                            .padding(top = 70.dp),
                        contentScale = ContentScale.FillWidth
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .aspectRatio(1f)
                        ){
                            if(state.usersMedications.size >= 8+(state.currentPage*9)) {
                                Image(
                                    painter = painterResource(id = R.drawable.pills_container),
                                    contentDescription = "Pills Container Image",
                                    modifier = Modifier
                                        .height(100.dp)
                                        .align(Alignment.TopEnd)
                                        .pointerInput(Unit) {
                                            detectTapGestures(
                                                onTap = {
                                                    if (!state.usersMedications[7 + (state.currentPage * 9)].isTaken()) {
                                                        lastClickedMed = 7 + (state.currentPage * 9)
                                                        pillDialogState.show()
                                                    }
                                                },
                                                onLongPress = {
                                                    lastClickedMed = 7 + (state.currentPage * 9)
                                                    infoDialogState.show()
                                                }
                                            )
                                        }
                                )
                                if(state.usersMedications[7+(state.currentPage*9)].isTaken()) {
                                    Image(
                                        painter = painterResource(id = R.drawable.medication_taken_check),
                                        contentDescription = "Check Icon Image",
                                        modifier = Modifier
                                            .align(Alignment.CenterEnd)
                                            .offset(y = (-20).dp)
                                            .size(50.dp)
                                    )
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .aspectRatio(1f)
                        ){
                            if(state.usersMedications.size >= 3+(state.currentPage*9)) {
                                Image(
                                    painter = painterResource(id = R.drawable.pills_container),
                                    contentDescription = "Pills Container Image",
                                    modifier = Modifier
                                        .height(100.dp)
                                        .align(Alignment.TopCenter)
                                        .pointerInput(Unit) {
                                            detectTapGestures(
                                                onTap = {
                                                    if (!state.usersMedications[2].isTaken()) {
                                                        lastClickedMed = 2 + (state.currentPage * 9)
                                                        pillDialogState.show()
                                                    }
                                                },
                                                onLongPress = {
                                                    lastClickedMed = 2 + (state.currentPage * 9)
                                                    infoDialogState.show()
                                                }
                                            )
                                        }
                                )
                                if(state.usersMedications[2+(state.currentPage*9)].isTaken()) {
                                    Image(
                                        painter = painterResource(id = R.drawable.medication_taken_check),
                                        contentDescription = "Check Icon Image",
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                            .offset(y = (-20).dp)
                                            .size(50.dp)
                                    )
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .aspectRatio(1f)
                        ){
                            if(state.usersMedications.size >= 4+(state.currentPage*9)) {
                                Image(
                                    painter = painterResource(id = R.drawable.pills_container),
                                    contentDescription = "Pills Container Image",
                                    modifier = Modifier
                                        .height(100.dp)
                                        .align(Alignment.TopStart)
                                        .pointerInput(Unit) {
                                            detectTapGestures(
                                                onTap = {
                                                    if (!state.usersMedications[3 + (state.currentPage * 9)].isTaken()) {
                                                        lastClickedMed = 3 + (state.currentPage * 9)
                                                        pillDialogState.show()
                                                    }
                                                },
                                                onLongPress = {
                                                    lastClickedMed = 3 + (state.currentPage * 9)
                                                    infoDialogState.show()
                                                }
                                            )
                                        }
                                )
                                if(state.usersMedications[3+(state.currentPage*9)].isTaken()) {
                                    Image(
                                        painter = painterResource(id = R.drawable.medication_taken_check),
                                        contentDescription = "Check Icon Image",
                                        modifier = Modifier
                                            .align(Alignment.CenterStart)
                                            .offset(y = (-20).dp)
                                            .size(50.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ){
                        if(state.usersMedications.size >= 5+(state.currentPage*9)){
                            Text(
                                text = state.usersMedications[4+(state.currentPage*9)].getName(), // Your 3-letter text
                                color = Color.Black,
                                fontSize = 16.sp,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ){
                        if(state.usersMedications.size >= 6+(state.currentPage*9)){
                            Text(
                                text = state.usersMedications[5+(state.currentPage*9)].getName(), // Your 3-letter text
                                color = Color.Black,
                                fontSize = 16.sp,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ){
                        if(state.usersMedications.size >= 9+(state.currentPage*9)){
                            Text(
                                text = state.usersMedications[8+(state.currentPage*9)].getName(), // Your 3-letter text
                                color = Color.Black,
                                fontSize = 16.sp,
                                modifier = Modifier.align(Alignment.CenterStart)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.medication_shelf),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth(0.9f)
                            .padding(top = 70.dp),
                        contentScale = ContentScale.FillWidth
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .aspectRatio(1f)
                        ){
                            if(state.usersMedications.size >= 5+(state.currentPage*9)) {
                                Image(
                                    painter = painterResource(id = R.drawable.pills_container),
                                    contentDescription = "Pills Container Image",
                                    modifier = Modifier
                                        .height(100.dp)
                                        .align(Alignment.TopEnd)
                                        .pointerInput(Unit) {
                                            detectTapGestures(
                                                onTap = {
                                                    if (!state.usersMedications[4 + (state.currentPage * 9)].isTaken()) {
                                                        lastClickedMed = 4 + (state.currentPage * 9)
                                                        pillDialogState.show()
                                                    }
                                                },
                                                onLongPress = {
                                                    lastClickedMed = 4 + (state.currentPage * 9)
                                                    infoDialogState.show()
                                                }
                                            )
                                        }
                                )
                                if(state.usersMedications[4+(state.currentPage*9)].isTaken()) {
                                    Image(
                                        painter = painterResource(id = R.drawable.medication_taken_check),
                                        contentDescription = "Check Icon Image",
                                        modifier = Modifier
                                            .align(Alignment.CenterEnd)
                                            .offset(y = (-20).dp)
                                            .size(50.dp)
                                    )
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .aspectRatio(1f)
                        ){
                            if(state.usersMedications.size >= 6+(state.currentPage*9)) {
                                Image(
                                    painter = painterResource(id = R.drawable.pills_container),
                                    contentDescription = "Pills Container Image",
                                    modifier = Modifier
                                        .height(100.dp)
                                        .align(Alignment.TopCenter)
                                        .pointerInput(Unit) {
                                            detectTapGestures(
                                                onTap = {
                                                    if (!state.usersMedications[5 + (state.currentPage * 9)].isTaken()) {
                                                        lastClickedMed = 5 + (state.currentPage * 9)
                                                        pillDialogState.show()
                                                    }
                                                },
                                                onLongPress = {
                                                    lastClickedMed = 5 + (state.currentPage * 9)
                                                    infoDialogState.show()
                                                }
                                            )
                                        }
                                )
                                if(state.usersMedications[5+(state.currentPage*9)].isTaken()) {
                                    Image(
                                        painter = painterResource(id = R.drawable.medication_taken_check),
                                        contentDescription = "Check Icon Image",
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                            .offset(y = (-20).dp)
                                            .size(50.dp)
                                    )
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .aspectRatio(1f)
                        ){
                            if(state.usersMedications.size >= 9+(state.currentPage*9)) {
                                Image(
                                    painter = painterResource(id = R.drawable.pills_container),
                                    contentDescription = "Pills Container Image",
                                    modifier = Modifier
                                        .height(100.dp)
                                        .align(Alignment.TopStart)
                                        .pointerInput(Unit) {
                                            detectTapGestures(
                                                onTap = {
                                                    if (!state.usersMedications[8 + (state.currentPage * 9)].isTaken()) {
                                                        lastClickedMed = 8 + (state.currentPage * 9)
                                                        pillDialogState.show()
                                                    }
                                                },
                                                onLongPress = {
                                                    lastClickedMed = 8 + (state.currentPage * 9)
                                                    infoDialogState.show()
                                                }
                                            )
                                        }
                                )
                                if(state.usersMedications[8+(state.currentPage*9)].isTaken()) {
                                    Image(
                                        painter = painterResource(id = R.drawable.medication_taken_check),
                                        contentDescription = "Check Icon Image",
                                        modifier = Modifier
                                            .align(Alignment.CenterStart)
                                            .offset(y = (-20).dp)
                                            .size(50.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(170.dp)
                        .wrapContentSize(Alignment.BottomCenter),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // First Button
                    if(state.usersMedications.size>9) {
                        Box(
                            modifier = Modifier
                                .shadow(4.dp, shape = RoundedCornerShape(25.dp))
                                .size(50.dp)
                                .background(
                                    color =  if(state.currentPage==0){Color.LightGray }else{Color(0xff3A2F59)},
                                    shape = RoundedCornerShape(25.dp)
                                )
                                .clickable(onClick = {
                                    viewModel.onEvent(MedicationPageEvent.PrevPage(""))
                                })
                                .padding(10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "<",
                                    fontSize = 23.sp,
                                    textAlign = TextAlign.Center,
                                    color = if(state.currentPage==0){Color.Black}else{Color.White}
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))
                    }
                    // Add Medication Button
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .shadow(4.dp, shape = RoundedCornerShape(25.dp))
                            .background(
                                color = Color(0xffE2E0E8),
                                shape = RoundedCornerShape(25.dp)
                            )
                            .clickable(onClick = { addMedication.show() })
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Add \n" +
                                        "Medication",
                                fontSize = 19.sp,
                                textAlign = TextAlign.Center,
                                color = Color.Black
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Remove Medication Button
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .shadow(4.dp, shape = RoundedCornerShape(25.dp))
                            .background(
                                color = Color(0xffE2E0E8),
                                shape = RoundedCornerShape(25.dp)
                            )
                            .clickable(onClick = { removeMedication.show() })
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Remove \n" +
                                    "Medication",
                            fontSize = 19.sp,
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                    }
                    if(state.usersMedications.size>9) {
                        Spacer(modifier = Modifier.width(16.dp))

                        // Last Button
                        Box(
                            modifier = Modifier
                                .shadow(4.dp, shape = RoundedCornerShape(25.dp))
                                .size(50.dp)
                                .background(
                                    color = if(state.currentPage==(state.numOfPages-1)){Color.LightGray }else{Color(0xff3A2F59)},
                                    shape = RoundedCornerShape(25.dp)
                                )
                                .clickable(onClick = {
                                    viewModel.onEvent(MedicationPageEvent.NextPage(""))
                                })
                                .padding(10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = ">",
                                    fontSize = 23.sp,
                                    textAlign = TextAlign.Center,
                                    color = if(state.currentPage==(state.numOfPages-1)){Color.Black}else{Color.White}
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    MaterialDialog(
        dialogState = pillDialogState,
        shape = RoundedCornerShape(20.dp),
    ) {
        Column (
            modifier = Modifier
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            val nameOfMedToBeLogged = state.usersMedications[lastClickedMed].getName()
            if(state.usersMedications[lastClickedMed].getTimesShouldBeTakenToday()==1) {
                Text(
                    text = "Do you want to log $nameOfMedToBeLogged?",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }
            else{
                if(state.usersMedications[lastClickedMed].getTimesTakenToday()==0)
                {
                    Text(
                        text = "Do you want to log day's first $nameOfMedToBeLogged?",
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                }
                else
                {
                    Text(
                        text = "Do you want to log $nameOfMedToBeLogged again?",
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                }
            }
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = {
                    viewModel.onEvent(
                        MedicationPageEvent.MedicationTaken(lastClickedMed))
                    pillDialogState.hide()},
                shape = RoundedCornerShape(50),
                modifier = Modifier
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
                    text = "Yes",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }

    MaterialDialog(
        dialogState = infoDialogState,
        shape = RoundedCornerShape(20.dp)
    ) {
        val nameOfMedClicked = state.usersMedications[lastClickedMed].getName()
        val dosageQuantity = state.usersMedications[lastClickedMed].getDosageQuantity()
        val dosageUnitMeasurement = state.usersMedications[lastClickedMed].getDosageUnitMeasurement()
        val startDay = state.usersMedications[lastClickedMed].getStartDay()
        val endDay = state.usersMedications[lastClickedMed].getEndDay()
        val timesShouldBeTakenToday = state.usersMedications[lastClickedMed].getTimesShouldBeTakenToday()
        val notes = state.usersMedications[lastClickedMed].getNotes()
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp, bottom = 20.dp, end = 10.dp)
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
                    text = "Details",
                    color = Color.Black,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Name:",
                        color = Color.Black,
                        fontSize = 18.sp,
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
                        Text(
                            text = "$nameOfMedClicked",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Dosage:",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Box(
                    modifier = Modifier
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
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${dosageQuantity ?: ""}", // Show dosageQuantity
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                            Spacer(Modifier.width(10.dp))
                            Box(
                                modifier = Modifier
                                    .height(40.dp)
                                    .width(60.dp)
                                    .background(Color.White, RoundedCornerShape(10.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = dosageUnitMeasurement ?: "",
                                    color = Color.Black,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Start Date:",
                        color = Color.Black,
                        fontSize = 18.sp,
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
                        Text(
                            text = startDay ?: "",
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "End Date:",
                        color = Color.Black,
                        fontSize = 18.sp,
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
                        Text(
                            text = endDay ?: "",
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Per day:",
                        color = Color.Black,
                        fontSize = 18.sp,
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
                        Text(
                            text = "${timesShouldBeTakenToday ?: ""}",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Notes:",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth(1f)
                        .fillMaxHeight()
                        .padding(start = 20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .fillMaxHeight()
                            .background(Color.LightGray, RoundedCornerShape(20.dp))
                            .padding(start = 10.dp, top = 10.dp),
                        contentAlignment = Alignment.TopCenter
                    )
                    {
                        Text(
                            text = notes ?: "",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = {infoDialogState.hide()},
                shape = RoundedCornerShape(50),
                modifier = Modifier
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
                    text = "Close",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }

    MaterialDialog(
        dialogState = removeMedication,
        shape = RoundedCornerShape(20.dp)
    ) {
        val medicationsList = state.usersMedications
        var selectedItemIndex by remember { mutableStateOf(-1) }
        var removeButtonBackground by remember { mutableStateOf(Color.LightGray) }
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
                    text = "Remove Medication",
                    color = Color.Black,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            LazyColumn (Modifier.height(200.dp)){
                items(medicationsList) { medication ->
                    val index = medicationsList.indexOfFirst { it.getName() == medication.getName() }
                    val borderColor = if (index == selectedItemIndex) Color(0xFFFC8686) else Color.LightGray

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray, RoundedCornerShape(10.dp))
                        .border(5.dp, borderColor, RoundedCornerShape(10.dp))
                        .padding(top = 3.dp, bottom = 3.dp)
                        .clickable(onClick = {
                            selectedItemIndex = index
                            removeButtonBackground = Color(0xFFFC8686)
                        }),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Text(
                            text = medication.getName(),
                            color = Color.Black,
                            fontSize = 20.sp,
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
                    onClick = {removeMedication.hide()},
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
                            viewModel.onEvent(MedicationPageEvent.RemoveMedication(selectedItemIndex))
                            removeMedication.hide()
                        }},
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .height(60.dp)
                        .weight(0.9f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = removeButtonBackground
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 5.dp,
                        pressedElevation = 5.dp,
                    )
                ) {
                    Text(
                        text = "Remove",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
    MaterialDialog(
        dialogState = addMedication,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp, bottom = 20.dp, end = 10.dp),
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
                    text = "Add Medication",
                    color = Color.Black,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "Name:",
                            color = Color.Black,
                            fontSize = 18.sp,
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
                            contentAlignment = Alignment.CenterStart
                        )
                        {
                            TextField(
                                value = newNameText,
                                shape = RoundedCornerShape(20.dp),
                                onValueChange = {
                                    newNameText = it
                                    newName = newNameText
                                    viewModel.onEvent(
                                       MedicationPageEvent.UpdatedAddMedication(
                                           typeChanged = "name",
                                           newValueString = newName,
                                           newValueFloat = null,
                                           newValueInt = null
                                       )
                                    )
                                },
                                maxLines = 1,
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight(),
                                label = { Text(text = "Write pill name") },
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
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "Dosage:",
                            color = Color.Black,
                            fontSize = 18.sp,
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
                            contentAlignment = Alignment.CenterStart
                        )
                        {
                            TextField(
                                value = newDosageText,
                                onValueChange = {
                                    newDosageText = it
                                    try{
                                    newDosage = newDosageText.toFloat()
                                    viewModel.onEvent(
                                        MedicationPageEvent.UpdatedAddMedication(
                                            typeChanged = "dosage",
                                            newValueString = null,
                                            newValueFloat = newDosage,
                                            newValueInt = null
                                        )
                                    )
                                    }catch (e: NumberFormatException) {
                                    newDosage = 0f // or any other default value
                                }},
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
                        }
                    }

                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "Unit\n" + "of Measurement:",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Box(
                        Modifier
                            .fillMaxWidth(1f)
                            .height(60.dp)
                            .padding(start = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .fillMaxHeight()
                                .background(Color.LightGray, RoundedCornerShape(20.dp)),
                            contentAlignment = Alignment.CenterStart
                        )
                        {
                            TextField(
                                value = newDosageUnitMeasurementText,
                                onValueChange = {
                                    newDosageUnitMeasurementText = it
                                    newDosageUnitMeasurement = newDosageUnitMeasurementText
                                    viewModel.onEvent(
                                        MedicationPageEvent.UpdatedAddMedication(
                                            typeChanged = "unitMeasurement",
                                            newValueString = newDosageUnitMeasurement,
                                            newValueFloat = null,
                                            newValueInt = null
                                        )
                                    )                                 },
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
                                )
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "Start Date:",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Box(
                        Modifier
                            .fillMaxWidth(1f)
                            .height(60.dp)
                            .padding(start = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = {
                                startDateDialogState.show()
                            },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.LightGray,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = startDayButtonText,
                                fontSize = 15.sp,
                                color = Color.Black,
                                textAlign = TextAlign.Start
                            )
                        }
                        MaterialDialog(
                            dialogState = startDateDialogState,
                            buttons = {
                                positiveButton(text = "Ok") {
                                }
                                negativeButton(text = "Cancel")
                            }
                        ) {
                            datepicker(
                                initialDate = LocalDate.now(),
                                title = "Pick a date"
                            ) {
                                pickedStartDate = it
                                dayInput = pickedStartDate.dayOfMonth
                                monthInput = pickedStartDate.monthValue
                                yearInput = pickedStartDate.year
                                startDayButtonText =
                                    "%02d/%02d/%02d".format(monthInput,dayInput,yearInput % 100)
                                viewModel.onEvent(
                                    MedicationPageEvent.UpdatedAddMedication(
                                        typeChanged = "startDate",
                                        newValueString = formattedStartDate,
                                        newValueFloat = null,
                                        newValueInt = null
                                    )
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "End Date:",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Box(
                        Modifier
                            .fillMaxWidth(1f)
                            .height(60.dp)
                            .padding(start = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = {
                                endDateDialogState.show()
                            },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.LightGray,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = endDayButtonText,
                                fontSize = 15.sp,
                                color = Color.Black,
                                textAlign = TextAlign.Start
                            )
                        }
                        MaterialDialog(
                            dialogState = endDateDialogState,
                            buttons = {
                                positiveButton(text = "Ok") {
                                }
                                negativeButton(text = "Cancel")
                            }
                        ) {
                            datepicker(
                                initialDate = LocalDate.now(),
                                title = "Pick a date",
                                allowedDateValidator = {
                                    it >= pickedStartDate
                                }
                            ) {
                                pickedEndDate = it
                                dayInput = pickedEndDate.dayOfMonth
                                monthInput = pickedEndDate.monthValue
                                yearInput = pickedEndDate.year
                                endDayButtonText =
                                    "%02d/%02d/%02d".format(monthInput, dayInput,  yearInput % 100)
                                viewModel.onEvent(
                                    MedicationPageEvent.UpdatedAddMedication(
                                        typeChanged = "endDate",
                                        newValueString = formattedEndDate,
                                        newValueFloat = null,
                                        newValueInt = null
                                    )
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "Per day:",
                            color = Color.Black,
                            fontSize = 18.sp,
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
                            contentAlignment = Alignment.CenterStart
                        )
                        {
                            TextField(
                                value = newTimesPerDayText,
                                onValueChange = {
                                    newTimesPerDayText = it
                                    newTimesPerDay = newTimesPerDayText.toInt()
                                    viewModel.onEvent(
                                        MedicationPageEvent.UpdatedAddMedication(
                                            typeChanged = "perDay",
                                            newValueString = null,
                                            newValueFloat = null,
                                            newValueInt = newTimesPerDay
                                        )
                                    )
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
                        }
                    }

                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .fillMaxHeight()
                            .padding(top = 5.dp),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Text(
                            text = "Notes:",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Box(
                        Modifier
                            .fillMaxWidth(1f)
                            .height(150.dp)
                            .padding(start = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .fillMaxHeight()
                                .background(Color.LightGray, RoundedCornerShape(20.dp)),
                            contentAlignment = Alignment.CenterStart
                        )
                        {
                            TextField(
                                value = newNotesText,
                                onValueChange = {
                                    newNotesText = it
                                    newNotes= newNotesText
                                    viewModel.onEvent(
                                        MedicationPageEvent.UpdatedAddMedication(
                                            typeChanged = "notes",
                                            newValueString = newNotes,
                                            newValueFloat = null,
                                            newValueInt = null
                                        )
                                    )                                },
                                maxLines = 5,
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
                                )
                            )
                        }
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
            Button(
                onClick = {
                    if(newNameText != "" && newDosageText != "" && newDosageText != "" && newTimesPerDayText !="" && newNotesText != "" && newDosageUnitMeasurementText != "" && startDayButtonText != "DD/MM/YY" && endDayButtonText != "DD/MM/YY") {
                        viewModel.onEvent(
                            MedicationPageEvent.AddMedication("")
                        )
                    }
                    newNameText = ""
                    newDosageText = ""
                    newDosageText = ""
                    newTimesPerDayText =""
                    newNotesText = ""
                    newDosageUnitMeasurementText = ""
                    startDayButtonText = "DD/MM/YY"
                    endDayButtonText = "DD/MM/YY"
                    addMedication.hide()},
                shape = RoundedCornerShape(50),
                modifier = Modifier
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
        }
    }
}

