package com.example.happyhabits.feature_authentication.data.data_source

abstract class UserDatabase {
    abstract val userDao: IUserDao
}