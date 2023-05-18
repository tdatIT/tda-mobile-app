package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.DivisionRemote
import com.tda.app.model.District
import com.tda.app.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DistrictViewModel @Inject constructor(val divisionRemote: DivisionRemote) : ViewModel() {
    private val _district = MutableStateFlow(emptyList<District>())
    val district: StateFlow<List<District>> = _district

    fun fetchData(code: Int) {
        getDistricts(code)
    }

    private fun getDistricts(code: Int) {
        viewModelScope.launch {
            divisionRemote.getDistrict(code).let {
                if (it is Resource.Success) {
                    it.data?.let { resp -> _district.value = resp.districts }
                }
            }
        }
    }
}