package com.example.happyhabits.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.happyhabits.LoginScreen
import com.example.happyhabits.ui.theme.HappyHabitsTheme
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.happyhabits.R


val First = FontFamily(
    Font(R.font.oi_regular, FontWeight.Bold),
)

class LogoView {
    class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                CreateView()
                }
            }
        }
    }
@Preview
@Composable
fun CreateView(){

    val background_color = Color(0xff8A6AE5)
    val letter_color = Color(0xff3A2F59)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background_color)
    ) {

        Text(
            text = "Happy Habits",
            color = letter_color,
            fontSize = 46.sp,
            textAlign = TextAlign.Center,
            modifier =  Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontFamily = First,
                lineHeight = 50.sp
            )
        )

    Image(
            painter=painterResource (R.drawable.logoscreen_icon),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.Center)
                .size(250.dp)


        )

        Text(text = "Keep track of what is important to you!",
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start= 50.dp , end= 50.dp )
                .padding(bottom = 60.dp)




        )
    }
}


