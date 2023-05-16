package com.tda.app.data.service

import com.tda.app.model.request.AddressRequest
import com.tda.app.model.request.ChangeInfo
import com.tda.app.model.request.ChangePassword
import com.tda.app.model.response.AddressResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part

interface JwtApiService {
    @GET("users/addresses")
    suspend fun getAllAddressOfUser(@Header("Authorization") token: String): List<AddressResponse>

    @POST("users/addresses")
    suspend fun addNewAddress(@Header("Authorization") token: String, @Body body: AddressRequest)

    @PATCH("users/change-info")
    suspend fun changeInfo(@Header("Authorization") token: String, @Body body: ChangeInfo)

    @PATCH("users/change-password")
    suspend fun changPassword(@Header("Authorization") token: String, @Body body: ChangePassword)

    @PATCH("users/change-avatar")
    suspend fun changAvatar(
        @Header("Authorization") token: String,
        @Part("image_file") file: MultipartBody.Part
    )
}