package com.example.happyhabits.Controller

import com.example.happyhabits.Model.User
import com.example.happyhabits.View.ILoginView

class LoginController (
    private val loginView: ILoginView
): ILoginController {
    override fun OnLogin() {
        val email = loginView.GetEmail();
        val password = loginView.GetPassword();

        println("Password: $password")
        println("Email: $email")

        val user = User(email, password);
        val loginCode = user.isValid()

        when (loginCode) {
            0 -> {
                loginView.OnLoginMessage("Please enter Email");
            }

            1 -> {
                loginView.OnLoginMessage("Please enter A valid Email");
            }

            2 -> {
                loginView.OnLoginMessage("Please enter Password");
            }

            3 -> {
                loginView.OnLoginMessage("Please enter Password greater the 6 char");
            }

            else -> {
                // Do the api call
            }
        }
    }
}
