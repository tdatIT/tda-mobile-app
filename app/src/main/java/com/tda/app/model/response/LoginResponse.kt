package com.tda.app.model.response

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.tda.app.model.User
import java.util.Date


data class LoginResponse(
    val code: Int,
    val message: String,
    val jwt: String,
    val expiryDate: Date,
    val user: UserResponse
) {
    fun mapperToUser(): User {

        return User(
            userId = user.userId,
            email = user.email,
            jwt = jwt,
            expiryDate = expiryDate,
            firstname = user.firstname,
            lastname = user.lastname,
            phone = user.phone,
            avatar = user.avatar,
            status = user.status,
            createDate = user.createDate,
            updateDate = user.updateDate
        )
    }
}