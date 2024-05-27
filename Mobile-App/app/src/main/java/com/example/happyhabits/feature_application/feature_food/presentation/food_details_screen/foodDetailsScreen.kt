package com.example.happyhabits.feature_application.feature_food.presentation.food_details_screen

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.happyhabits.feature_application.feature_food.domain.model.Measurement
import com.example.happyhabits.feature_application.presentation.util.Screen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.example.happyhabits.feature_application.feature_food.presentation.util.PieChart
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FoodDetailsView(
    navController: NavController,
    viewModel: FoodDetailsViewmodel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state
    val searchInput = state.searchInput?:""
    var quantity by remember { mutableStateOf(TextFieldValue("")) }
    var expanded by remember { mutableStateOf(false) }
    var selectedMeasurement by remember { mutableStateOf<Measurement?>(null) }
    var quantityChosenText by remember { mutableStateOf("") }
    var measurementChosenText by remember { mutableStateOf("") }
    val colorsPurple = listOf(Color(0xffA586FD), Color(0xff64519A), Color(0xff645199))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(0xffEDEDED)
            )
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                Modifier
                    .fillMaxHeight(0.1f)
            )
            {
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
                                        if (state.foodDefined) {
                                            val specificFoodJson =
                                                Json.encodeToString(state.specificFood)
                                            val encodedSpecificFoodJson = Uri.encode(specificFoodJson)
                                            navController.navigate(Screen.FoodPageScreen.route + "?specificFood=$encodedSpecificFoodJson")
                                        } else {
                                            navController.navigate(Screen.FoodPageScreen.route)
                                        }
                                    }
                                )
                            {
                                Text(
                                    text = "<",
                                    color = Color.Black,
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.padding(start = 20.dp, top = 24.dp)
                                )
                                Text(
                                    text = "Back",
                                    color = Color.Black,
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.padding(top = 31.dp)
                                )
                            }
                        }
                    }

                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box (modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(top = 10.dp)
                    .shadow(5.dp, shape = RoundedCornerShape(20.dp))
                    .background(
                        brush = Brush.verticalGradient(colorsPurple),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clip(RoundedCornerShape(20.dp))
                ){
                    Column (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = state.searchInput?:"",
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        TextField(
                            value = quantityChosenText,
                            onValueChange = {
                                quantityChosenText = it
                                val quantityToSend = if (quantityChosenText.isNotEmpty() && quantityChosenText.all { it.isDigit() }) {
                                    quantityChosenText.toFloat()
                                } else {
                                    0f
                                }
                                viewModel.onEvent(FoodDetailsEvent.QuantityChanged(quantityToSend))
                            },
                            maxLines = 1,
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            label = {
                                Text(
                                    text = "Type Quantity",
                                    fontSize = 20.sp
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
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(modifier = Modifier
                            .fillMaxWidth(0.9f))
                        {
                            ExposedDropdownMenuBox(expanded=expanded, onExpandedChange={ expanded = !expanded})
                            {
                                TextField(
                                    modifier = Modifier
                                        .menuAnchor()
                                        .fillMaxWidth(),
                                    value =measurementChosenText,
                                    shape = RoundedCornerShape(20.dp),
                                    onValueChange={},
                                    readOnly=true,
                                    label = {
                                        Text(
                                            text = "Chose Measurement",
                                            fontSize = 20.sp
                                        )
                                    },
                                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                                        cursorColor = Color.Gray,
                                        unfocusedLabelColor = Color.Gray,
                                        focusedLabelColor = Color.Black,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        unfocusedContainerColor = Color.White,
                                        focusedContainerColor = Color.White,
                                        focusedTextColor = Color.Black),
                                    trailingIcon= { ExposedDropdownMenuDefaults.TrailingIcon(expanded= expanded)})
                                ExposedDropdownMenu(expanded=expanded, onDismissRequest = {expanded = false}, modifier = Modifier
                                    .background(Color.White)
                                    .fillMaxWidth()){
                                    state.dataBaseFood.getMeasurements()?.forEach { measurement ->
                                        DropdownMenuItem(
                                            text = {Text(text = measurement.label + " ("+ String.format("%.2f", measurement.weight)+")", color = Color.Black)},
                                            onClick = {
                                                measurementChosenText = measurement.label
                                                viewModel.onEvent(FoodDetailsEvent.MeasurementChosen(measurement))
                                                expanded = false
                                            },
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
                                .padding(top = 10.dp),
                            contentAlignment = Alignment.Center
                        ) {

                            Button(
                                onClick = {
                                    if(quantityChosenText!=""&&measurementChosenText!="") {
                                        viewModel.onEvent(FoodDetailsEvent.FoodChosen(""))
                                    }},
                                shape = RoundedCornerShape(50),
                                modifier = Modifier
                                    .height(60.dp)
                                    .padding(start = 20.dp, end = 5.dp),

                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White
                                ),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 5.dp,
                                    pressedElevation = 5.dp,
                                )
                            ) {
                                Text(
                                    text = "Set",
                                    color = Color.Black,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                }
                Spacer(modifier = Modifier.height(15.dp))

                if(state.foodDefined) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.95f)
                            .shadow(5.dp, shape = RoundedCornerShape(20.dp))
                            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
                            .clip(RoundedCornerShape(20.dp)),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(25.dp))
                        Text(
                            text = "Calories",
                            color = Color.Black,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Text(
                            text = String.format(
                                "%.2f",
                                state.specificFood.getCalories()
                            ) + " kcal",
                            color = Color.Black,
                            fontSize = 27.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Macros",
                            color = Color.Black,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                        PieChart(
                            data = mapOf(
                                Pair("Fiber", state.specificFoodMarcos.fiberPercentage),
                                Pair("Protein", state.specificFoodMarcos.proteinPercentage),
                                Pair("Carbs", state.specificFoodMarcos.carbsPercentage),
                                Pair("Fats", state.specificFoodMarcos.fatsPercentage)
                            )
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Button(
                                onClick = {
                                    val specificFoodJson = Json.encodeToString(state.specificFood)
                                    val encodedSpecificFoodJson = Uri.encode(specificFoodJson)
                                    navController.navigate(Screen.FoodPageScreen.route + "?specificFood=$encodedSpecificFoodJson")
                                },
                                shape = RoundedCornerShape(40),
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .fillMaxWidth(0.5f)
                                    .height(75.dp),

                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xff645199)
                                ),
                                elevation = ButtonDefaults.buttonElevation(
                                    defaultElevation = 3.dp,
                                    pressedElevation = 3.dp,
                                )

                            )
                            {
                                Text(
                                    text = "save food",
                                    color = Color.White,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}
