package com.tda.app.data.service

import com.tda.app.data.model.JwtResponse
import com.tda.app.data.model.LoginBody
import retrofit2.http.POST
import retrofit2.Call;
import retrofit2.http.Body

interface ApiService {
    //Login api
    @POST("auth/login")
    fun loginRestfulApi(@Body body: LoginBody): Call<JwtResponse>


}