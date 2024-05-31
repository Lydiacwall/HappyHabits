package com.example.happyhabits.feature_application.feature_profile.presentation.profile_page

import android.graphics.Bitmap
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyhabits.core.data.model.Manager
import com.example.happyhabits.core.domain.model.Type
import com.example.happyhabits.feature_application.feature_profile.domain.use_case.ProfileUseCases
import com.example.happyhabits.feature_application.presentation.util.Screen
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewmodel @Inject constructor(
    private val profileUseCases: ProfileUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state
    val userId: String? = Manager.currentUser?.id
    val type: Type? = Manager.currentUser?.type

    // Use mutableStateOf to allow Compose to observe changes
    private val _qrCodeBitmap = mutableStateOf<Bitmap?>(null)
    val qrCodeBitmap: State<Bitmap?> = _qrCodeBitmap
    private var _scanError = mutableStateOf<String?>(null)
    val scanError: State<String?> = _scanError

    init {
        _state.value = _state.value.copy(
            firstName = Manager.currentUser?.firstName,
            lastName = Manager.currentUser?.lastName,
            email = Manager.currentUser?.email,
            birthdate = Manager.currentUser?.birthDate,
            userId = userId)
        generateQRCode(userId)
    }

    private fun generateQRCode(userId: String?) {
        viewModelScope.launch {
            val bitmap = withContext(Dispatchers.Default) {
                try {
                    val elements = "$userId?$type"
                    val barcodeEncoder = BarcodeEncoder()
                    barcodeEncoder.encodeBitmap(elements, BarcodeFormat.QR_CODE, 800, 800)
                } catch (e: WriterException) {
                    null
                }
            }
            _qrCodeBitmap.value = bitmap
        }
    }
    // Method to handle scan QR code
    fun handleScanResult(result: String?) {
        // Send friend request
        viewModelScope.launch {
            try {
                if (userId != null && result != null) {
                    val parts = result.split("?")
                    val scannedUserId = parts[0]
                    val scannedType = parts[1]
                    if (userId == scannedUserId) {
                        throw Exception("Cannot befriend yourself")
                    }
                    else if (scannedType == type.toString()) {
                        if (type.toString() == "DOCTOR") {
                            throw Exception("Cannot befriend another doctor")
                        }
                        else {
                            throw Exception("Cannot befriend another simple user")
                        }
                    }
                    profileUseCases.addNewFriendship(userId, scannedUserId)
                }
            } catch (e: Exception) {
                _scanError.value = e.message
            }
        }
    }
    fun initScanError() {
        _scanError.value = null
    }
    fun onEvent(event: ProfileEvent.ChangePage) {
        when(event.pageName){
            "homepage"->{
                event.navController.navigate(Screen.HomePageScreen.route)
            }
            "messages" -> {
            }
            "statistics"-> {
            }
            "profile"->
            {
                event.navController.navigate(Screen.ProfilePageScreen.route)
            }
        }
    }
}

