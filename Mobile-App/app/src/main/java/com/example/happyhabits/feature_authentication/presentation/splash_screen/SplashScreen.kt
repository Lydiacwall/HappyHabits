package com.example.happyhabits.feature_authentication.presentation.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happyhabits.R

@Composable
fun SplashScreen() {
    ///////////////////////////////////////////////////SPLASH SCREEN/////////////////////////////////////////////////
    val colorsPurple = listOf(Color(0xffA586FD), Color(0xff64519A), Color(0xff645199))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = colorsPurple)
            ),
        contentAlignment = Alignment.Center
    ) {
        BoxWithConstraints {
            val imageSize = maxWidth * 0.8f // Adjust the size as needed
            Image(
                painter = painterResource(id = R.drawable.happy_habits_logo), // Replace 'your_image' with your image resource
                contentDescription = null, // Provide appropriate content description
                modifier = Modifier.size(imageSize)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Happy Habits",
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 400.dp),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                lineHeight = 50.sp,
                fontFamily =  FontFamily.SansSerif)
        )
    }
}