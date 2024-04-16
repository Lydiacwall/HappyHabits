package com.example.happyhabits.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happyhabits.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.Placeholder


class SignIn : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {

                CreateSignInView()
            }
        }
}
@Preview
@Composable
fun CreateSignInView(){
    val colors = listOf(Color(0xffF8F7FA),Color(0xffA687FF))
    var emailInput by remember {
        mutableStateOf("")
    }
    var passwordInput by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = colors)
            )
            .padding(0.dp)
    )
    {
//        Row(
//            horizontalArrangement = Arrangement.End,
//            modifier = Modifier
//                .fillMaxHeight(0.6f)
//                .fillMaxWidth(0.2f)
//                .background(Color.White)
//        )
//        {
//            Box(
//                modifier = Modifier.fillMaxSize(),
//                contentAlignment = Alignment.TopEnd
//            ) {
////                Image(
////                    painter = painterResource(id = R.drawable.light_purple_corner_top),
////                    contentDescription = null,
////                    modifier = Modifier
////                        .graphicsLayer(rotationZ = 180f)
////                        .fillMaxHeight()
////                )
////                Image(
////                    painter = painterResource(id = R.drawable.dark_purple_corner_top),
////                    contentDescription = null,
////                    modifier = Modifier
////                        .graphicsLayer(rotationZ = 180f)
////                        .fillMaxSize(0.9f)
////                )
//            }
//        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.8f)
        )
        {
            Text(text = "Hello!",
                color= Color.Black,
                fontSize = 40.sp,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    lineHeight = 30.sp
                )
            )
            Text(text = "Sign into your account",
                color= Color.Black,
                fontSize = 30.sp,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    lineHeight = 40.sp
                )
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "E M A I L",
                    color= Color.Black,
                    fontSize = 15.sp,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 6.dp, bottom = 2.dp)
            )
            OutlinedTextField(
                value = emailInput,
                onValueChange = {
                    emailInput = it
                },
                maxLines = 1,
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .size(100.dp)
                    .background(Color.White)

            )


            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "P A S S W O R D",
                color= Color.Black,
                fontSize = 15.sp,
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 6.dp, bottom = 2.dp)
            )
            OutlinedTextField(
                value = passwordInput,
                onValueChange = {
                    passwordInput = it
                },
                maxLines = 1,
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .size(100.dp)

            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {
                          println(emailInput)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff8A6AE5),
                    contentColor = Color.White
                ),
                shape = ButtonDefaults.filledTonalShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color(0xff8A6AE5))
                    .shadow(4.dp))
            {
                Text(
                    text = "Login",
                    fontSize =25.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Not a member?",
                color = Color.White,
                fontSize =25.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Sign Up",
                color = Color(0xff8A6AE5),
                fontSize =27.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }

    }
}