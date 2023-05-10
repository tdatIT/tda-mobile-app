package com.tda.provinceapi.model

import com.tda.app.model.District
import com.tda.app.model.DivisionGroup

data class Province(
    override val name: String,
    override val code: Int,
    override val division_type: String,
    override val codename: String,
    val districts: List<District>
):DivisionGroup
