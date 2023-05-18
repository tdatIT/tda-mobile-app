package com.tda.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.CartRemote
import com.tda.app.data.repository.UserRepository
import com.tda.app.model.request.CartItemRequest
import com.tda.app.model.response.CartItemResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    val remote: CartRemote,
    userRepository: UserRepository
) :
    ViewModel() {
    private val _state = MutableStateFlow(emptyList<CartItemResponse>())
    val cartItems: StateFlow<List<CartItemResponse>> = _state

    init {
        viewModelScope.launch {
            val user = userRepository.getUser()
            if (user != null) {
                getAllItem(user.jwt)
            }
        }
    }

    fun getAllItem(jwt: String) {
        viewModelScope.launch {
            remote.getAllItemInCart(jwt)?.let { resp ->
                resp.data?.let {
                    _state.value = it
                }
                resp.message?.let { Log.i("get-cart", it) }
            }
        }
    }

    fun addItem(jwt: String, productCode: String, quantity: Int) {
        viewModelScope.launch {
            val item = CartItemRequest(productCode, quantity)
            remote.addItem(jwt, item)?.let { resp ->
                resp.data?.let {
                    getAllItem(jwt)
                    Log.i("add-cart", it)
                }
            }

        }
    }

    fun updateItem(jwt: String, productCode: String, quantity: Int) {
        viewModelScope.launch {
            remote.updateQuantity(jwt, productCode, quantity)?.let { resp ->
                resp.data?.let {
                    getAllItem(jwt)
                    Log.i("update-cart", it)
                }
            }

        }
    }

    fun deleteItem(jwt: String, productCode: String) {
        viewModelScope.launch {
            remote.deleteItem(jwt, productCode)?.let { resp ->
                resp.data?.let {
                    getAllItem(jwt)
                    Log.i("delete-cart", it)
                }
            }

        }
    }
}