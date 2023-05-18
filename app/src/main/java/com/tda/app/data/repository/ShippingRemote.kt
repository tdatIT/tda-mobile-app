package com.tda.app.data.repository

import android.util.Log
import com.tda.app.data.service.RetrofitClient
import com.tda.app.model.Resource
import com.tda.app.model.response.ShippingResponse

class ShippingRemote {
    suspend fun calculateShipping(addressId: Long): Resource<ShippingResponse> {
        return try {
            Resource.Success(
                RetrofitClient.retrofit_TDA_JWT.calculateShipping(addressId)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("calculate ship", "fail cause: ${e.message}")
            Resource.Error(e.message ?: "An unknown error")
        }
    }
}