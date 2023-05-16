package com.tda.app.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp
import java.util.Date

@Entity("user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Long,
    //json web token request
    val jwt: String,
    val expiryDate: Date,
    //user info
    val firstname: String,
    val lastname: String,
    val email: String,
    val phone: String,
    val avatar: String? = null,
    val status: Boolean,
    val createDate: Date,
    val updateDate: Date? = null,
)