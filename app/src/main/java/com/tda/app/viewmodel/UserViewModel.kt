package com.tda.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.UserRemote
import com.tda.app.data.repository.UserRepository
import com.tda.app.model.User
import com.tda.app.model.request.AddressRequest
import com.tda.app.model.request.ChangeInfo
import com.tda.app.model.request.ChangePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    private val _state = MutableStateFlow<User?>(null)
    val state: StateFlow<User?> = _state

    private val userRemote = UserRemote()

    init {
        getUserFromDB()
    }

    fun getUserFromDB() {
        viewModelScope.launch {
            _state.value = repository.getUser()
            if (_state.value != null)
                if (repository.checkAvalaibleUser())
                    Log.i("logged-status", "UserId: ${_state.value!!.userId}")
                else {
                    Log.i("jwt o2auth has expired", "Remove old user from db")
                    _state.value?.let {
                        repository.deleteAccount(it.id)
                    }
                }
            else
                Log.i("logged-status", "Not found account logged")
        }
    }

    fun changeInfo(firstName: String, lastName: String, phone: String) {
        viewModelScope.launch {
            state.value?.let { user ->
                val authToken = "Bearer ${user.jwt}"
                val body = ChangeInfo(firstName, lastName, phone)
                userRemote.changeInfo(body = body, token = authToken)
                repository.updateAccount(user.id, firstName, lastName, phone)
                getUserFromDB()
                Log.e("change-success", "update info")
            }
        }
    }

    fun changePass(newPass: String, oldPass: String) {
        viewModelScope.launch {
            state.value?.let { user ->
                val authToken = "Bearer ${user.jwt}"
                val body = ChangePassword(newPass, oldPass)
                userRemote.changePassword(body = body, token = authToken)

                Log.e("change-password", "process")
            }
        }

    }

    fun addNewAddress(provine: Int, district: Int, ward: Int, detail: String, phone: String) {
        viewModelScope.launch {
            state.value?.let { user ->
                val authToken = "Bearer ${user.jwt}"
                val body = AddressRequest(
                    province_code = provine,
                    district_code = district,
                    ward_code = ward,
                    phone = phone,
                    detail = detail
                )
                userRemote.addNewAddress(body = body, token = authToken)

                Log.e("add-password", "process")
            }
        }
    }

    fun logout(id: Int) {
        viewModelScope.launch {
            repository.deleteAccount(id)
            _state.value = null
        }
    }
}

