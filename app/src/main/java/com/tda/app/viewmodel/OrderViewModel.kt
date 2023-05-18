package com.tda.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tda.app.data.repository.CartRemote
import com.tda.app.data.repository.OrderRemote
import com.tda.app.data.repository.UserRepository
import com.tda.app.model.Order
import com.tda.app.model.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class OrderViewModel @Inject constructor(
    val orderRemote: OrderRemote,
    val userRepository: UserRepository,
    val cartRemote: CartRemote
) : ViewModel() {
    private val _state = MutableStateFlow(0)
    val orderStatus: StateFlow<Int> = _state

    fun createOrder(
        shippingCosts: Int,
        payment: Int,
        addressId: Long,
        discount: Double,
        total: Double,
        voucher: String? = null,
    ) {
        viewModelScope.launch {
            val user = userRepository.getUser()

            user?.let {

                val cartItem = cartRemote.getAllItemInCart(it.jwt)
                var items: MutableList<Long> = mutableListOf()
                for (i in cartItem.data!!) {
                    items.add(i.itemId)
                }
                val order = Order(
                    userId = user.userId,
                    address_id = addressId,
                    shippingCosts = shippingCosts,
                    paymentMethod = payment,
                    cart_items = items,
                    total = total,
                    discount = discount,
                    voucher_code = voucher
                )
                if (order != null) {
                    orderRemote.createNewOrder(it.jwt, order).let { resp ->
                        if (resp is Resource.Success)
                            _state.value = 1
                        else
                            _state.value = 2
                    }
                }
            }
        }
    }
}
