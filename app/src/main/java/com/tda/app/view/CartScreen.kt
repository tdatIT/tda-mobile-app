package com.tda.app.view

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import com.tda.app.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.tda.app.model.response.CartItemResponse
import com.tda.app.navigation.Screen
import com.tda.app.ui.theme.bgwhitelight
import com.tda.app.ui.theme.black
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.white
import com.tda.app.utils.Constants
import com.tda.app.viewmodel.CartViewModel
import com.tda.app.viewmodel.DiscountCartViewModel

import com.tda.app.viewmodel.UserViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CartScreen(
    nav: NavController,
    cartViewModel: CartViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    discountCartViewModel: DiscountCartViewModel = hiltViewModel()
) {
    val user by userViewModel.state.collectAsState()
    val cartItems by cartViewModel.cartItems.collectAsState()
    val price by discountCartViewModel.total.collectAsState()

    userViewModel.getUserFromDB()

    user?.let {
        cartViewModel.getAllItem(it.jwt)
    }

    var display by remember { mutableStateOf(false) }

    if (display) {
        ProgressIndicator()
    }

    Scaffold(
        bottomBar = {
            Column(
                Modifier
                    .background(white)
                    .padding(top = 10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Tổng thanh toán",
                        fontSize = 16.sp,
                        color = black,
                        modifier = Modifier.padding(start = 10.dp)
                    )

                    Text(
                        text = Constants.formatPrice(price) + "đ",
                        fontSize = 20.sp,
                        color = colorPrimary,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(end = 10.dp)
                    )

                }
                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = { nav.navigate(Screen.CheckoutScreen.route) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary),
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Mua hàng",
                        color = white,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = white,
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(20.dp, 20.dp)
                    )
                }

            }
        },
        topBar = { HeaderCartItems(nav) },
        backgroundColor = bgwhitelight
    ) { padding ->
        Box(Modifier.padding(padding)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                if (cartItems.isNotEmpty() && user != null) {
                    itemsIndexed(cartItems) { _, item ->
                        Items(
                            item, user!!.jwt,
                            fetchData = { jwt ->
                                cartViewModel.getAllItem(jwt)
                            },
                            update = { jwt, productCode, quantity ->
                                cartViewModel.updateItem(jwt, productCode, quantity)
                            },
                            delete = { jwt, productCode ->
                                cartViewModel.deleteItem(jwt, productCode)
                            },
                            updatePrice = { jwt ->
                                discountCartViewModel.fecthData(jwt)
                            }
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                    }
                }
                if (cartItems.isEmpty()) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Giỏ hàng trống.",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.padding(bottom = 100.dp))
        }
    }
}

@Composable
fun Items(
    item: CartItemResponse,
    jwt: String,
    fetchData: (jwt: String) -> Unit,
    update: (jwt: String, productCode: String, quantity: Int) -> Unit,
    delete: (jwt: String, productCode: String) -> Unit,
    updatePrice: (jwt: String) -> Unit
) {
    var display by remember {
        mutableStateOf(true)
    }

    AnimatedVisibility(visible = display) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(white)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 10.dp),
                    painter = rememberAsyncImagePainter(model = item.images_file[0]),
                    contentDescription = "",
                )
                Column() {
                    Row() {
                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier
                                .weight(0.9f)
                                .wrapContentHeight()
                        ) {
                            Text(
                                text = item.productName.uppercase(),
                                fontSize = 16.sp,
                                color = black,
                                fontWeight = FontWeight.Bold,
                                maxLines = 2,
                                modifier = Modifier.padding(bottom = 34.dp)
                            )
                            Text(
                                text = if (item.promotionPrice > 0)
                                    Constants.formatPrice(item.promotionPrice) + "đ"
                                else
                                    Constants.formatPrice(item.price) + "đ",
                                fontSize = 16.sp,
                                color = colorPrimary,
                            )
                        }
                        Column() {
                            IconButton(onClick = {
                                delete(jwt, item.productCode)
                                fetchData(jwt)
                                display = false
                                updatePrice(jwt)
                            }) {
                                Icon(
                                    imageVector = Icons.Outlined.Close,
                                    contentDescription = "Delete item"
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        var counter by remember {
                            mutableStateOf(item.quantity)
                        }
                        Text(
                            text = "Số lượng:",
                            color = Color.Gray,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Box(
                            modifier = Modifier
                                .width(110.dp)
                                .height(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFFE5F4EF))
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(colorPrimary)
                                        .size(32.dp, 32.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    IconButton(onClick = {
                                        counter--
                                        if (counter == 0) {
                                            display = false
                                            delete(jwt, item.productCode)
                                        } else {
                                            update(jwt, item.productCode, counter)
                                        }
                                        Log.i("cart-status", "quantity: ${counter}")
                                        fetchData(jwt)
                                        updatePrice(jwt)
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_baseline_minimize_24),
                                            contentDescription = "dec quantity",
                                            modifier = Modifier.padding(bottom = 15.dp),
                                            tint = white
                                        )
                                    }
                                }
                                Text(
                                    text = "$counter",
                                    color = colorPrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp
                                )
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(colorPrimary)
                                        .size(32.dp, 32.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    IconButton(onClick = {
                                        counter++
                                        update(jwt, item.productCode, counter)
                                        fetchData(jwt)
                                        updatePrice(jwt)
                                        Log.i("cart-status", "quantity: $counter")
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Add,
                                            contentDescription = "",
                                            tint = white,
                                            modifier = Modifier.size(20.dp, 20.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun HeaderCartItems(nav: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(white),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            nav.popBackStack()
        }
        ) {
            Icon(
                modifier = Modifier.size(32.dp, 32.dp),
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "",
                tint = colorPrimary
            )
        }
        Text(
            text = "Giỏ hàng",
            modifier = Modifier.padding(end = 160.dp, bottom = 20.dp, top = 20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
        )
    }
}


@Composable
fun CheckoutDetails(priceState: State<Double>) {


}


@Preview(showBackground = true)
@Composable
fun previewScreensas() {
    val nav = rememberNavController()
    CartScreen(nav = nav)
}


