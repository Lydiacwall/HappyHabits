package com.example.happyhabits.feature_application.feature_food.presentation.food_search_screen

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.happyhabits.feature_application.presentation.util.Screen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FoodSearchView(
    navController: NavController,
    viewModel: FoodSearchViewmodel = hiltViewModel(),
) {
    val state by viewModel.state
    val searchInput = state.searchInput?:""
    val mealInput = state.meal?:""
    val colorsPurple = listOf(Color(0xffA586FD), Color(0xff64519A), Color(0xff645199))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = colorsPurple)
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
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center)
                    {
                        Box()
                        {
                            Row(
                                modifier = Modifier.clickable{
                                    val specificFoodJson = Json.encodeToString(state.specificFood)
                                    val encodedSpecificFoodJson = Uri.encode(specificFoodJson)
                                    navController.navigate(Screen.FoodPageScreen.route + "?specificFood=$encodedSpecificFoodJson")}
                            )
                            {
                                Text(
                                    text = "<",
                                    color = Color.White,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.padding(start = 20.dp, top = 15.dp)
                                )
                                Text(
                                    text = "Back",
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    modifier = Modifier.padding(top = 22.dp)
                                )
                            }
                        }

                        Text(
                            text = "Results for \"$searchInput\"",
                            color = Color.White,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 20.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(7.dp)
                ) {
                    items(state.foodsAccordingToSearch) { food ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .shadow(5.dp)
                                .clickable(onClick={
                                    navController.navigate(Screen.FoodDetailsScreen.route + "?searchedFood=$food&meal=$mealInput")
                                })
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = food,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}