package com.example.happyhabits.View

interface ILoginView {
    fun OnLoginMessage(message: String?)

    fun GetEmail() : String?
    fun GetPassword() : String?
}