package com.tda.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.CartRemote
import com.tda.app.data.repository.UserRepository
import com.tda.app.model.response.CartItemResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TotalCartViewModel @Inject constructor(
    val remote: CartRemote,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow<Double>(0.0)
    val total: StateFlow<Double> = _state

    init {
        viewModelScope.launch {
            val user = userRepository.getUser();
            user?.let {
                getAllItem(it.jwt)
            }
        }
    }

    fun getAllItem(jwt: String) {
        viewModelScope.launch {
            remote.totalAmount(jwt).let { resp ->
                resp.data?.let {
                    _state.value = it
                }
                resp.message?.let { Log.i("get-total", it) }
            }
        }
    }

}