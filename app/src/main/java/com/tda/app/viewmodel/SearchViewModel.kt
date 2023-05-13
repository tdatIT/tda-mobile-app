package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.ProductRespository
import com.tda.app.model.Resource
import com.tda.app.model.response.ProductResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private var _state = MutableStateFlow(emptyList<ProductResponse>())
    var state: StateFlow<List<ProductResponse>> = _state

    val repository = ProductRespository()

    fun getProductByKeyword(keyword: String) {
        viewModelScope.launch {
            repository.getProductByKeyWord(keyword).let {
                if (it is Resource.Success) {
                    _state.value = it.data!!
                }
            }
        }
    }

}