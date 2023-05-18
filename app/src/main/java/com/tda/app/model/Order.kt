package com.tda.app.model


data class Order(
    val userId: Long,
    val shippingCosts: Int,
    val discount: Double,
    val total: Double,
    val paymentMethod: Int,
    val address_id: Long,
    val voucher_code: String? = null,
    val cart_items: List<Long>
)