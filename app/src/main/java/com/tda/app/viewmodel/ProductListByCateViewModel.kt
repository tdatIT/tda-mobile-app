package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.ProductRemote
import com.tda.app.model.Resource
import com.tda.app.model.response.ProductResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductListByCateViewModel : ViewModel() {
    private val _state = MutableStateFlow(emptyList<ProductResponse>())
    var state: StateFlow<List<ProductResponse>> = _state

    private val repository = ProductRemote()

    fun getProducts(code: String) {
        viewModelScope.launch {
            repository.getProductsByCategoryCode(code)?.let {
                if (it is Resource.Success) {
                    _state.value = it.data!!
                }
            }
        }
    }
}