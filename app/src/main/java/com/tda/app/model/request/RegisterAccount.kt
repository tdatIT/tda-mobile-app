package com.tda.app.model.request

import com.tda.app.model.UserAddress

data class RegisterAccount(
    var firstname: String,
    var lastname: String,
    var email: String,
    var hashPassword: String,
    var confirmPassword: String,
    var phone: String,
    var defaultAddress: UserAddress,
    var address_detail: String,
)