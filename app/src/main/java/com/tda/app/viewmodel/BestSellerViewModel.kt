package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.ProductRespository
import com.tda.app.model.Resource
import com.tda.app.model.response.ProductResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BestSellerViewModel : ViewModel() {
    private val _state = MutableStateFlow(emptyList<ProductResponse>())
    val state: StateFlow<List<ProductResponse>> = _state

    val repository = ProductRespository()

    init {
        fecthData()
    }

    fun fecthData() {
        viewModelScope.launch {
            repository.getBestSeller().let {
                if (it is Resource.Success) {
                    _state.value = it.data!!
                }
            }
        }
    }
}
