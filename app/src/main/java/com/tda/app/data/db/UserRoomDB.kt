package com.tda.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tda.app.data.service.UserDao
import com.tda.app.model.Converters
import com.tda.app.model.User

@Database(entities = [(User::class)], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class UserRoomDB : RoomDatabase() {
    abstract fun userDao(): UserDao
}