package com.example.happyhabits.feature_authentication.presentation.choose_role

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.happyhabits.R
import com.example.happyhabits.feature_authentication.presentation.util.Screen

@Composable
fun ChooseRoleView(
    navController: NavController
) {
    var selectedRole by remember{ mutableStateOf("") }
    val font = FontFamily(
        Font(R.font.josefinsans_bold, FontWeight.Bold),
    )
    val colors = listOf(Color(0xffA586FD), Color(0xff64519A), Color(0xff645199))
    var alpha1= 1f
    var alpha2=1f


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = colors)
            )

    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Choose Your Role",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = font

            )
            Spacer(modifier = Modifier.height(40.dp))
            Row(

                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    .clickable { selectedRole = "user" }
                    .graphicsLayer {
                        this.alpha = alpha1
                        this.clip = true
                    }
            )
            {
                Box(modifier = Modifier
                    .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "USER",
                        color = Color.White,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = font
                    )
                }
                Box(modifier = Modifier
                    .weight(1f),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Image(
                        painter = painterResource(R.drawable.simple_user_photo_trim),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize(0.92f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .clickable { selectedRole = "doctor" }
                    .graphicsLayer {
                        this.alpha = alpha2
                        this.clip = true
                    }
            )
            {
                Box(modifier = Modifier
                    .weight(1f),
                    contentAlignment = Alignment.CenterStart
                )
                {
                    Image(
                        painter = painterResource(R.drawable.doctor_photo_trim),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Box(modifier = Modifier
                    .weight(1f),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(
                        text = "DOCTOR",
                        color = Color.White,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = font
                    )
                }

            }
            Spacer(Modifier.height(15.dp))
            Box( modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f),
                contentAlignment = Alignment.Center
            ) {
                if(selectedRole!= "") {
                    Button(
                        onClick = {
                            if (selectedRole == "user") {
                                navController.navigate(Screen.AddUserScreen.route + "?type=${0}")
                            } else {
                                navController.navigate(Screen.AddUserScreen.route + "?type=${1}")
                            }
                        },
                        shape = RoundedCornerShape(15),
                        modifier = Modifier
                            .width(300.dp)
                            .height(55.dp)
                            .shadow(4.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF8E1FB)

                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 20.dp,
                            pressedElevation = 20.dp,
                        )

                    )
                    {
                        Text(
                            text = "Continue",
                            color = Color.Black,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
            if(selectedRole=="user"){
                alpha2=0.3f
            }
            else if(selectedRole=="doctor"){
                alpha1=0.3f
            }
        }
    }

}