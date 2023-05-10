package com.tda.app.data.service

import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val URL_API_BASE = "http://api.tdawebservice.tech/api/v1/"
    private const val URL_VIETNAM_API = "https://provinces.open-api.vn/api/"

    private val retrofit_TDA: Retrofit = Retrofit.Builder()
        .baseUrl(URL_API_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofit_Province: Retrofit = Retrofit.Builder()
        .baseUrl(URL_VIETNAM_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val retrofit_TDA_API: ApiService = retrofit_TDA.create(ApiService::class.java)
    val retrofit_PV_API: ApiService = retrofit_Province.create(ApiService::class.java)
}