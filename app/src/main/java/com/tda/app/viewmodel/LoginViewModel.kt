package com.tda.app.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.DataStoreUserLogged
import com.tda.app.data.service.RetrofitClient
import com.tda.app.model.request.LoginRequest
import com.tda.app.model.response.LoginResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow<LoginResponse?>(null)
    val state: StateFlow<LoginResponse?> = _state


    fun login(email: String, password: String, context: Context) {
        viewModelScope.launch {
            try {
                val dataStore = DataStoreUserLogged(context)
                val body = LoginRequest(email, password)
                val resp = RetrofitClient.retrofit_TDA_API.login(body)
                resp?.let {
                    dataStore.saveJwt(jwt = resp.jwt)
                    dataStore.saveExpiryDate(resp.expiryDate)
                    Log.i("auth", "Login success jwt was stored into datastore")
                    Log.i("jwt", resp.jwt)
                }
            } catch (e: Exception) {
                Log.e("auth", "Login fail cause: ${e.message}")
            }
        }
    }

}