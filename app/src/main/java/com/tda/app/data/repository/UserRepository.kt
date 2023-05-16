package com.tda.app.data.repository

import com.tda.app.data.service.UserDao
import com.tda.app.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Timestamp
import java.util.Date
import javax.inject.Inject

class UserRepository @Inject constructor(val userDao: UserDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertUserInfo(us: User) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                userDao.insertNewUser(us)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun checkAvalaibleUser(): Boolean {
        var data = getUser()
        data?.let {

            if (data.expiryDate < getCurrentTime()) {
                deleteAccount(data.id)
                return false
            }
            return true
        }
        return false
    }

    suspend fun getUser(): User? = withContext(Dispatchers.IO) {
        var data = userDao.findUserLogged();
        if (data.isNotEmpty())
            return@withContext data[0]
        return@withContext null
    }


    fun getCurrentTime(): Timestamp {
        return Timestamp(Date().time)
    }

    fun deleteAccount(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                userDao.removeUserLogged(id);
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}