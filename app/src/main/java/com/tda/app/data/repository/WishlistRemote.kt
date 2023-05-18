package com.tda.app.data.repository

import android.util.Log
import com.tda.app.data.service.RetrofitClient
import com.tda.app.model.Resource
import com.tda.app.model.response.CustomResponse
import com.tda.app.model.response.WishlistResponse

class WishlistRemote {

    suspend fun getAllItem(jwt: String): Resource<List<WishlistResponse>> {
        val token = "Bearer $jwt"
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_JWT.getAllWishlist(token)
            )
        } catch (e: Exception) {
            Log.e("get-wishlist", "${e.message}")
            Resource.Error(message = e.message)
        }
    }

    suspend fun addItem(jwt: String, productCode: String): Resource<CustomResponse> {
        val token = "Bearer $jwt"
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_JWT.addWishlist(token, productCode)
            )
        } catch (e: Exception) {
            Log.e("add-wishlist", "${e.message}")
            Resource.Error(message = e.message)
        }
    }

    suspend fun deleteItem(jwt: String, itemId: Long): Resource<CustomResponse> {
        val token = "Bearer $jwt"
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_JWT.removeWishlist(token, itemId)
            )
        } catch (e: Exception) {
            Log.e("delete-wishlist", "${e.message}")
            Resource.Error(message = e.message)
        }
    }

}