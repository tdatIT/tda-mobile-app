package com.tda.app.model.response

import java.sql.Timestamp

class UserResponse(
    val userId: Long,
    val firstname: String,
    val lastname: String,
    val email: String,
    val phone: String,
    val avatar: String,
    val status: Boolean,
    val createDate: Timestamp,
    val updateDate: Timestamp,
)