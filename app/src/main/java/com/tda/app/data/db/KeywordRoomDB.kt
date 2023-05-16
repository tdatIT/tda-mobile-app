package com.tda.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tda.app.data.service.KeywordDao
import com.tda.app.model.Keyword

@Database(entities = [(Keyword::class)], version = 1, exportSchema = false)
abstract class KeywordRoomDB : RoomDatabase() {
    abstract fun getKeywordDao(): KeywordDao
}