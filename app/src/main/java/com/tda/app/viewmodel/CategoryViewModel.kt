package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.CategoryRemote
import com.tda.app.model.Resource
import com.tda.app.model.response.CategoryResp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    private val _state = MutableStateFlow(emptyList<CategoryResp>())
    val state: StateFlow<List<CategoryResp>> = _state

    private val repository = CategoryRemote()

    init {
        getCategoryList(6)
    }

    fun getCategoryList(size: Int) {
        viewModelScope.launch {
            repository.getCategories(size)?.let {
                if (it is Resource.Success) {
                    _state.value = it.data!!
                }
            }
        }
    }
}