package com.example.happyhabits.feature_application.feature_syptoms.presentation.syptoms

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import com.example.happyhabits.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.example.happyhabits.feature_application.feauture_sleep.presentation.sleep_screen.SleepPageViewModel
import com.example.happyhabits.feature_authentication.presentation.util.Screen

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun SymptomsPageView(
//    navController: NavController,
//    viewModel : SymptomsPageViewModel = hiltViewModel()
) {


    val colors = listOf(Color.White, Color(0xff64519A))
    var newNotification = true
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = colors)
            )
            .padding(0.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            Row(
                Modifier
                    .fillMaxHeight(0.2f)
            ) {
                Box(
                    Modifier
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight()
                )
                {
                    Column(modifier = Modifier.fillMaxSize())
                    {
                        Box()
                        {
                            Row(modifier = Modifier.clickable {
                                //navController.navigate(Screen.HomePageScreen.route)
                            })
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
                        }
                        Text(
                            text = "Symptoms",
                            color = Color.Black,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 20.dp)
                        )
                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(8.dp)
                )
                {
                    Box(
                        modifier = Modifier
                            .weight(0.9f)
                            .fillMaxHeight()
                            .padding(top = 45.dp)
                    )
                    {
                        Image(
                            painter = painterResource(R.drawable.barcode_icon),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxSize()
                                .size(50.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(0.9f)
                            .fillMaxHeight()
                            .padding(top = 53.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.notification_icon),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxSize()
                                .size(35.dp)
                        )
                        if (newNotification) {
                            Box(
                                modifier = Modifier
                                    .size(15.dp)
                                    .background(
                                        Color(0xffff8c14),
                                        shape = MaterialTheme.shapes.small
                                    )
                                    .align(Alignment.TopEnd)
                                    .padding(
                                        end = 16.dp,
                                        top = 16.dp
                                    ) // Adjust padding as needed
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(0.9f)
                            .fillMaxHeight()
                            .padding(top = 53.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.settings_icon),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(35.dp)
                        )
                    }
                }

            }
            Spacer(modifier= Modifier.height(60.dp))
            //------------------------------Head---------------------------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(30.dp))

                    .background(Color.White)


            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text="Head and Neck",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier= Modifier.padding(top=10.dp , bottom = 10.dp,start=20.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(80.dp)
                            .background(Color.LightGray, CircleShape)
                            ,
                        contentAlignment= Alignment.Center

                    ) {
                        Text("ICON")
                    }

                }
            }
            Spacer(modifier= Modifier.height(20.dp))
            // ----------------------------Abdomen ------------------------------------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(30.dp))

                    .background(Color.White)


            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text="Abdomen",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier= Modifier.padding(top=10.dp , bottom = 10.dp,start=20.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(80.dp)
                            .background(Color.LightGray, CircleShape)
                        ,
                        contentAlignment= Alignment.Center

                    ) {
                        Text("ICON")
                    }

                }
            }
            Spacer(modifier= Modifier.height(20.dp))
            //------------------------------- Chest and Back ---------------------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(30.dp))

                    .background(Color.White)


            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text="Chest and Back",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier= Modifier.padding(top=10.dp , bottom = 10.dp,start=20.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(80.dp)
                            .background(Color.LightGray, CircleShape)
                        ,
                        contentAlignment= Alignment.Center

                    ) {
                        Text("ICON")
                    }

                }
            }
            Spacer(modifier= Modifier.height(20.dp))
            // ------------------------Pelvic and Genitourinary--------------------------------------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(30.dp))

                    .background(Color.White)


            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text="Pelvic and Genitourinary",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier= Modifier.padding(top=10.dp , bottom = 10.dp,start=20.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(80.dp)
                            .background(Color.LightGray, CircleShape)
                        ,
                        contentAlignment= Alignment.Center

                    ) {
                        Text("ICON")
                    }

                }
            }
            Spacer(modifier= Modifier.height(20.dp))
            //------------------- Limbs --------------------------------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(30.dp))

                    .background(Color.White)


            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text="Limbs",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier= Modifier.padding(top=10.dp , bottom = 10.dp,start=20.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(80.dp)
                            .background(Color.LightGray, CircleShape)
                        ,
                        contentAlignment= Alignment.Center

                    ) {
                        Text("ICON")
                    }

                }
            }
            Spacer(modifier= Modifier.height(20.dp))
            // -------------------- Neurological -------------------------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(30.dp))

                    .background(Color.White)


            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text="Neurological",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier= Modifier.padding(top=10.dp , bottom = 10.dp,start=20.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(80.dp)
                            .background(Color.LightGray, CircleShape)
                        ,
                        contentAlignment= Alignment.Center

                    ) {
                        Text("ICON")
                    }

                }
            }
            Spacer(modifier= Modifier.height(20.dp))
            // --------------------- Skin ----------------------------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .clip(RoundedCornerShape(30.dp))

                    .background(Color.White)


            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text="Skin",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier= Modifier.padding(top=10.dp , bottom = 10.dp,start=20.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(80.dp)
                            .background(Color.LightGray, CircleShape)
                        ,
                        contentAlignment= Alignment.Center

                    ) {
                        Text("ICON")
                    }

                }
            }
            Spacer(modifier= Modifier.height(20.dp))

        }


















    }//end bigger box
}//end function

