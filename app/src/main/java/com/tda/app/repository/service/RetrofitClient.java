package com.tda.app.repository.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static String URL_API_BASE = "http://api.tdawebservice.tech/api/v1/";
    private static String URL_VIETNAM_API = "https://provinces.open-api.vn/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitForTDA() {
        retrofit = new Retrofit.Builder()
                .baseUrl(URL_API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static Retrofit getRetrofitApiForProviceData() {
        return new Retrofit.Builder()
                .baseUrl(URL_VIETNAM_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}