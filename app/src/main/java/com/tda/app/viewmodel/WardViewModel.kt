package com.tda.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.service.RetrofitClient
import com.tda.provinceapi.model.Ward
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WardViewModel : ViewModel() {
    private val _wards = MutableStateFlow(emptyList<Ward>())
    val wards: StateFlow<List<Ward>> = _wards

    fun fecthData(code: Int) {
        getWards(code)
    }

    private fun getWards(code: Int) {
        viewModelScope.launch {
            val resp = RetrofitClient.retrofit_PV_API.getAllWard(code, 2)
            try {
                if (resp != null)
                    _wards.value = resp.wards
            } catch (e: Exception) {
                e.stackTrace
                Log.e("Province API", e.stackTrace.toString())
            }
        }


    }
}