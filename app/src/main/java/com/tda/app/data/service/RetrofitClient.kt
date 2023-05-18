package com.tda.app.data.service

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object RetrofitClient {
    private const val URL_API_BASE = "http://api.tdawebservice.tech/api/v1/"
    private const val URL_VIETNAM_API = "https://provinces.open-api.vn/api/"
    private val gson = GsonBuilder()
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    private val retrofit_TDA: Retrofit = Retrofit.Builder()
        .baseUrl(URL_API_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofit_Province: Retrofit = Retrofit.Builder()
        .baseUrl(URL_VIETNAM_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofit_TDA_hasJWT: Retrofit = Retrofit.Builder()
        .baseUrl(URL_API_BASE)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofit_TDA_API: ApiService = retrofit_TDA.create(ApiService::class.java)
    val retrofit_TDA_JWT: JwtApiService = retrofit_TDA_hasJWT.create(JwtApiService::class.java)
    val retrofit_PV_API: ApiService = retrofit_Province.create(ApiService::class.java)
}