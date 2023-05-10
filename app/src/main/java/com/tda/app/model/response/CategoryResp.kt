package com.tda.app.model.response

import java.sql.Timestamp

data class CategoryResp(
    val categoryId: Int,
    val name: String,
    val images: String,
    val description: String,
    val createDate: Timestamp,
    val updateDate: Timestamp,
    val code: String,
    val status: Boolean
)
