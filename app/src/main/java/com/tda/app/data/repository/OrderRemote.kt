package com.tda.app.data.repository

import android.util.Log
import com.tda.app.data.service.RetrofitClient
import com.tda.app.model.Order
import com.tda.app.model.Resource
import com.tda.app.model.response.OrderResponse


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

    suspend fun getAllOrderForUser(
        jwt: String,
        page: Int,
        size: Int
    ): Resource<List<OrderResponse>> {
        val token = "Bearer $jwt"
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_JWT.getOrderList(token, page, size)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("get-orders", "fail cause: ${e.message}")
            Resource.Error(e.message ?: "An unknown error")
        }
    }

    suspend fun getOrderDetails(jwt: String, orderId: Long): Resource<OrderResponse> {
        val token = "Bearer $jwt"
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_JWT.getOrderDetails(token, orderId)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("get-order-details", "fail cause: ${e.message}")
            Resource.Error(e.message ?: "An unknown error")
        }
    }
}