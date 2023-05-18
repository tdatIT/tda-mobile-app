package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.DivisionRemote
import com.tda.app.model.Resource
import com.tda.provinceapi.model.Ward
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WardViewModel @Inject constructor(val divisionRemote: DivisionRemote) : ViewModel() {
    private val _wards = MutableStateFlow(emptyList<Ward>())
    val wards: StateFlow<List<Ward>> = _wards

    fun fecthData(code: Int) {
        getWards(code)
    }

    private fun getWards(code: Int) {
        viewModelScope.launch {
            divisionRemote.getWards(code).let {
                if (it is Resource.Success) {
                    it.data?.let { resp -> _wards.value = resp.wards }
                }
            }
        }
    }
}