package com.tda.app.data.repository

import android.util.Log
import com.tda.app.data.service.RetrofitClient
import com.tda.app.model.District
import com.tda.app.model.Resource
import com.tda.app.model.Province

class DivisionRemote {

    suspend fun getAllProvince(): Resource<List<Province>> {
        return try {
            Resource.Success(
                RetrofitClient.retrofit_PV_API.getAllProvince()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("get-province", "Login fail cause: ${e.message}")
            Resource.Error(e.message ?: "An unknown error")
        }
    }

    suspend fun getDistrict(code: Int): Resource<Province> {
        return try {
            Resource.Success(
                RetrofitClient.retrofit_PV_API.getAllDistrictInProvince(code, 2)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("get-province", "Login fail cause: ${e.message}")
            Resource.Error(e.message ?: "An unknown error")
        }
    }

    suspend fun getWards(code: Int): Resource<District> {
        return try {
            Resource.Success(
                RetrofitClient.retrofit_PV_API.getAllWard(code, 2)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("get-province", "Login fail cause: ${e.message}")
            Resource.Error(e.message ?: "An unknown error")
        }
    }

}