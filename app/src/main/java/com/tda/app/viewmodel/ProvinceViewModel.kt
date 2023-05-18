package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.DivisionRemote
import com.tda.app.data.service.RetrofitClient
import com.tda.app.model.Province
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProvinceViewModel @Inject constructor(val divisionRemote: DivisionRemote) : ViewModel() {

    private val _provinces = MutableStateFlow(emptyList<Province>())
    val provinces: StateFlow<List<Province>> = _provinces

    init {
        getProvinces()
    }

    private fun getProvinces() {
        viewModelScope.launch {
            divisionRemote.getAllProvince().let {
                it.data?.let { resp ->
                    _provinces.value = resp
                }
            }

        }
    }

    private fun getProvincesV2() {
        viewModelScope.launch {
            val response = RetrofitClient.retrofit_PV_API.getAllProvince()
            _provinces.value = response
        }
    }
}