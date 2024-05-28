package com.example.happyhabits.feature_application.presentation.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.happyhabits.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController

@Composable
fun BottomNavBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize() // Adjust bottom padding as needed
            .wrapContentSize(Alignment.BottomCenter)
            .zIndex(1f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
                .padding(5.dp)
                .background(
                    Color(0xffE9E0FF),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(R.drawable.home_navbar),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .weight(1f)
                        .clickable(onClick = {
                            navController.navigate(Screen.HomePageScreen.route)
                        })
                )
                Image(
                    painter = painterResource(R.drawable.chat_navbar),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .weight(1f)
                        .clickable(onClick = {
                            navController.navigate((Screen.InboxPageScreen.route))
                        })
                )
                Image(
                    painter = painterResource(R.drawable.statistics_navbar),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .weight(1f)
                        .clickable(onClick = {
                            navController.navigate(Screen.StatisticsPageScreen.route)
                        })
                )
                Image(
                    painter = painterResource(R.drawable.profile_navbar),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .weight(1f)
                        .clickable(onClick = {
                            navController.navigate(Screen.ProfilePageScreen.route)

                        })
                )
            }
        }
    }
}