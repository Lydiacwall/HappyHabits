package com.example.happyhabits.feature_application.feature_food.presentation.statistics_food

import android.os.Build
import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.happyhabits.R
import com.example.happyhabits.feature_application.feature_food.presentation.util.calculateHeightDp
import com.example.happyhabits.feature_application.presentation.util.Screen
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import com.example.happyhabits.feature_application.feature_food.presentation.util.PieChart


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FoodStatisticsPageView(
    navController: NavController,
    viewModel: FoodStatisticsViewmodel = hiltViewModel()
) {
    var dayInput by remember {
        mutableStateOf(-1)
    }
    var monthInput by remember {
        mutableStateOf(-1)
    }
    var yearInput by remember {
        mutableStateOf(-1)
    }
    var dateButtonText by remember {
        mutableStateOf("mm/dd/yy")
    }
    val state by viewModel.state
    val foodsList = state.foodList
    val meals = listOf("Breakfast", "Lunch", "Dinner", "Snacks")
    val colors = listOf(Color.White, Color(0xff64519A))
    val dateDialogState = rememberMaterialDialogState()
    val infoMaterialDialog = rememberMaterialDialogState()
    val daysStats = rememberMaterialDialogState()
    var lazyColumnHeightInDp = -1
    Log.d("FoodPageView", "Meals: $meals")
    Log.d("FoodPageView", "FoodsList: ${foodsList.joinToString("\n")}")
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
                .fillMaxSize()
        ) {
            Row(
                Modifier
                    .fillMaxHeight(0.13f)
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
                        Row(modifier = Modifier.clickable { navController.navigate(Screen.StatisticsPageScreen.route) })
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
                        Text(
                            text = "Food Statistics",
                            color = Color.Black,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                    }
                }
            }
            if(state.dateSelected== "mm/dd/yy") {
                dateDialogState.show()
            }
            else {
                Spacer(Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(start = 25.dp, end = 25.dp, top = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .shadow(5.dp, shape = RoundedCornerShape(26.dp))
                            .fillMaxHeight()
                            .background(
                                color = Color(0xFF776A9C),
                                shape = RoundedCornerShape(30.dp)
                            )
                            .clickable(onClick = {
                                viewModel.onEvent(FoodStatisticsEvent.GetTodaysStatistics(""))
                                daysStats.show()
                            }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.pie_chart_icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(bottom = 5.dp)
                            )
                            Text(
                                text = "Day's Stats",
                                fontSize = 20.sp,
                                color = Color(0xffF2F1F6)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable(onClick = { dateDialogState.show() })
                            .shadow(4.dp, shape = RoundedCornerShape(26.dp))
                            .background(
                                color = Color(0xFF776A9C),
                                shape = RoundedCornerShape(30.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.calendar_icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(bottom = 5.dp)
                            )
                            Text(
                                text = state.dateSelected,
                                fontSize = 20.sp,
                                color = Color(0xffF2F1F6)
                            )
                        }
                    }
                }
                Spacer(Modifier.height(15.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(meals) { mealName ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.9f),
                            contentAlignment = Alignment.Center
                        )
                        {
                            Column(
                                modifier = Modifier
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
                                        text = mealName,
                                        color = Color.Black,
                                        fontSize = 25.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                                lazyColumnHeightInDp = calculateHeightDp(mealName, foodsList)
                                if (lazyColumnHeightInDp != -1) {
                                    LazyColumn(
                                        Modifier
                                            .height(lazyColumnHeightInDp.dp)
                                            .padding(start = 7.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        items(foodsList) { food ->
                                            if (food.getMeal() == mealName) {
                                                Box(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .background(
                                                            Color.LightGray,
                                                            RoundedCornerShape(10.dp)
                                                        )
                                                        .padding(vertical = 7.dp),
                                                    contentAlignment = Alignment.CenterStart
                                                )
                                                {
                                                    Row(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .padding(
                                                                start = 10.dp,
                                                                end = 10.dp
                                                            ),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    )
                                                    {
                                                        Box(
                                                            modifier = Modifier
                                                                .fillMaxWidth(0.8f)
                                                                .padding(end = 5.dp)
                                                        )
                                                        {
                                                            Text(
                                                                text = if (food.getName().length <= 20) {
                                                                    food.getName()
                                                                } else {
                                                                    food.getName()
                                                                        .substring(
                                                                            0,
                                                                            15
                                                                        ) + "..."
                                                                },
                                                                color = Color.Black,
                                                                fontSize = 20.sp,
                                                                fontWeight = FontWeight.Normal
                                                            )
                                                        }
                                                        Row(
                                                            modifier = Modifier.fillMaxWidth(),
                                                            horizontalArrangement = Arrangement.Center,
                                                            verticalAlignment = Alignment.CenterVertically
                                                        ) {
                                                            Box(
                                                                modifier = Modifier
                                                                    .weight(0.5f)
                                                                    .padding(end = 3.dp),
                                                                contentAlignment = Alignment.CenterEnd
                                                            ) {
                                                                Image(
                                                                    painter = painterResource(id = R.drawable.info_icon),
                                                                    contentDescription = "info button",
                                                                    modifier = Modifier
                                                                        .height(30.dp)
                                                                        .align(Alignment.CenterEnd)
                                                                        .clickable(
                                                                            onClick = {
                                                                                viewModel.onEvent(
                                                                                    FoodStatisticsEvent.FoodForInfo(food.getFoodId(), food.getName()))
                                                                                infoMaterialDialog.show()
                                                                            }
                                                                        )
                                                                )
                                                            }
                                                        }
                                                    }
                                                }
                                                Spacer(modifier = Modifier.height(5.dp))
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }
    }
    MaterialDialog(
        dialogState = infoMaterialDialog,
        shape = RoundedCornerShape(20.dp),
        backgroundColor =  Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(20.dp))
            Text(
                text = state.foodForInfo.getName(),
                color = Color(0xff645199),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Calories:",
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = String.format(
                    "%.2f",
                    state.foodForInfo.getCalories()
                ) + " kcal",
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            )
            {
                Text(
                    text = "Macros(%):",
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),
                verticalArrangement = Arrangement.Center
            )
            {
                PieChart(
                    data = mapOf(
                        Pair("Fiber", state.foodForInfoMacros.fiberPercentage),
                        Pair("Protein", state.foodForInfoMacros.proteinPercentage),
                        Pair("Carbs", state.foodForInfoMacros.carbsPercentage),
                        Pair("Fats", state.foodForInfoMacros.fatsPercentage)
                    )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    viewModel.onEvent(FoodStatisticsEvent.FoodForInfo("",""))
                    infoMaterialDialog.hide()
                },
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .height(60.dp)
                    .padding(start = 20.dp, end = 5.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff645199)
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
            Spacer(Modifier.height(10.dp))
        }
    }
    MaterialDialog(
        dialogState = daysStats,
        shape = RoundedCornerShape(20.dp),
        backgroundColor =  Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(20.dp))
            Text(
                text = "Day's Stats",
                color = Color(0xff645199),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Calories:",
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = String.format(
                    "%.2f",
                    state.totalCalories
                ) + " kcal",
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            )
            {
                Text(
                    text = "Macros(%):",
                    color = Color.Black,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),
                verticalArrangement = Arrangement.Center
            )
            {
                PieChart(
                    data = mapOf(
                        Pair("Fiber", state.totalFiber),
                        Pair("Protein", state.totalProtein),
                        Pair("Carbs", state.totalCarbs),
                        Pair("Fats", state.totalFats)
                    )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    daysStats.hide()
                },
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .height(60.dp)
                    .padding(start = 20.dp, end = 5.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff645199)
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
            Spacer(Modifier.height(10.dp))
        }
    }
    MaterialDialog(
        dialogState = dateDialogState,
        shape = RoundedCornerShape(20.dp),
        autoDismiss = false,
        buttons = {
            positiveButton(text = "Ok", onClick ={
                viewModel.onEvent(FoodStatisticsEvent.RetrieveFoods(dateButtonText))
                dateDialogState.hide()
            })
            negativeButton(text = "Cancel", onClick = {
                if(state.dateSelected == "mm/dd/yy")
                {
                    dateDialogState.hide()
                    navController.navigate(Screen.StatisticsPageScreen.route)
                }
                else {
                    dateDialogState.hide()
                }
            })
        },
        onCloseRequest = {}
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date",
            allowedDateValidator = {
                it <= LocalDate.now()
            }
        ) {
            val pickedDate = it
            dayInput = pickedDate.dayOfMonth
            monthInput = pickedDate.monthValue
            yearInput = pickedDate.year
            dateButtonText = "%02d/%02d/%02d".format(monthInput, dayInput, yearInput % 100)
            viewModel.onEvent(FoodStatisticsEvent.dateSelected(dateButtonText))

        }
    }
}