package com.tda.app.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("keyword")
data class Keyword(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val keyword: String
)
