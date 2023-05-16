package com.tda.app.data.repository

import com.tda.app.data.service.KeywordDao
import com.tda.app.model.Keyword
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class KeywordRepository @Inject constructor(var keywordDao: KeywordDao) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertKeyword(keyword: Keyword) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                keywordDao.insertNewKeyword(keyword)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun getAll(): List<Keyword>? = withContext(Dispatchers.IO) {
        var data = keywordDao.getAll();
        return@withContext data
    }


    fun deleteKey(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                keywordDao.deleteKeyword(id);
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}