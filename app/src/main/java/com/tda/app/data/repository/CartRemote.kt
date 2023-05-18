package com.tda.app.data.repository

import android.util.Log
import com.tda.app.data.service.RetrofitClient
import com.tda.app.model.Resource
import com.tda.app.model.request.CartItemRequest
import com.tda.app.model.response.CartItemResponse

class CartRemote {
    suspend fun getAllItemInCart(jwt: String): Resource<List<CartItemResponse>> {
        val token = "Bearer $jwt"
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_JWT.viewCart(token)
            )
        } catch (e: Exception) {
            Log.e("get-cart-item", "${e.message}")
            Resource.Error(message = e.message)
        }
    }

    suspend fun addItem(jwt: String, body: CartItemRequest): Resource<String> {
        val token = "Bearer $jwt"
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_JWT.addItem(token, body)
            )
        } catch (e: Exception) {
            Log.e("add-item", "${e.message}")
            Resource.Error(message = e.message)
        }
    }

    suspend fun updateQuantity(jwt: String, productCode: String, quantity: Int): Resource<String> {
        val token = "Bearer $jwt"
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_JWT.updateItem(token, productCode, quantity)
            )
        } catch (e: Exception) {
            Log.e("update-item", "${e.message}")
            Resource.Error(message = e.message)
        }
    }

    suspend fun deleteItem(jwt: String, productCode: String): Resource<String> {
        val token = "Bearer $jwt"
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_JWT.deleteItem(token, productCode)
            )
        } catch (e: Exception) {
            Log.e("delete-item", "${e.message}")
            Resource.Error(message = e.message)
        }
    }

    suspend fun totalQuantity(jwt: String): Resource<Int> {
        val token = "Bearer $jwt"
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_JWT.totalQuantity(token)
            )
        } catch (e: Exception) {
            Log.e("total-quantity", "${e.message}")
            Resource.Error(message = e.message)
        }
    }

    suspend fun totalDiscount(jwt: String): Resource<Double> {
        val token = "Bearer $jwt"
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_JWT.totalDiscount(token)
            )
        } catch (e: Exception) {
            Log.e("total-discount", "${e.message}")
            Resource.Error(message = e.message)
        }
    }

    suspend fun totalAmount(jwt: String): Resource<Double> {
        val token = "Bearer $jwt"
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_JWT.totalAmount(token)
            )
        } catch (e: Exception) {
            Log.e("total-amount", "${e.message}")
            Resource.Error(message = e.message)
        }
    }
}