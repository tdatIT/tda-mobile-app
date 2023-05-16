package com.tda.app.model.request

data class ChangePassword(
    val new_pass: String,
    val old_pass: String
)
