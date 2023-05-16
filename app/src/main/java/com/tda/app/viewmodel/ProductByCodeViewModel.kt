package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.ProductRepository
import com.tda.app.model.Resource
import com.tda.app.model.response.ProductResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductByCodeViewModel : ViewModel() {
    private val _state = MutableStateFlow<ProductResponse?>(null)
    val state: StateFlow<ProductResponse?> = _state

    val repository = ProductRepository()

    fun fecthData(code: String) {
        viewModelScope.launch {
            repository.getProductsByCode(code).let {
                if (it is Resource.Success) {
                    _state.value = it.data!!
                }
            }
        }
    }
}
