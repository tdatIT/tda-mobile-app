package com.tda.app.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val URL_API_BASE = "http://192.168.1.145:8080/api/v1/"
    private var retrofit: Retrofit?=null

    fun getRetrofitApiForTDA(): Retrofit? {
        retrofit = Retrofit.Builder()
            .baseUrl(URL_API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

}