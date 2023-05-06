package com.tda.app.model.response


import java.sql.Timestamp

data class OrderResponse (
    val orderId: Long,
    val userId: Long,
    val shippingCosts:Int,
    val discount:Double,
    val total:Double,
    val orderStatus:Int,
    val paymentMethod:Int,
    val createDate: Timestamp,
    val address_detail: String,
    val address_id: Long,
   // val voucher: Voucher? = null
    val order_items: List<OrderItemResp>
)