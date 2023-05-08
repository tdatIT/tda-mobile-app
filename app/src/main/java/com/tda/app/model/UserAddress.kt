package com.tda.app.model

data class UserAddress(
    val addressId: Long,
    val phone: String,
    val detail: String,
    val province: Int,
    val district: Int,
    val ward: Int,
    val commune: String,
    val isDeleted: Boolean
)