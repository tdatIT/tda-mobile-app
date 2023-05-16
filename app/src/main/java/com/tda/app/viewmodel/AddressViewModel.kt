package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.AddressRemote
import com.tda.app.data.repository.ProductRemote
import com.tda.app.model.Resource
import com.tda.app.model.response.AddressResponse
import com.tda.app.model.response.ProductResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddressViewModel : ViewModel() {

    private var _state = MutableStateFlow(emptyList<AddressResponse>())
    var state: StateFlow<List<AddressResponse>> = _state

    private val repository = AddressRemote()

    fun fetchAll(token: String) {
        viewModelScope.launch {
            repository.fetchAllData(token)?.let {
                if (it is Resource.Success) {
                    _state.value = it.data!!
                }
            }
        }
    }
}