package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.OrderRemote
import com.tda.app.data.repository.UserRepository
import com.tda.app.model.response.OrderResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel @Inject constructor(
    val userRepository: UserRepository,
    private val orderRemote: OrderRemote
) : ViewModel() {
    private val _state = MutableStateFlow(emptyList<OrderResponse>())
    val orders: StateFlow<List<OrderResponse>> = _state

    init {
        fetchData(0,8)
    }
    fun fetchData(page: Int, size: Int) {
        viewModelScope.launch {
            val user = userRepository.getUser()
            user?.let {
                orderRemote.getAllOrderForUser(it.jwt, page, size).let { resp ->
                    resp.data?.let {
                        _state.value = resp.data
                    }
                }
            }
        }
    }
}