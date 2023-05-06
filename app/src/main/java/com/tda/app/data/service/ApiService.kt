package com.tda.app.data.service

import com.tda.app.model.request.LoginRequest
import com.tda.app.model.response.LoginResponse
import com.tda.app.model.District
import com.tda.provinceapi.model.Province
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("login")
    suspend fun login(@Body body: LoginRequest): LoginResponse
    //province
    @GET("p/")
    suspend fun getAllProvince(): List<Province>

    @GET("p/{code}")
    suspend fun getAllDistrictInProvince(
        @Path("code") code: Int,
        @Query("depth") depth: Int
    ): Province

    @GET("d/{code}")
    suspend fun getAllWard(
        @Path("code") code: Int,
        @Query("depth") depth: Int
    ): District
}