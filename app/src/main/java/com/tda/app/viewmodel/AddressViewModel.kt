package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.AddressRemote
import com.tda.app.data.repository.UserRepository
import com.tda.app.model.Resource
import com.tda.app.model.response.AddressResponse
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    val remote: AddressRemote,
    userRepository: UserRepository
) : ViewModel() {

    private var _state = MutableStateFlow(emptyList<AddressResponse>())
    var state: StateFlow<List<AddressResponse>> = _state


    init {
        viewModelScope.launch {
            val user = userRepository.getUser()
            user?.let {
                fetchAll(it.jwt)
            }
        }
    }

    fun fetchAll(token: String) {
        viewModelScope.launch {
            remote.fetchAllData(token).let {
                if (it is Resource.Success) {
                    _state.value = it.data!!
                }
            }
        }
    }
}