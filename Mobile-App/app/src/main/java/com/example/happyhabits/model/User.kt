package com.example.happyhabits.Model

import android.text.TextUtils
import android.util.Patterns

class User (
) : IUser {
    private var username: String? = null;
    private var email: String? = null;
    private var password: String? = null;

    constructor(email: String?, password: String?) : this() {
        this.email = email;
        this.password = password;
    }

    constructor(email: String?, password: String?, username: String?) : this() {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    override fun getEmail(): String? {
        return email;
    }

    override fun getUsername(): String? {
        return username;
    }

    override fun getPassword(): String? {
        return password;
    }

    override fun isValid(): Int {
        return if(TextUtils.isEmpty(getEmail()))
            0
        else if(!Patterns.EMAIL_ADDRESS.matcher(getEmail().toString()).matches())
            1
        else if(TextUtils.isEmpty(getPassword()))
            2
        else if(getPassword()?.length!! <=6)
            3
        else
            -1;
    }
}