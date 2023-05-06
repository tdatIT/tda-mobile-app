package com.tda.app.model.response

data class CartItemResponse(
    val itemId: Long,
    val quantity: Int,
    val amount: Double,

    //product
    val productCode: String,
    val productName: String,
    val status: Int,
    val price: Double,
    val promotionPrice: Double,
    val categoryId: Long,
    val images_file: List<String>
)