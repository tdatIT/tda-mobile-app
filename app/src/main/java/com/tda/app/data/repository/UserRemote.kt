package com.tda.app.data.repository

import android.util.Log
import com.tda.app.data.service.RetrofitClient
import com.tda.app.model.Resource
import com.tda.app.model.request.LoginRequest
import com.tda.app.model.response.LoginResponse

class UserRemote {
    suspend fun loginAccount(body: LoginRequest): Resource<LoginResponse> {
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_API.login(body)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("auth-error", "Cause ${e.message}")
            Resource.Error("Login request was fail cause: ${e.message}")
        }
    }
}