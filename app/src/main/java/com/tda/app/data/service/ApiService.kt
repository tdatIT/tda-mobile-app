package com.tda.app.data.service

import com.tda.app.model.District
import com.tda.app.model.Province
import com.tda.app.model.request.LoginRequest
import com.tda.app.model.request.RegisterAccount
import com.tda.app.model.response.CategoryResp
import com.tda.app.model.response.CustomResponse
import com.tda.app.model.response.LoginResponse
import com.tda.app.model.response.ProductResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("auth/login")
    suspend fun login(@Body body: LoginRequest): LoginResponse

    @POST("auth/register")
    suspend fun register(@Body body: RegisterAccount): CustomResponse

    @POST("auth/verify-account")
    suspend fun verify(@Query("token") token: String): CustomResponse

    //province
    @GET("p/")
    suspend fun getAllProvince(): List<Province>

    @GET("p/{code}")
    suspend fun getAllDistrictInProvince(
        @Path("code") code: Int, @Query("depth") depth: Int
    ): Province

    @GET("d/{code}")
    suspend fun getAllWard(
        @Path("code") code: Int, @Query("depth") depth: Int
    ): District

    @GET("products")
    suspend fun getAllProducts(
        @Query("page") page: Int, @Query("size") size: Int
    ): List<ProductResponse>

    @GET("categories")
    suspend fun getCategories(
        @Query("size") size: Int
    ): List<CategoryResp>

    @GET("categories")
    suspend fun getAllCategories(): List<CategoryResp>

    @GET("products/category/{categoryCode}")
    suspend fun getAllProductByCategoryCode(@Path("categoryCode") categoryCode: String): List<ProductResponse>

    @GET("products/{code}")
    suspend fun getProductByCode(@Path("code") code: String): ProductResponse

    @GET("products/popular")
    suspend fun getPopularProduct(): List<ProductResponse>

    @GET("products/best-seller")
    suspend fun getBestSellerProduct(): List<ProductResponse>

    @GET("search/p")
    suspend fun getProductByKeyword(@Query("keyword") keyword: String): List<ProductResponse>

}