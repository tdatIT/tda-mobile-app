package com.tda.app.model.response


data class ExceptionResponse(
    val code: Int,
    val message: String,
    val timestamp: Long
)