package com.tda.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.UserRemote
import com.tda.app.data.repository.UserRepository
import com.tda.app.model.Resource
import com.tda.app.model.User
import com.tda.app.model.request.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _state = MutableStateFlow<Int>(0)
    val state: StateFlow<Int> = _state

    private val userRemote = UserRemote()

    init {
        getUserFromDB()
    }

    fun continueLogin() {
        _state.value = 0
    }

    fun getUserFromDB() {
        viewModelScope.launch {
            repository.getUser()?.let {
                if (repository.checkAvalaibleUser()) {
                    _state.value = 1
                    Log.i("logged-status", "UserId: ${it.userId}")
                } else {
                    _state.value = 0
                    Log.i("jwt o2auth has expired", "Remove old user from db")
                    repository.deleteAccount(it.id)
                }
            } ?: run {
                Log.i("logged-status", "Not found account logged")
            }
        }
    }


    fun loginAccount(email: String, password: String) {
        viewModelScope.launch {
            val body = LoginRequest(email, password)
            userRemote.loginAccount(body = body).let { data ->
                if (data is Resource.Success) {
                    data.data?.let {
                        val user = it.mapperToUser()
                        _state.value = 1
                        repository.insertUserInfo(user)
                        Log.e("auth-success", "UserId: ${it.user.userId}")
                    }
                } else {
                    _state.value = 2
                }
            }
        }
    }

}


