package com.tda.app.model.response

data class OrderItemResp(
    val itemOId: Long,
    val productId: Long,
    val productCode: String,
    val productName: String,
    val productImg: String,
    val quantity: Int,
    val price: Double
)