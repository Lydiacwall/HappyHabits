package com.example.happyhabits.feature_authentication.presentation.homepage_screen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.happyhabits.R
import com.example.happyhabits.feature_authentication.domain.model.Type
import com.example.happyhabits.feature_authentication.presentation.login.LoginViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.delay
import org.w3c.dom.Text
import com.example.happyhabits.feature_authentication.presentation.util.Screen
import com.example.happyhabits.feature_authentication.domain.model.User
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomePageView(
    navController: NavController
){
    val context = LocalContext.current

    var newNotification = true

    val currentUser = User("1234", "Miltos", "Tsolkas", "yuriuser", "tsolkas@gmail.com", Type.CLIENT, birthDate = "29/03/2002")

    val colors = listOf(Color(0xffF8F7FA), Color(0xffA687FF))

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
                        .shadow(5.dp, MaterialTheme.shapes.small)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFFF2EFFA), // Start color
                                    Color(0xFFFFC79C)  // End color
                                )
                            ), shape = MaterialTheme.shapes.small
                        )
                ){
                    Row(modifier = Modifier.fillMaxSize())
                    {
                        Box (
                            Modifier
                                .fillMaxWidth(0.8f)
                                .fillMaxHeight()
                                .padding(top = 10.dp, start = 10.dp))
                        {
                            Column (modifier = Modifier.fillMaxSize()){
                                Text(text = "Great work!", color = Color.Black, fontSize = 30.sp, fontWeight = FontWeight.SemiBold)
                                Spacer(modifier = Modifier.height(7.dp))
                                Text(text="Keep logging to grow your streak ", color = Color.Black, fontSize = 19.sp, fontWeight = FontWeight.Normal)
                                Spacer(modifier = Modifier.height(3.dp))
                                Text(text="View Streaks ", color = Color(0xFFF89F5B), fontSize = 22.sp, fontWeight = FontWeight.Bold)

                            }
                        }
                        Box (
                            Modifier
                                .fillMaxWidth(1f)
                                .fillMaxHeight()
                                .padding(top = 10.dp, start = 10.dp)
                                .align(Alignment.CenterVertically))
                        {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(top = 53.dp)
                                )
                                {
                                    Image(painter = painterResource(R.drawable.streak_fire),
                                        contentDescription = null,
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                    )
                                }
                        }
                    }

                }
            }

        }
    }

}