package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.service.RetrofitClient
import com.tda.app.model.District
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DistrictViewModel : ViewModel() {
    private val _district = MutableStateFlow(emptyList<District>())
    val district: StateFlow<List<District>> = _district

    fun fetchData(code: Int) {
        getDistricts(code)
    }

    private fun getDistricts(code: Int) {
        viewModelScope.launch {
            val resp = RetrofitClient.retrofit_PV_API.getAllDistrictInProvince(code, 2)
            if (resp != null) {
                _district.value = resp.districts
            }
        }
    }
}