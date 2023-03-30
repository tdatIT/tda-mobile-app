package com.tda.app.data.model

import User

data class UserAddress(
    var addressId: Int?,
    var phone: String?,
    var detai: String?,
    var province: Int?,
    var district: Int?,
    var ward: Int?,
    var commune: Int?,
    var isDeleted: Boolean?,
    var user: User?
) {

}
