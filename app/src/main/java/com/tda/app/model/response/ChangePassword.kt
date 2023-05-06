package com.tda.app.model.response

data class ChangePassword(
    val new_pass: String,
    val old_pass: String
)