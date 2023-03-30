package com.tda.app.data.model

import java.util.Date

data class JwtResponse(
    val code: Int,
    val message: String,
    val jwt: String,
    val expiryDate: Date) {

}