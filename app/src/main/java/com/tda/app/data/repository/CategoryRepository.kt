package com.tda.app.data.repository

import android.util.Log
import com.tda.app.data.service.RetrofitClient
import com.tda.app.model.Resource
import com.tda.app.model.response.CategoryResp
import com.tda.app.model.response.ProductResponse

class CategoryRepository {

    suspend fun getCategories(size: Int): Resource<List<CategoryResp>> {
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_API.getCategories(size)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("get-product", "Login fail cause: ${e.message}")
            Resource.Error(e.message ?: "An unknown error")
        }
    }
}