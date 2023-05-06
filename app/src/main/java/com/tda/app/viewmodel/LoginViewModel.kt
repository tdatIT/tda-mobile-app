package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.service.RetrofitClient
import com.tda.app.model.request.LoginRequest
import com.tda.app.model.response.LoginResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow<LoginResponse?>(null)
    val state: StateFlow<LoginResponse?> = _state

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val body = LoginRequest(email, password)
                val resp = RetrofitClient.retrofit_TDA_API.login(body)
                _state.value = resp
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }

}