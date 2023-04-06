package com.tda.app.repository.service;

import com.tda.app.data.model.LoginRequest;
import com.tda.app.data.model.LoginResponse;
import com.tda.app.data.model.ProvinceAPI;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest body);


}
