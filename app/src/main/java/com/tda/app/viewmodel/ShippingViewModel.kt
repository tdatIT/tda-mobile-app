package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.ShippingRemote
import com.tda.app.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShippingViewModel @Inject constructor(val remote: ShippingRemote) : ViewModel() {

    private val _cost = MutableStateFlow(0)
    val cost: StateFlow<Int> = _cost

    fun getCalculate(addressId: Long) {
        viewModelScope.launch {
            remote.calculateShipping(addressId).let {
                if (it is Resource.Success) {
                    it.data?.let { shippingResponse ->
                        _cost.value = shippingResponse.cost
                    }
                }
            }
        }
    }
}