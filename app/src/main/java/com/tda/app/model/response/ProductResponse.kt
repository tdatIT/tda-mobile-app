package com.tda.app.model.response

import java.sql.Timestamp


data class ProductResponse(
    val productId: Long,
    val productCode: String,
    val productName: String,
    val description: String,
    val quantity: Int,
    val status: Int,
    val price: Double,
    val promotionPrice: Double,
    val createDate: Timestamp,
    val updateDate: Timestamp,
    val isDeleted: Boolean,
    val categoryId: Long,
    val images_file: List<String>
)