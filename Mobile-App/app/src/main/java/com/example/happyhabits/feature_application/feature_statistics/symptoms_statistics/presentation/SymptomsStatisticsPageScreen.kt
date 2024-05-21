package com.example.happyhabits.feature_application.feature_statistics.symptoms_statistics.presentation

import android.graphics.fonts.FontFamily
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happyhabits.R
import kotlinx.coroutines.delay

@Composable
@Preview
fun SymptomsStatisticsPageScreen() {
    var fill by remember { mutableStateOf(false) }

        // when the screen will load
    LaunchedEffect(Unit) {
        delay(500)
        fill = true
    }
    val colors = listOf(Color.White, Color(0xff64519A))

    val categories= listOf("Head and Neck","Abdomen","Limbs","Pelvic","Neurological")
    val symptomsH = listOf("Headache","Sore Throat")
    val symptomsA = listOf("Abdominal pain","Nausea","Bloating")

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(colors = colors))
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Row(
                    modifier = Modifier.clickable {
                        //navController.navigate(Screen.HomePageScreen.route)
                    }
                ) {
                    Text(
                        text = "<",
                        color = Color(0xFF544C4C),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Normal,
                    )
                    Text(
                        text = "Back",
                        color = Color(0xFF544C4C),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Normal,
                    )
                }

                Text(
                    text = " Sleep ",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Row {
                    Text(
                        text = "Top ",
                        fontSize = 30.sp,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif
                    )
                    Text(
                        text = "5",
                        color = Color(0xff64519A),
                        fontSize = 33.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif
                    )
                    Text(
                        text = " Categories",
                        fontSize = 30.sp,
                        fontFamily = androidx.compose.ui.text.font.FontFamily.SansSerif
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                categories.forEach { item ->
                    if (item == "Head and Neck") {
                        CategoryBox(fill = fill, symptoms = symptomsH, image = R.drawable.head_neck,0.9f)
                    }
                    if(item =="Abdomen"){
                        CategoryBox(fill = fill, symptoms = symptomsH, image = R.drawable.abdomen,0.5f)
                    }
                    if(item =="Pelvic"){
                        CategoryBox(fill = fill, symptoms = symptomsH, image = R.drawable.pelvic_icon,0.4f)
                    }
                    if(item=="Skin"){
                        CategoryBox(fill = fill, symptoms = symptomsH, image = R.drawable.skin_icon,0.2f)
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryBox(fill: Boolean, symptoms: List<String>, image: Int, percentage : Float) {
    val fillFraction by animateFloatAsState(
        targetValue = if (fill) percentage else 0f,
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(Color.White)
            .clickable { /* Handle click if needed */ }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fillFraction)
                .background(Color.Red)
                .align(Alignment.BottomStart)
        )
        Image(
            painter = painterResource(id = image),
            contentDescription = "Category Icon",
            modifier = Modifier
                .align(Alignment.Center)
                .padding(10.dp)
        )
    }

    Spacer(modifier = Modifier.height(20.dp))
}