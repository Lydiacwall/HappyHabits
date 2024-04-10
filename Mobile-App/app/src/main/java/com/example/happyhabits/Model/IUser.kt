package com.example.happyhabits.Model

interface IUser {
    fun getPassword(): String?
    fun getUsername(): String?
    fun getEmail(): String?
    fun isValid(): Int?
}