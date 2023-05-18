package com.tda.app.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.CartRemote
import com.tda.app.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscountCartViewModel @Inject constructor(
    val remote: CartRemote,
    val userRepository: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow(0.0)
    val total: StateFlow<Double> = _state

    init {
        viewModelScope.launch {
            val user = userRepository.getUser();
            user?.let {
                fecthData(it.jwt)
            }
        }
    }

    fun fecthData(jwt: String) {
        viewModelScope.launch {
            remote.totalDiscount(jwt).let { resp ->
                resp.data?.let {
                    _state.value = it
                }
            }
        }
    }

}