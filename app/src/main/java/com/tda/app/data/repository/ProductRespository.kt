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
            Log.e("get-product-by-category-code", "Failed cause: ${e.message}")
            Resource.Error(e.message ?: "An unknown error")

        }
    }

    suspend fun getProductsByCode(code: String): Resource<ProductResponse> {
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_API.getProductByCode(code)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("get-product-by-code", "Failed cause: ${e.message}")
            Resource.Error(e.message ?: "An unknown error")

        }
    }

    suspend fun getBestSeller(): Resource<List<ProductResponse>> {
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_API.getBestSellerProduct()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("best-seller", "Failed cause: ${e.message}")
            Resource.Error(e.message ?: "An unknown error")

        }
    }

    suspend fun getPopular(): Resource<List<ProductResponse>> {
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_API.getPopularProduct()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("popular", "Failed cause: ${e.message}")
            Resource.Error(e.message ?: "An unknown error")

        }
    }

    suspend fun getProductByKeyWord(keyword: String): Resource<List<ProductResponse>> {
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_API.getProductByKeyword(keyword)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("search-keyword", "Failed cause: ${e.message}")
            Resource.Error(e.message ?: " An unknown error")
        }
    }

}