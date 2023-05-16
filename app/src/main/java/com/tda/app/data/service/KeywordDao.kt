package com.tda.app.data.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tda.app.model.Keyword

@Dao
interface KeywordDao {
    @Insert
    fun insertNewKeyword(keyword: Keyword)

    @Query("Select * from keyword")
    fun getAll(): List<Keyword>?

    @Query("delete from keyword where id =:id")
    fun deleteKeyword(id: Int)
}