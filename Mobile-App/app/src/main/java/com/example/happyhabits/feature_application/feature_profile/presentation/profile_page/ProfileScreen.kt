package com.example.happyhabits.feature_application.feature_profile.presentation.profile_page


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.happyhabits.R
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.zIndex
import com.example.happyhabits.feature_application.feature_profile.presentation.profile_page.ProfileEvent
import com.example.happyhabits.feature_application.feature_profile.presentation.profile_page.ProfileViewmodel
import com.example.happyhabits.feature_application.feature_workout.presentation.workout_pop_up_screen.WorkoutPopUpEvent
import com.example.happyhabits.feature_application.feature_workout.presentation.workout_screen.WorkoutPageEvent
import com.example.happyhabits.feature_application.home_page.HomePageEvent
import kotlin.io.path.Path
import kotlin.io.path.moveTo

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileView(
    navController: NavController,
    viewModel: ProfileViewmodel = hiltViewModel()
){
    val context = LocalContext.current
    val state by viewModel.state

    val firstName by remember {
        mutableStateOf(state.firstName)
    }

    val lastName by remember {
        mutableStateOf(state.lastName)
    }

    val email by remember {
        mutableStateOf(state.email)
    }

    val birthdate by remember {
        mutableStateOf(state.birthdate)
    }

    var newNotification = true

    val colors = listOf(Color(0xffF8F7FA), Color(0xffA687FF))
    val colorsPurple = listOf(Color(0xffB4A4E0), Color(0xff9686C3))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xffF2F1F6))
    )
    {
        Column (
            modifier = Modifier
                .fillMaxSize()
        ){
            Row (
                Modifier
                    .fillMaxHeight(0.1f)
                    .background(brush = Brush.verticalGradient(colors = colorsPurple))
            ) {
                Box(
                    Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight()
                )
                {
                    Text(
                        text = "Maria",
                        color = Color(0xffF2F1F6),
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 32.dp,start = 22.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(top = 32.dp, end = 13.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Absolute.Right
                ) {
                    Image(
                        painter = painterResource(R.drawable.barcode_icon),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(33.dp)
                    )
                    Box {
                        Image(
                            painter = painterResource(R.drawable.notification_icon),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(33.dp)
                        )
                        Row(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(end = 3.dp, top = 4.dp)
                        ) {
                            if (newNotification) {
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .background(
                                            Color(0xffff8c14),
                                            shape = MaterialTheme.shapes.small
                                        )
                                )
                            }
                        }
                    }
                    Image(
                        painter = painterResource(R.drawable.settings_icon),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(33.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.15f)
                    .fillMaxWidth()
                    .background(color = Color(0xff9686C3)),
                contentAlignment = Alignment.BottomCenter // Aligns content to the bottom center of the box
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arc_3),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Image(
                    painter = painterResource(id = R.drawable.user_maria),
                    contentDescription = null,
                    modifier = Modifier
                        .size(110.dp)
                        .align(Alignment.Center) // Aligns the image to the center horizontally
                        .offset(y = -16.dp) // Elevates the image from the bottom by 16.dp (adjust as needed)
                        .clip(CircleShape)
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .height(70.dp)
                        .padding(16.dp)
                        .border(2.dp, Color(0xFF64519A), shape = RoundedCornerShape(16.dp))
                        .background(
                            color = Color(0xFFD9D9D9),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "E D I T",
                        fontSize = 18.sp,
                        color = Color(0xFF64519A)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                )
                {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .background(Color.White, RoundedCornerShape(20.dp))
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().height(50.dp),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.3f)
                                    .fillMaxHeight()
                                    .padding(start=10.dp, top=12.dp)
                            ) {
                                Text(
                                    text = "Full Name:",
                                    color = Color.Black,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Box(
                                Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight()
                                    .padding(start = 20.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .fillMaxHeight()
                                        .background(Color.LightGray, RoundedCornerShape(20.dp)),
                                    contentAlignment = Alignment.Center
                                )
                                {
                                    
                                }
                            }

                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth().height(50.dp),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.3f)
                                    .fillMaxHeight()
                                    .padding(start=10.dp, top=12.dp)
                            ) {
                                Text(
                                    text = "Email:",
                                    color = Color.Black,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Box(
                                Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight()
                                    .padding(start = 20.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .fillMaxHeight()
                                        .background(Color.LightGray, RoundedCornerShape(20.dp)),
                                    contentAlignment = Alignment.Center
                                )
                                {

                                }
                            }

                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth().height(50.dp),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.3f)
                                    .fillMaxHeight()
                                    .padding(start=10.dp, top=12.dp)
                            ) {
                                Text(
                                    text = "Phone:",
                                    color = Color.Black,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Box(
                                Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight()
                                    .padding(start = 20.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .fillMaxHeight()
                                        .background(Color.LightGray, RoundedCornerShape(20.dp)),
                                    contentAlignment = Alignment.Center
                                )
                                {

                                }
                            }

                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth().height(50.dp),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.3f)
                                    .fillMaxHeight()
                                    .padding(start=10.dp, top=12.dp)
                            ) {
                                Text(
                                    text = "Birth Date:",
                                    color = Color.Black,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Box(
                                Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight()
                                    .padding(start = 20.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(1f)
                                        .fillMaxHeight()
                                        .background(Color.LightGray, RoundedCornerShape(20.dp)),
                                    contentAlignment = Alignment.Center
                                )
                                {

                                }
                            }

                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(120.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .shadow(4.dp, shape = RoundedCornerShape(16.dp))
                            .fillMaxHeight()
                            .background(
                                color = Color(0xff64519A),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.scan_qr_profile),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(85.dp)
                            )
                            Text(
                                text = "Scan QR",
                                fontSize = 20.sp,
                                color = Color(0xffF2F1F6)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .shadow(4.dp, shape = RoundedCornerShape(16.dp))
                            .background(
                                color = Color(0xff64519A),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.share_qr_profile),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(85.dp)
                            )
                            Text(
                                text = "Share QR",
                                fontSize = 20.sp,
                                color = Color(0xffF2F1F6)
                            )
                        }
                    }
                }
            }
        }
    }

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
        ){
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                Image(painter = painterResource(R.drawable.home_navbar),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .weight(1f)
                        .clickable(onClick = {
                            viewModel.onEvent(
                                ProfileEvent.ChangePage(
                                    "homepage",
                                    navController
                                )
                            )
                        })
                )
                Image(painter = painterResource(R.drawable.chat_navbar),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .weight(1f)
                        .clickable(onClick = {
                            viewModel.onEvent(
                                ProfileEvent.ChangePage(
                                    "messages",
                                    navController
                                )
                            )
                        })
                )
                Image(painter = painterResource(R.drawable.statistics_navbar),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .weight(1f)
                        .clickable(onClick = {
                            viewModel.onEvent(
                                ProfileEvent.ChangePage(
                                    "statistics",
                                    navController
                                )
                            )
                        })

                )
                Image(painter = painterResource(R.drawable.profile_navbar),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .weight(1f)
                        .clickable(onClick = {
                            viewModel.onEvent(
                                ProfileEvent.ChangePage(
                                    "profile",
                                    navController
                                )
                            )
                        })
                )
            }
        }

    }
}
