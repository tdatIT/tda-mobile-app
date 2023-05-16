package com.tda.app.data.repository

import android.util.Log
import com.tda.app.data.service.RetrofitClient
import com.tda.app.model.Resource
import com.tda.app.model.response.AddressResponse

class AddressRemote {

    suspend fun fetchAllData(jwt: String): Resource<List<AddressResponse>> {
        val token = "Bearer $jwt"
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_JWT.getAllAddressOfUser(token)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("get-address", "fail cause: ${e.message}")
            Resource.Error(e.message ?: "An unknown error")
        }
    }

}