package com.tda.app.data.repository

import android.util.Log
import com.tda.app.data.service.RetrofitClient
import com.tda.app.model.Resource
import com.tda.app.model.response.ProductResponse


class ProductRespository {

    suspend fun getProducts(page: Int, size: Int): Resource<List<ProductResponse>> {
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_API.getAllProducts(page, size)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("get-product", "Login fail cause: ${e.message}")
            Resource.Error(e.message ?: "An unknown error")

        }
    }

    suspend fun getProductsByCategoryCode(code: String): Resource<List<ProductResponse>> {
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_API.getAllProductByCategoryCode(code)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("get-product-by-code", "Failed cause: ${e.message}")
            Resource.Error(e.message ?: "An unknown error")

        }
    }
}