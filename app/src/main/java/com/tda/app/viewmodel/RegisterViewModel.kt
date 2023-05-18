package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.UserRemote
import com.tda.app.model.Resource
import com.tda.app.model.request.AddressRequest
import com.tda.app.model.request.RegisterAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(val userRemote: UserRemote) : ViewModel() {
    private val _state = MutableStateFlow<Int>(0)
    val state: StateFlow<Int> = _state

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
                    province = provinceCode,
                    district = districtCode,
                    ward = wardCode,
                    detail = address_detail,
                    phone = phone
                )
            )
            userRemote.registerAccount(newAccount).let {
                if (it is Resource.Success)
                    _state.value = 1
                else
                    _state.value = 2
            }
        }
    }

    fun continueRegister() {
        viewModelScope.launch {
            _state.value = 0
        }
    }
}


