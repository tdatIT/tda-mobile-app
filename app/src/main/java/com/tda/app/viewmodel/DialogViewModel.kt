package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DialogViewModel : ViewModel() {
    private val _state = MutableStateFlow(false)
    val isOpened: StateFlow<Boolean> = _state

    fun openDialog() {
        _state.value = true
    }

    fun closeDialog() {
        _state.value = false
    }
}