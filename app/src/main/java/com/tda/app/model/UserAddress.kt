package com.tda.app.model

data class UserAddress(
    val addressId: Long? = null,
    val phone: String,
    val detail: String,
    val province: Int,
    val district: Int,
    val ward: Int,
    val commune: String? = null,
    val isDeleted: Boolean? = null
)