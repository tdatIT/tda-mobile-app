package com.tda.app.di

import android.content.Context
import androidx.room.Room
import com.tda.app.data.db.KeywordRoomDB
import com.tda.app.data.db.UserRoomDB
import com.tda.app.data.repository.AddressRemote
import com.tda.app.data.repository.CartRemote
import com.tda.app.data.repository.DivisionRemote
import com.tda.app.data.repository.KeywordRepository
import com.tda.app.data.repository.OrderRemote
import com.tda.app.data.repository.ShippingRemote
import com.tda.app.data.repository.UserRemote
import com.tda.app.data.repository.UserRepository
import com.tda.app.data.repository.WishlistRemote
import com.tda.app.data.service.KeywordDao
import com.tda.app.data.service.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun getKwRepository(dao: KeywordDao): KeywordRepository {
        return KeywordRepository(dao)
    }

    @Singleton
    @Provides
    fun getKwDao(database: KeywordRoomDB): KeywordDao {
        return database.getKeywordDao()
    }

    @Singleton
    @Provides
    fun getCartRemote(): CartRemote {
        return CartRemote()
    }

    @Singleton
    @Provides
    fun getOrderRemote(): OrderRemote {
        return OrderRemote()
    }

    @Singleton
    @Provides
    fun getShippingRemote(): ShippingRemote {
        return ShippingRemote()
    }

    @Singleton
    @Provides
    fun getAddressRemote(): AddressRemote {
        return AddressRemote()
    }

    @Singleton
    @Provides
    fun getWishlistRemote(): WishlistRemote {
        return WishlistRemote()
    }

    @Singleton
    @Provides
    fun getUserRemote(): UserRemote {
        return UserRemote()
    }

    @Singleton
    @Provides
    fun getDivisionRemote(): DivisionRemote {
        return DivisionRemote()
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): UserRoomDB {
        return Room.databaseBuilder(
            context.applicationContext, UserRoomDB::class.java, "user_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideKeywordDatabase(@ApplicationContext context: Context): KeywordRoomDB {
        return Room.databaseBuilder(
            context.applicationContext, KeywordRoomDB::class.java, "keyword_db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}