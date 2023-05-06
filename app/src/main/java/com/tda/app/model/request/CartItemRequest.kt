package com.tda.app.model.request

data class CartItemRequest(
    val productCode: String,
    val quantity:Int
)