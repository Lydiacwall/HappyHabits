package com.example.happyhabits.feature_authentication.data.data_source

import com.example.happyhabits.feature_authentication.domain.model.Type
import com.example.happyhabits.feature_authentication.domain.model.User

class UserDao: IUserDao {
    val userList: List<User> = listOf(User("1234", "Miltos", "Tsolkas", "yuriuser", "tsolkas@gmail.com", Type.CLIENT, birthDate = "29/03/2002"),
        User("1234","Miltos", "Tsolkas", "yuridoctor", "tsolkas@gmail.com", Type.DOCTOR, birthDate = "29/03/2002"))
    override fun getUserByPasswordAndEmail(password: String, email: String): User? {
        return userList.find { it.password == password && it.email == email }
    }

}
