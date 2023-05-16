package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.ProductRepository
import com.tda.app.model.Resource
import com.tda.app.model.response.ProductResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PopularProductViewModel : ViewModel() {
    private val _state = MutableStateFlow(emptyList<ProductResponse>())
    val state: StateFlow<List<ProductResponse>> = _state

    val repository = ProductRepository()

    init {
        fecthData()
    }

    fun fecthData() {
        viewModelScope.launch {
            repository.getPopular().let {
                if (it is Resource.Success) {
                    _state.value = it.data!!
                }
            }
        }
    }
}
