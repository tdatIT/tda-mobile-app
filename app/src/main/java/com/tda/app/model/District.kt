package com.tda.app.model

import com.tda.provinceapi.model.Ward

data class District(
    override val name: String,
    override val code: Int,
    override val division_type: String,
    override val codename: String,
    val wards: List<Ward>
) : DivisionGroup
