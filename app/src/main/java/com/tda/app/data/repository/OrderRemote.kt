package com.tda.app.data.repository

import android.util.Log
import com.tda.app.data.service.RetrofitClient
import com.tda.app.model.Order
import com.tda.app.model.Resource


class OrderRemote {
    suspend fun createNewOrder(jwt: String, order: Order): Resource<String> {
        val token = "Bearer $jwt"
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_JWT.createOrder(token, order)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("createNewOrder", "fail cause: ${e.message}")
            Resource.Error(e.message ?: "An unknown error")
        }
    }
}