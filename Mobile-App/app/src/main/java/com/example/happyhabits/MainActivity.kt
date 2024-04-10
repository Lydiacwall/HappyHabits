package com.example.happyhabits

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.happyhabits.ui.theme.HappyHabitsTheme

import com.example.happyhabits.Controller.ILoginController
import com.example.happyhabits.Controller.LoginController
import com.example.happyhabits.View.ILoginView

class MainActivity : ComponentActivity(), ILoginView {

    private var loginController: ILoginController? = null
    private var emailField: EditText? = null
    private var passwordField: EditText? = null
    private var loginButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginController = LoginController(this);
        emailField = findViewById(R.id.emailEditText)
        passwordField = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
    }

    override fun onStart() {
        super.onStart()
        loginButton?.setOnClickListener {
            // Do some work here
            loginController?.OnLogin();
        }
    }

    override fun OnLoginMessage(message: String?) {
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }

    override fun GetEmail(): String? {
        return emailField?.text.toString();
    }

    override fun GetPassword(): String? {
        return passwordField?.text.toString();
    }

//    @OptIn(ExperimentalComposeUiApi::class)
//    @Composable
//    fun LoginScreen() {
//        val username = remember { mutableStateOf("") }
//        val password = remember { mutableStateOf("") }
//        val email = remember { mutableStateOf("") }
//
//        val keyboardController = LocalSoftwareKeyboardController.current
//
//        Column(
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            TextField(
//                value = username.value,
//                onValueChange = { username.value = it },
//                label = { Text("Username") },
//                singleLine = true,
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            TextField(
//                value = password.value,
//                onValueChange = { password.value = it },
//                label = { Text("Password") },
//                singleLine = true,
//                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
//                keyboardActions = KeyboardActions(
//                    onDone = {
//                        keyboardController?.hide()
//                    }
//                ),
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            TextField(
//                value = email.value,
//                onValueChange = { email.value = it },
//                label = { Text("Email") },
//                singleLine = true,
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Button(
//                onClick = {
//                    // Perform login action here
//                    val enteredUsername = username.value
//                    val enteredPassword = password.value
//                    val enteredEmail = email.value
//                    // Handle login logic
//
//                    // Print entered username and password
//                    println("Username: $enteredUsername")
//                    println("Password: $enteredPassword")
//                    println("Email: $enteredEmail")
//
//                },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text("Login")
//            }
//        }
//    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    HappyHabitsTheme {
//        LoginScreen()
//    }
//}
