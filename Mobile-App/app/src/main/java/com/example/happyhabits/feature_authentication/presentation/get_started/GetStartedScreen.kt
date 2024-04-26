package com.example.happyhabits.feature_authentication.presentation.get_started

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.happyhabits.R
import com.example.happyhabits.feature_authentication.presentation.util.Screen

@Composable
fun GetStartedView(
    navController: NavController
) {
    val first = FontFamily(
        Font(R.font.oi_regular, FontWeight.Bold),
    )
    val colors = listOf(Color(0xffA586FD), Color(0xff64519A), Color(0xff645199))
    val letter_color = Color(0xff3A2F59)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = colors)
            )
    ) {

        Text(
            text = "Happy Habits",
            color = letter_color,
            fontSize = 46.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontFamily = first,
                lineHeight = 50.sp
            )
        )

        Image(
            painter = painterResource(R.drawable.logoscreen_icon),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.Center)
                .size(250.dp)


        )

        Text(
            text = "Keep track of what is important to you!",
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 50.dp, end = 50.dp)
                .padding(bottom = 60.dp)

        )
        Button(
            onClick = {
                navController.navigate(Screen.LoginScreen.route)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff8A6AE5),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(
                vertical = 15.dp, // The padding inside the button, vertical
                horizontal = 90.dp // The padding inside the button, horizontal
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 20.dp,
                pressedElevation = 20.dp,
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 150.dp)

        ) {
            Text(
                text = "Get Started",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
