package com.tda.app.data.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tda.app.model.User

@Dao
interface UserDao {

    @Insert
    fun insertNewUser(user: User)

    @Query("select * from user")
    fun findUserLogged(): List<User>

    @Query("delete from user where id = :id")
    fun removeUserLogged(id: Int)

    @Query("update user set avatar =:avt")
    fun updateNewAvt(avt: String)

    @Query("update user set firstname=:first_name,lastname=:last_name,phone=:phone")
    fun updateInfo(first_name: String, last_name: String, phone: String)
}