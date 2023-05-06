package com.tda.app.model.response

import java.util.Date

data class LoginResponse(
    val code: Int,
    val message: String,
    val jwt: String,
    val expiryDate: Date?
)