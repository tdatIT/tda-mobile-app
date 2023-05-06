package com.tda.app.model.response

data class UpdateCartResponse(
    val message: String,
    val new_total: Double,
    val new_discount: Double,
    val new_quantity: Int
)