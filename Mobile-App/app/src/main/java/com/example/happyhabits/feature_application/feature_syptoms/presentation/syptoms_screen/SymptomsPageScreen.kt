package com.example.happyhabits.feature_application.feature_syptoms.presentation.syptoms_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import com.example.happyhabits.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.happyhabits.feature_application.presentation.util.Screen
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SymptomsPageView(
    navController: NavController,
    viewModel : SymptomsPageViewModel = hiltViewModel()
) {

    val state by viewModel.state
    val colors = listOf(Color.White, Color(0xff64519A))
    val newNotification = true
    val scrollState = rememberScrollState()
    var notes by remember {
        mutableStateOf(state.notes)
    }
    var symptom by remember {
        mutableStateOf(state.symptom)
    }

    var showDialog by remember {
        mutableStateOf(false)
    }
    var selectedSection by remember {
        mutableStateOf("")
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = colors)
            )
            .padding(0.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
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
                            text = "Symptoms",
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
            Spacer(modifier= Modifier.height(60.dp))
            //------------------------------Head---------------------------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color.White)
                    .clickable {
                        selectedSection = "Head and Neck"
                        showDialog = true
                    }


            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text="Head and Neck",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier= Modifier.padding(top=10.dp , bottom = 10.dp,start=20.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(80.dp)
                            .background(Color.LightGray, CircleShape)
                            ,
                        contentAlignment= Alignment.Center

                    ) {
                        Text("ICON")
                    }

                }
            }
            Spacer(modifier= Modifier.height(20.dp))
            // ----------------------------Abdomen ------------------------------------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(30.dp))

                    .background(Color.White)
                    .clickable {
                        selectedSection = "Abdomen"
                        showDialog = true
                    }


            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text="Abdomen",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier= Modifier.padding(top=10.dp , bottom = 10.dp,start=20.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(80.dp)
                            .background(Color.LightGray, CircleShape)
                        ,
                        contentAlignment= Alignment.Center

                    ) {
                        Text("ICON")
                    }

                }
            }
            Spacer(modifier= Modifier.height(20.dp))
            //------------------------------- Chest and Back ---------------------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(30.dp))

                    .background(Color.White)
                    .clickable {
                        selectedSection = "Chest and Back"
                        showDialog = true
                    }


            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text="Chest and Back",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier= Modifier.padding(top=10.dp , bottom = 10.dp,start=20.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(80.dp)
                            .background(Color.LightGray, CircleShape)
                        ,
                        contentAlignment= Alignment.Center

                    ) {
                        Text("ICON")
                    }

                }
            }
            Spacer(modifier= Modifier.height(20.dp))
            // ------------------------Pelvic and Genitourinary--------------------------------------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(30.dp))

                    .background(Color.White)
                    .clickable {
                        selectedSection = "Pelvic and Genitourinary"
                        showDialog = true
                    }


            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text="Pelvic and Genitourinary",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier= Modifier.padding(top=10.dp , bottom = 10.dp,start=20.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(80.dp)
                            .background(Color.LightGray, CircleShape)
                        ,
                        contentAlignment= Alignment.Center

                    ) {
                        Text("ICON")
                    }

                }
            }
            Spacer(modifier= Modifier.height(20.dp))
            //------------------- Limbs --------------------------------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(30.dp))

                    .background(Color.White)
                    .clickable {
                        selectedSection = "Limbs"
                        showDialog = true
                    }


            ) {
                Row(
                    modifier = Modifier
                        .clickable {
                            selectedSection = "Limbs"
                            showDialog = true
                        }
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text="Limbs",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier= Modifier.padding(top=10.dp , bottom = 10.dp,start=20.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(80.dp)
                            .background(Color.LightGray, CircleShape)
                        ,
                        contentAlignment= Alignment.Center

                    ) {
                        Text("ICON")
                    }

                }
            }
            Spacer(modifier= Modifier.height(20.dp))
            // -------------------- Neurological -------------------------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(30.dp))

                    .background(Color.White)
                    .clickable {
                        selectedSection = "Neurological"
                        showDialog = true
                    }


            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text="Neurological",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier= Modifier.padding(top=10.dp , bottom = 10.dp,start=20.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(80.dp)
                            .background(Color.LightGray, CircleShape)
                        ,
                        contentAlignment= Alignment.Center

                    ) {
                        Text("ICON")
                    }

                }
            }
            Spacer(modifier= Modifier.height(20.dp))
            // --------------------- Skin ----------------------------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(30.dp))

                    .background(Color.White)
                    .clickable {
                        selectedSection = "Skin"
                        showDialog = true
                    }


            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text="Skin",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier= Modifier.padding(top=10.dp , bottom = 10.dp,start=20.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(80.dp)
                            .background(Color.LightGray, CircleShape)
                        ,
                        contentAlignment= Alignment.Center

                    ) {
                        Text("ICON")
                    }

                }
            }
            Spacer(modifier= Modifier.height(20.dp))

        }
        if (showDialog) {
            if (selectedSection == "Skin") {

                ChecklistDialog(
                    section = selectedSection,
                    dialogState = remember { MaterialDialogState(true) },
                    symptomList = viewModel.getSkinList()
                ) {
                    showDialog = false
                }
            }
            if (selectedSection == "Limbs"){
                ChecklistDialog(
                    section = selectedSection,
                    dialogState = remember { MaterialDialogState(true) },
                    symptomList = viewModel.getLimbList()
                ) {
                    showDialog = false
                }

            }
            if (selectedSection == "Head and Neck"){
                ChecklistDialog(
                    section = selectedSection,
                    dialogState = remember { MaterialDialogState(true) },
                    symptomList = viewModel.getHandList()
                ) {
                    showDialog = false
                }

            }
            if (selectedSection == "Abdomen"){
                ChecklistDialog(
                    section = selectedSection,
                    dialogState = remember { MaterialDialogState(true) },
                    symptomList = viewModel.getAbdList()
                ) {
                    showDialog = false
                }

            }
            if (selectedSection == "Chest and Back"){
                ChecklistDialog(
                    section = selectedSection,
                    dialogState = remember { MaterialDialogState(true) },
                    symptomList = viewModel.getChestList()
                ) {
                    showDialog = false
                }

            }
            if (selectedSection == "Pelvic and Genitourinary"){
                ChecklistDialog(
                    section = selectedSection,
                    dialogState = remember { MaterialDialogState(true) },
                    symptomList = viewModel.getPelvList()
                ) {
                    showDialog = false
                }

            }
            if (selectedSection == "Neurological"){
                ChecklistDialog(
                    section = selectedSection,
                    dialogState = remember { MaterialDialogState(true) },
                    symptomList = viewModel.getNeuroList()
                ) {
                    showDialog = false
                }

            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChecklistDialog(section: String, dialogState: MaterialDialogState, symptomList: List<String>, onDismiss: () -> Unit) {
    val selectedOption = remember { mutableIntStateOf(-1) }
    var diary by remember { mutableStateOf("") }

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("OK") {
                onDismiss()
            }
            negativeButton("Cancel") {
                onDismiss()
            }
        },
        shape = RoundedCornerShape(20.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xffE9E3FB))
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = section,
                    fontSize = 24.sp,
                    color = Color(0xff64519A),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                symptomList.forEachIndexed { index, item ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        RadioButton(
                            selected = selectedOption.value == index,
                            onClick = { selectedOption.value = index }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = item,
                            style = TextStyle(fontSize = 18.sp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val lineHeight = 40.dp.toPx()
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
                            diary = if (lines.size <= 2) {
                                newText
                            } else {
                                lines.take(2).joinToString("\n")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .fillMaxHeight(),
                        label = { Text("Additional Notes") },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 40.sp
                        ),
                        maxLines=2

                    )
                }
            }
        }
    }
}











