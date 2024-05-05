package com.example.happyhabits.feature_application.home_page

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.happyhabits.R
import com.example.happyhabits.feature_application.feature_workout.presentation.workout_pop_up_screen.WorkoutPopUpViewmodel
import com.example.happyhabits.feature_authentication.domain.model.Type
import com.example.happyhabits.feature_authentication.presentation.login.LoginViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.delay
import org.w3c.dom.Text
import com.example.happyhabits.feature_application.presentation.util.Screen
import com.example.happyhabits.feature_authentication.domain.model.User
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomePageView(
    navController: NavController,
    viewModel: HomePageViewmodel = hiltViewModel()
){
    val context = LocalContext.current

    var newNotification = true

    val streakCount = 0;
    var streakText = "";
    if (streakCount==0){
        streakText = "No streak yet!"
    }
    else{
        streakText= "Great work!"
    }
    val currentUser = User("1234", "Miltos", "Tsolkas", "yuriuser", "tsolkas@gmail.com", Type.CLIENT, birthDate = "29/03/2002")

    val colors = listOf(Color(0xffF8F7FA), Color(0xffA687FF))
    val colorsCategories = listOf(Color(0xffF8F7FA), Color(0xff5734BA))
    val currentDateTime = LocalDateTime.now()
    val formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("E, MMM dd, yyyy", Locale.ENGLISH))
/////////////////////////////////////////////////////////////LOGN IN////////////////////////////////////////////////////////////////////////////////
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = colors)
            )
            .padding(0.dp)
    )
    {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ){
            Row (
                Modifier
                    .fillMaxHeight(0.2f)){
                Box (
                    Modifier
                        .fillMaxWidth(0.7f)
                        .fillMaxHeight()
                        .padding(top = 30.dp, start = 20.dp))
                {
                    Column (modifier = Modifier.fillMaxSize()){
                        Text(text = formattedDateTime, color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Normal)
                        Text(text="Hello ${currentUser.firstName}!", color = Color.Black, fontSize = 35.sp, fontWeight = FontWeight.Bold)
                    }

                }
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(8.dp))
                {
                    Box(
                        modifier = Modifier
                            .weight(0.9f)
                            .fillMaxHeight()
                            .padding(top = 45.dp)
                    )
                    {
                        Image(painter = painterResource(R.drawable.barcode_icon),
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
                    )  {
                        Image(painter = painterResource(R.drawable.notification_icon),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxSize()
                                .size(35.dp)
                        )
                        if (newNotification)
                        {
                            Box(
                                modifier = Modifier
                                    .size(15.dp)
                                    .background(
                                        Color(0xffff8c14),
                                        shape = MaterialTheme.shapes.small
                                    )
                                    .align(Alignment.TopEnd)
                                    .padding(end = 16.dp, top = 16.dp) // Adjust padding as needed
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(0.9f)
                            .fillMaxHeight()
                            .padding(top = 53.dp)
                    )  {
                        Image(painter = painterResource(R.drawable.settings_icon),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(35.dp)
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.height(20.dp))
            Box (modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(start = 13.dp, end = 13.dp))
            {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(120.dp)
                        .shadow(5.dp,RoundedCornerShape(12.dp))
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFFF2EFFA), // Start color
                                    Color(0xFFFFC79C)  // End color
                                )
                            ), shape = RoundedCornerShape(12.dp)
                        )
                ){
                    Row(modifier = Modifier.fillMaxSize())
                    {
                        Box (
                            Modifier
                                .fillMaxWidth(0.75f)
                                .fillMaxHeight()
                                .padding(top = 10.dp, start = 10.dp))
                        {
                            Column (
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center
                            ){
                                Text(text = streakText, color = Color.Black, fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
                                Spacer(modifier = Modifier.height(7.dp))
                                Text(text="Keep logging to grow your streak ", color = Color.Black, fontSize = 19.sp, fontWeight = FontWeight.Normal)
//                                Spacer(modifier = Modifier.height(3.dp))
//                                Text(text="View Streaks ", color = Color(0xFFF89F5B), fontSize = 22.sp, fontWeight = FontWeight.Bold)

                            }
                        }
                        Box (
                            Modifier
                                .fillMaxWidth(1f)
                                .fillMaxHeight()
                                .padding(top = 15.dp, start = 10.dp, bottom = 15.dp))
                        {
                            Image(
                                    painter = painterResource(R.drawable.streak_fire),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                )
                                Text(
                                    text = streakCount.toString(),
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(
                                            top = 25.dp,
                                            start = 17.dp,
                                            end = 14.dp,
                                            bottom = 3.dp
                                        ),
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                        }
                    }

                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .padding(start = 13.dp, end = 13.dp)
                    .background(
                        brush = Brush.verticalGradient(colorsCategories),
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Text(
                    text = "Categories",
                    color = Color.Black, fontSize = 30.sp, fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(bottom = 3.dp, start = 13.dp, end = 13.dp, top = 16.dp)
                        .background(color = Color.Transparent)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .verticalScroll(rememberScrollState())
                        .background(color = Color.Transparent)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {navController.navigate("sleep_page_screen")}
                            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    ) {
                        Row (modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically)
                        {
                            Box (
                                modifier = Modifier
                                    .fillMaxWidth(0.2f)
                                    .fillMaxHeight()
                                    .padding(7.dp)
                                    ,
                                contentAlignment = Alignment.Center

                            ){
                                Box(
                                    modifier = Modifier
                                        .size(50.dp) // Set the size of the circle
                                        .background(Color.LightGray, shape = CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    // Your image goes here
                                    Image(
                                        painter = painterResource(id = R.drawable.sleep_icon_purple),
                                        contentDescription = null, // Add appropriate content description
                                        modifier = Modifier
                                            .size(37.dp)
                                    )
                                }
                            }
                            Box (
                                Modifier
                                    .fillMaxWidth(0.7f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ){
                                Text(text = "Sleep",
                                    color = Color.Black, fontSize = 27.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center )
                            }
                            Box (
                                Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight().padding(start = 45.dp)
                            ){
                                Icon(
                                    Icons.Rounded.KeyboardArrowUp,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(38.dp)
                                        .graphicsLayer {
                                            rotationZ = 90f
                                        },
                                    tint = Color(0xFF64519A),
                                )
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()

                            .clickable {navController.navigate("workout_page_screen")}
                            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    ) {
                        Row (modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically)
                        {
                            Box (
                                modifier = Modifier
                                    .fillMaxWidth(0.2f)
                                    .fillMaxHeight()
                                    .padding(7.dp),
                                contentAlignment = Alignment.Center

                            ){
                                Box(
                                    modifier = Modifier
                                        .size(50.dp) // Set the size of the circle
                                        .background(Color.LightGray, shape = CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    // Your image goes here
                                    Image(
                                        painter = painterResource(id = R.drawable.running_purple_icon),
                                        contentDescription = null, // Add appropriate content description
                                        modifier = Modifier
                                            .size(37.dp)
                                    )
                                }
                            }
                            Box (
                                Modifier
                                    .fillMaxWidth(0.7f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ){
                                Text(text = "Workout",
                                    color = Color.Black, fontSize = 27.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center )
                            }
                            Box (
                                Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight().padding(start = 45.dp)
                            ){
                                Icon(
                                    Icons.Rounded.KeyboardArrowUp,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(38.dp)
                                        .graphicsLayer {
                                            rotationZ = 90f
                                        },
                                    tint = Color(0xFF64519A),
                                )
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    ) {
                        Row (modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically)
                        {
                            Box (
                                modifier = Modifier
                                    .fillMaxWidth(0.2f)
                                    .fillMaxHeight()
                                    .padding(7.dp),
                                contentAlignment = Alignment.Center

                            ){
                                Box(
                                    modifier = Modifier
                                        .size(50.dp) // Set the size of the circle
                                        .background(Color.LightGray, shape = CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    // Your image goes here
                                    Image(
                                        painter = painterResource(id = R.drawable.food_icon_purple),
                                        contentDescription = null, // Add appropriate content description
                                        modifier = Modifier
                                            .size(37.dp)
                                    )
                                }
                            }
                            Box (
                                Modifier
                                    .fillMaxWidth(0.7f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ){
                                Text(text = "Food",
                                    color = Color.Black, fontSize = 27.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center )
                            }
                            Box (
                                Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight().padding(start = 45.dp)
                            ){
                                Icon(
                                    Icons.Rounded.KeyboardArrowUp,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(38.dp)
                                        .graphicsLayer {
                                            rotationZ = 90f
                                        },
                                    tint = Color(0xFF64519A),
                                )
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    ) {
                        Row (modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically)
                        {
                            Box (
                                modifier = Modifier
                                    .fillMaxWidth(0.2f)
                                    .fillMaxHeight()
                                    .padding(7.dp),
                                contentAlignment = Alignment.Center

                            ){
                                Box(
                                    modifier = Modifier
                                        .size(50.dp) // Set the size of the circle
                                        .background(Color.LightGray, shape = CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    // Your image goes here
                                    Image(
                                        painter = painterResource(id = R.drawable.symptoms_icon_purple),
                                        contentDescription = null, // Add appropriate content description
                                        modifier = Modifier
                                            .size(37.dp)
                                    )
                                }
                            }
                            Box (
                                Modifier
                                    .fillMaxWidth(0.7f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ){
                                Text(text = "Symptoms",
                                    color = Color.Black, fontSize = 27.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center )
                            }
                            Box (
                                Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight().padding(start = 45.dp)
                            ){
                                Icon(
                                    Icons.Rounded.KeyboardArrowUp,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(38.dp)
                                        .graphicsLayer {
                                            rotationZ = 90f
                                        },
                                    tint = Color(0xFF64519A),
                                )
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    ) {
                        Row (modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically)
                        {
                            Box (
                                modifier = Modifier
                                    .fillMaxWidth(0.2f)
                                    .fillMaxHeight()
                                    .padding(7.dp),
                                contentAlignment = Alignment.Center

                            ){
                                Box(
                                    modifier = Modifier
                                        .size(50.dp) // Set the size of the circle
                                        .background(Color.LightGray, shape = CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    // Your image goes here
                                    Image(
                                        painter = painterResource(id = R.drawable.medication_icon_purple),
                                        contentDescription = null, // Add appropriate content description
                                        modifier = Modifier
                                            .size(37.dp)
                                    )
                                }
                            }
                            Box (
                                Modifier
                                    .fillMaxWidth(0.7f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ){
                                Text(text = "Medication",
                                    color = Color.Black, fontSize = 27.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center )
                            }
                            Box (
                                Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight().padding(start = 45.dp)
                            ){
                                Icon(
                                    Icons.Rounded.KeyboardArrowUp,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(38.dp)
                                        .graphicsLayer {
                                            rotationZ = 90f
                                        },
                                    tint = Color(0xFF64519A),
                                )
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {navController.navigate("mood_page_screen")}
                            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    ) {
                        Row (modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically)
                        {
                            Box (
                                modifier = Modifier
                                    .fillMaxWidth(0.2f)
                                    .fillMaxHeight()
                                    .padding(7.dp),
                                contentAlignment = Alignment.Center

                            ){
                                Box(
                                    modifier = Modifier
                                        .size(50.dp) // Set the size of the circle
                                        .background(Color.LightGray, shape = CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    // Your image goes here
                                    Image(
                                        painter = painterResource(id = R.drawable.mood_icon_purple),
                                        contentDescription = null, // Add appropriate content description
                                        modifier = Modifier
                                            .size(37.dp)
                                    )
                                }
                            }
                            Box (
                                Modifier
                                    .fillMaxWidth(0.7f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ){
                                Text(text = "Mood",
                                    color = Color.Black, fontSize = 27.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center )
                            }
                            Box (
                                Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight().padding(start = 45.dp)
                            ){
                                Icon(
                                    Icons.Rounded.KeyboardArrowUp,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(38.dp)
                                        .graphicsLayer {
                                            rotationZ = 90f
                                        },
                                    tint = Color(0xFF64519A),
                                )
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    //-------------- TOILET -----------------------------
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                            .clickable {navController.navigate("toilet_page_screen")}
                    ) {
                        Row (modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically)
                        {
                            Box (
                                modifier = Modifier
                                    .fillMaxWidth(0.2f)
                                    .fillMaxHeight()
                                    .padding(7.dp),
                                contentAlignment = Alignment.Center

                            ){
                                Box(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .background(Color.LightGray, shape = CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    // Your image goes here
                                    Image(
                                        painter = painterResource(id = R.drawable.toilet_icon_purple),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(37.dp)
                                    )
                                }
                            }
                            Box (
                                Modifier
                                    .fillMaxWidth(0.7f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ){
                                Text(text = "Toilet",
                                    color = Color.Black, fontSize = 27.sp, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center )
                            }
                            Box (
                                Modifier
                                    .fillMaxWidth(1f)
                                    .fillMaxHeight().padding(start = 45.dp)
                            ){
                                Icon(
                                    Icons.Rounded.KeyboardArrowUp,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(38.dp)
                                        .graphicsLayer {
                                            rotationZ = 90f
                                        },
                                    tint = Color(0xFF64519A),
                                )
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))
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
                    modifier = Modifier.weight(1f).clickable (onClick= {viewModel.onEvent(HomePageEvent.ChangePage("homepage", navController))})
                )
                Image(painter = painterResource(R.drawable.chat_navbar),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.weight(1f).clickable (onClick= {viewModel.onEvent(HomePageEvent.ChangePage("messages", navController))})
                )
                Image(painter = painterResource(R.drawable.statistics_navbar),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.weight(1f).clickable (onClick= {viewModel.onEvent(HomePageEvent.ChangePage("statistics", navController))})
                )
                Image(painter = painterResource(R.drawable.profile_navbar),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.weight(1f).clickable (onClick= {viewModel.onEvent(HomePageEvent.ChangePage("profile", navController))})
                )

            }
        }

    }

}

