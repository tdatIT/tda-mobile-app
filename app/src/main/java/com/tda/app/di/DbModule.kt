package com.tda.app.di

import android.content.Context
import androidx.room.Room
import com.tda.app.data.db.UserRoomDB
import com.tda.app.data.repository.UserRepository
import com.tda.app.data.service.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {


    @Singleton
    @Provides
    fun getRepository(dao: UserDao): UserRepository {
        return UserRepository(dao)
    }

    @Singleton
    @Provides
    fun getDao(database: UserRoomDB): UserDao {
        return database.userDao()
    }
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): UserRoomDB {
        return Room.databaseBuilder(
            context.applicationContext, UserRoomDB::class.java, "user_db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}