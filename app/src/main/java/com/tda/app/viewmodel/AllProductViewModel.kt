package com.tda.app.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.ProductRepository
import com.tda.app.model.Resource
import com.tda.app.model.response.ProductResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AllProductViewModel() :
    ViewModel() {
    private var _state = MutableStateFlow(emptyList<ProductResponse>())
    var state: StateFlow<List<ProductResponse>> = _state

    private val repository = ProductRepository()

    init {
        getProduct(0, 8)
    }

    fun getProduct(page: Int, size: Int) {
        viewModelScope.launch {
            repository.getProducts(page, size)?.let {
                if (it is Resource.Success) {
                    _state.value = it.data!!
                }
            }
        }
    }

}