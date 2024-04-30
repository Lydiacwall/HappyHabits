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
                .fillMaxSize()
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Choose Your Role",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = font,
                modifier = Modifier
                    .padding(top=100.dp)

            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(

                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { selectedRole = "user" }
                    .graphicsLayer {
                        this.alpha = alpha1
                        this.clip = true
                    }
            )
            {
                Text(
                    text = "SIMPLE USER",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = font,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 30.dp)
                )
                Spacer(modifier = Modifier.width(16.dp)) // Adjust spacing as necessary
                Image(
                    painter = painterResource(R.drawable.simple_user_photo),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(250.dp)
                )


            }


            Row(

                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { selectedRole = "doctor" }
                    .graphicsLayer {
                        this.alpha = alpha2
                        this.clip = true
                    }
            )
            {
                Image(
                    painter = painterResource(R.drawable.doctor_photo),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(250.dp)
                        .padding(start = 7.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "DOCTOR",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = font,
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 12.dp)
                )


            }
            if(selectedRole!= "") {
                Spacer(modifier = Modifier.height(30.dp))
                Box( modifier = Modifier
                    .fillMaxSize()) {
                    Button(
                        onClick = {
                                  if(selectedRole=="user"){
                                      navController.navigate(Screen.AddUserScreen.route)
                                  }else{
                                      navController.navigate(Screen.AddDoctorScreen.route)
                                  }
                        },
                        shape = RoundedCornerShape(15),
                        modifier = Modifier
                            .align(Alignment.TopCenter)
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
                            text="Continue",
                            color= Color.Black,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Normal
                        )
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


}