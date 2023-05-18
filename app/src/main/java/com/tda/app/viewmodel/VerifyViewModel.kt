package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.UserRemote
import com.tda.app.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyViewModel @Inject constructor(val userRemote: UserRemote) : ViewModel() {

    private val _state = MutableStateFlow(0)
    val state: StateFlow<Int> = _state

    fun verifyAccount(token: String) {
        viewModelScope.launch {
            userRemote.verifyAccount(token).let {
                if (it is Resource.Success)
                    _state.value = 1
                else
                    _state.value = 2
            }
        }
    }

    fun continueVerify() {
        _state.value = 0
    }

}


