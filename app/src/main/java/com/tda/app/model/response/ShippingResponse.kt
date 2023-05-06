package com.tda.app.model.response

data class ShippingResponse(
    val code: Int,
    val message: String,
    val cost: Int,
)