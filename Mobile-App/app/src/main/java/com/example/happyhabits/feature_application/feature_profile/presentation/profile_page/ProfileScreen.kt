package com.example.happyhabits.feature_application.feature_profile.presentation.profile_page


import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.happyhabits.R
import com.example.happyhabits.feature_application.presentation.util.BottomNavBar
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileView(
    navController: NavController,
    viewModel: ProfileViewmodel = hiltViewModel()
){
    val context = LocalContext.current
    val state by viewModel.state

    val qrCodeBitmap by viewModel.qrCodeBitmap
    val scanError by viewModel.scanError
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

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

    val qrCodeDialog = rememberMaterialDialogState()
    val scanDialog = rememberMaterialDialogState()

//    LaunchedEffect(Unit) {
//        viewModel.generateQRCode("6633665f563dbd9d22f06d9d")
//    }

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
                        .fillMaxWidth(1f)
                        .fillMaxHeight()
                        .padding(top=5.dp),
                    contentAlignment = Alignment.TopStart
                )
                {
                    Text(
                        text = state.firstName?: "No User",
                        color = Color(0xffF2F1F6),
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 32.dp,start = 22.dp)
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
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.3f)
                                    .fillMaxHeight()
                                    .padding(start = 10.dp, top = 12.dp)
                            ) {
                                Text(
                                    text = "First Name:",
                                    color = Color.Black,
                                    fontSize = 15.sp,
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
                                    contentAlignment = Alignment.CenterStart
                                )
                                {
                                    Text(
                                        text = state.firstName?:"",
                                        color = Color(0xff000000),
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                }
                            }

                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.3f)
                                    .fillMaxHeight()
                                    .padding(start = 10.dp, top = 12.dp)
                            ) {
                                Text(
                                    text = "Last Name:",
                                    color = Color.Black,
                                    fontSize = 15.sp,
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
                                    contentAlignment = Alignment.CenterStart
                                )
                                {
                                    Text(
                                        text = state.lastName?:"",
                                        color = Color(0xff000000),
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                }
                            }

                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.3f)
                                    .fillMaxHeight()
                                    .padding(start = 10.dp, top = 12.dp)
                            ) {
                                Text(
                                    text = "Email:",
                                    color = Color.Black,
                                    fontSize = 15.sp,
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
                                    contentAlignment = Alignment.CenterStart
                                )
                                {
                                    Text(
                                        text = state.email?:"",
                                        color = Color(0xff000000),
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                }
                            }

                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.3f)
                                    .fillMaxHeight()
                                    .padding(start = 10.dp, top = 12.dp)
                            ) {
                                Text(
                                    text = "Birth Date:",
                                    color = Color.Black,
                                    fontSize = 15.sp,
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
                                    contentAlignment = Alignment.CenterStart
                                )
                                {
                                    Text(
                                        text = state.birthdate ?:"",
                                        color = Color(0xff000000),
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                }
                            }

                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(70.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .shadow(4.dp, shape = RoundedCornerShape(16.dp))
                            .fillMaxHeight()
                            .background(
                                color = Color(0xff64519A),
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clickable(onClick = {
                                scanDialog.show()
                            }),
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
                            )
                            .clickable(onClick = {
                                qrCodeDialog.show()
                            }),
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

    BottomNavBar(navController = navController)

    // Scan QR code
    if (scanDialog.showing) {
        ScanDialog(
            context = context,
            viewModel = viewModel, // Pass viewModel instance
            onScanResult = { result ->
                viewModel.handleScanResult(result)
                scanDialog.hide()
            }
        )
    }

    scanError?.let { result ->
        AlertDialog(
            onDismissRequest = { viewModel.initScanError() },
            title = { Text("Scan Result") },
            text = { Text(result) },
            confirmButton = {
                Button(onClick = { viewModel.initScanError() }) {
                    Text("OK")
                }
            }
        )
    }

    /// QR Code Generator
    MaterialDialog(
        dialogState = qrCodeDialog,
        shape = RoundedCornerShape(20.dp),
        backgroundColor =  Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(20.dp))
            Text(
                text = "QR Code",
                color = Color(0xff645199),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            qrCodeBitmap?.let { bitmap ->
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    qrCodeDialog.hide()
                },
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .height(60.dp)
                    .padding(start = 20.dp, end = 5.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff645199)
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 5.dp,
                    pressedElevation = 5.dp,
                )
            ) {
                Text(
                    text = "Close",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(Modifier.height(10.dp))
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScanDialog(
    context: Context,
    viewModel: ProfileViewmodel, // Add viewModel parameter
    onScanResult: (String?) -> Unit
) {
    val scannerInitialized = remember { mutableStateOf(false) }
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    AlertDialog(
        onDismissRequest = { onScanResult(null) },
        title = { Text("Scan QR Code") },
        text = {
            if (!cameraPermissionState.hasPermission) {
                Text("Camera permission is required to scan QR codes.")
                LaunchedEffect(Unit) {
                    cameraPermissionState.launchPermissionRequest()
                }
            } else {
                AndroidView(
                    modifier = Modifier.size(250.dp),
                    factory = { ctx ->
                        val barcodeView = DecoratedBarcodeView(ctx)
                        if (!scannerInitialized.value) {
                            // Initialize the barcode scanner only once
                            barcodeView.initializeFromIntent(
                                IntentIntegrator(context as Activity).createScanIntent()
                            )
                            scannerInitialized.value = true
                        }
                        // Start decoding when the scanner is initialized
                        if (scannerInitialized.value) {
                            barcodeView.resume()
                        }
                        barcodeView.decodeSingle { result ->
                            onScanResult(result?.text)
                            viewModel.handleScanResult(result?.text) // Call viewmodel method
                        }
                        barcodeView
                    }
                )
            }
        },
        confirmButton = {
            Button(onClick = { onScanResult(null) }) {
                Text("Cancel")
            }
        }
    )
}


