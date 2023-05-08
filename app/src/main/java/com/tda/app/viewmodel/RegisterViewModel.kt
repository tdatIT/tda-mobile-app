package com.tda.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.service.RetrofitClient
import com.tda.app.model.UserAddress
import com.tda.app.model.request.AddressRequest
import com.tda.app.model.request.RegisterAccount
import com.tda.app.model.response.CustomResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

class RegisterViewModel : ViewModel() {
    private val _state = MutableStateFlow<CustomResponse?>(null)
    val state: StateFlow<CustomResponse?> = _state

    fun registerAccount(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String,
        phone: String,
        address_detail: String,
        provinceCode: Int,
        districtCode: Int,
        wardCode: Int
    ) {
        try {
            viewModelScope.launch {
                val newAccount = RegisterAccount(
                    firstname = firstName,
                    lastname = lastName,
                    email = email,
                    hashPassword = password,
                    confirmPassword = confirmPassword,
                    phone = phone,
                    address_detail = address_detail,
                    defaultAddress = AddressRequest(
                        province_code = provinceCode,
                        district_code = districtCode,
                        ward_code = wardCode,
                        detail = address_detail,
                        phone = null
                    )
                )
                val resp = RetrofitClient.retrofit_TDA_API.register(newAccount)?.awaitResponse()
                resp?.let {
                    if (!it.isSuccessful) {
                        Log.e(
                            "login-process",
                            "FAIL - CODE: ${resp.code()} - Message ${resp.message()}"
                        )
                    } else {
                        _state.value = resp.body()
                        Log.e(
                            "login-process",
                            "Success - CODE: ${_state.value?.code} - Message ${_state.value?.message}"
                        )
                    }
                }

            }
        } catch (t: Throwable) {
            Log.e("login-process", "Error: ${t.message}")
        }
    }
}


