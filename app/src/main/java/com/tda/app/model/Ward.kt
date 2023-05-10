package com.tda.provinceapi.model

import com.tda.app.model.DivisionGroup

data class Ward(
    override val name: String,
    override val code: Int,
    override val division_type: String,
    override val codename: String,
) : DivisionGroup