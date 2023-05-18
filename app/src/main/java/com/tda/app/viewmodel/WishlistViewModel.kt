package com.tda.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.UserRepository
import com.tda.app.data.repository.WishlistRemote
import com.tda.app.model.response.WishlistResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    val remote: WishlistRemote,
    userRepository: UserRepository
) : ViewModel() {
    private val _state = MutableStateFlow(emptyList<WishlistResponse>())
    val items: StateFlow<List<WishlistResponse>> = _state

    init {
        viewModelScope.launch {
            val user = userRepository.getUser()
            user?.let {
                fecthData(it.jwt)
            }
        }
    }

    fun fecthData(jwt: String) {
        viewModelScope.launch {
            remote.getAllItem(jwt).let { resp ->
                resp.data?.let {
                    _state.value = it
                }
                resp.message?.let { Log.i("get-item", it) }
            }
        }
    }

    fun addItem(jwt: String, productCode: String) {
        viewModelScope.launch {
            remote.addItem(jwt, productCode).let { resp ->
                resp.data?.let {
                    fecthData(jwt)
                    Log.i("add-item", it.message)
                }
            }
        }
    }

    fun delete(jwt: String, itemId: Long) {
        viewModelScope.launch {
            remote.deleteItem(jwt, itemId).let { resp ->
                resp.data?.let {
                    fecthData(jwt)
                    Log.i("update-cart", it.message)
                }
            }
        }
    }


}