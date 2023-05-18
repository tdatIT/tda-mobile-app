package com.tda.app.model.request

data class AddressRequest(
    val province: Int,
    val district: Int,
    val ward: Int,
    val detail: String,
    val phone: String? = null,
)