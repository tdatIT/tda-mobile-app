package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.OrderRemote
import com.tda.app.data.repository.UserRepository
import com.tda.app.model.Resource
import com.tda.app.model.response.OrderResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderDetailViewModel @Inject constructor(
    val remote: OrderRemote,
    val userRepository: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow<OrderResponse?>(null)
    val order: StateFlow<OrderResponse?> = _state

    fun fetchData(orderId: Long) {
        viewModelScope.launch {
            val user = userRepository.getUser()
            user?.let {
                remote.getOrderDetails(it.jwt, orderId).let { resp ->
                    if (resp is Resource.Success) {
                        _state.value = resp.data
                    }
                }
            }
        }
    }

}