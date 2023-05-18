package com.tda.app.model

data class Province(
    override val name: String,
    override val code: Int,
    override val division_type: String,
    override val codename: String,
    val districts: List<District>
):DivisionGroup
