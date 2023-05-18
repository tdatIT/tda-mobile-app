package com.tda.app.data.service

import com.tda.app.model.Order
import com.tda.app.model.request.AddressRequest
import com.tda.app.model.request.CartItemRequest
import com.tda.app.model.request.ChangeInfo
import com.tda.app.model.request.ChangePassword
import com.tda.app.model.response.AddressResponse
import com.tda.app.model.response.CartItemResponse
import com.tda.app.model.response.CustomResponse
import com.tda.app.model.response.OrderResponse
import com.tda.app.model.response.ShippingResponse
import com.tda.app.model.response.WishlistResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface JwtApiService {
    @GET("users/addresses")
    suspend fun getAllAddressOfUser(@Header("Authorization") token: String): List<AddressResponse>

    @POST("users/addresses")
    suspend fun addNewAddress(@Header("Authorization") token: String, @Body body: AddressRequest)

    @PATCH("users/change-info")
    suspend fun changeInfo(@Header("Authorization") token: String, @Body body: ChangeInfo)

    @PATCH("users/change-password")
    suspend fun changPassword(@Header("Authorization") token: String, @Body body: ChangePassword)

    @PATCH("users/change-avatar")
    suspend fun changAvatar(
        @Header("Authorization") token: String,
        @Part("image_file") file: MultipartBody.Part
    )

    @GET("cart")
    suspend fun viewCart(@Header("Authorization") token: String): List<CartItemResponse>

    @POST("cart")
    suspend fun addItem(
        @Header("Authorization") token: String,
        @Body item: CartItemRequest
    ): String

    @PUT("cart/{productCode}")
    suspend fun updateItem(
        @Header("Authorization") token: String,
        @Path("productCode") productCode: String,
        @Query("quantity") quantity: Int
    ): String

    @DELETE("cart/{productCode}")
    suspend fun deleteItem(
        @Header("Authorization") token: String,
        @Path("productCode") productCode: String
    ): String

    @GET("cart/total-quantity")
    suspend fun totalQuantity(
        @Header("Authorization") token: String
    ): Int

    @GET("cart/total-discount")
    suspend fun totalDiscount(
        @Header("Authorization") token: String
    ): Double

    @GET("cart/total-amount")
    suspend fun totalAmount(
        @Header("Authorization") token: String
    ): Double

    @POST("orders/user")
    suspend fun createOrder(
        @Header("Authorization") token: String, @Body order: Order
    ): String

    @GET("shipping-cost-calculator")
    suspend fun calculateShipping(@Query("addressId") addressId: Long): ShippingResponse?

    @GET("wishlists")
    suspend fun getAllWishlist(@Header("Authorization") token: String): List<WishlistResponse>

    @POST("wishlists")
    suspend fun addWishlist(
        @Header("Authorization") token: String,
        @Query("productCode") productCode: String
    ): CustomResponse

    @DELETE("wishlists/{itemId}")
    suspend fun removeWishlist(
        @Header("Authorization") token: String,
        @Path("itemId") itemId: Long
    ): CustomResponse

}