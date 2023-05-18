package com.tda.app.model.request

data class AddressRequest(
    val province_code: Int,
    val district_code: Int,
    val ward_code: Int,
    val detail: String,
    val phone: String? = null,
)