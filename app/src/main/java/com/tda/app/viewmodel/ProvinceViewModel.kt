package com.tda.provinceapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.service.RetrofitClient
import com.tda.provinceapi.model.Province
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProvinceViewModel : ViewModel() {
    private val _provinces = MutableStateFlow(emptyList<Province>())

    val provinces: StateFlow<List<Province>> = _provinces

    init {
        getProvinces()
    }

    private fun getProvinces() {
        viewModelScope.launch {
            val response = RetrofitClient.retrofit_PV_API.getAllProvince()
            _provinces.value = response
        }
    }
}