package com.tda.app.view

import NavigationBottomBar
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import com.tda.app.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.tda.app.component.CheckoutDetail
import com.tda.app.component.DropDownMenuAddress
import com.tda.app.model.response.CartItemResponse
import com.tda.app.navigation.Screen
import com.tda.app.ui.theme.*
import com.tda.app.utils.Constants
import com.tda.app.viewmodel.AddressViewModel
import com.tda.app.viewmodel.CartViewModel
import com.tda.app.viewmodel.DiscountCartViewModel
import com.tda.app.viewmodel.OrderViewModel
import com.tda.app.viewmodel.ShippingViewModel
import com.tda.app.viewmodel.TotalCartViewModel


@Composable
fun CheckoutScreen(
    navController: NavController,
    addressViewModel: AddressViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel(),
    totalCartViewModel: TotalCartViewModel = hiltViewModel(),
    discountCartViewModel: DiscountCartViewModel = hiltViewModel(),
    shippingViewModel: ShippingViewModel = hiltViewModel(),
    orderViewModel: OrderViewModel = hiltViewModel()
) {
    val cartItem by cartViewModel.cartItems.collectAsState()
    val address by addressViewModel.state.collectAsState()
    val total by totalCartViewModel.total.collectAsState()
    val discount by discountCartViewModel.total.collectAsState()
    val cost by shippingViewModel.cost.collectAsState()
    val orderStatus by orderViewModel.orderStatus.collectAsState()

    var addressId by remember { mutableStateOf(0L) }

    when (orderStatus) {
        0 -> {}
        1 -> {
            LaunchedEffect(key1 = "") {
                navController.navigate(Screen.CheckOutSuccessScreen.route)
            }
        }
    }

    Scaffold(topBar = { HeaderCheckoutItems(navController) },
        bottomBar = { NavigationBottomBar(navController = navController) })
    { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .padding(padding)
        ) {
            itemsIndexed(cartItem) { _, item ->
                ProductItems(item)
                Spacer(modifier = Modifier.padding(20.dp))
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                        .clickable {
                        }
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        Row() {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = colorPrimary,
                                modifier = Modifier.padding(end = 10.dp)
                            )
                            Text(
                                text = "Giá giao đến địa chỉ: $cost đ",
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(bottom = 5.dp)
                                    .height(40.dp),
                                textAlign = TextAlign.Left,
                                color = GrayText
                            )
                        }
                        Column(
                            Modifier
                                .fillMaxHeight()
                                .width(300.dp)
                                .fillMaxWidth()
                        ) {
                            DropDownMenuAddress(
                                list = address,
                                content = "Chọn địa chỉ giao hàng",
                                onItemSelected = { id -> addressId = id },
                                onUpdate = { id -> shippingViewModel.getCalculate(id) }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(10.dp))
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clip(RoundedCornerShape(16.dp))
                        .background(white)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {

                        Text(
                            text = "Chi tiết hóa đơn",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "Tổng đơn hàng",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )

                            Text(
                                text = Constants.formatPrice(total) + "đ",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "Giảm giá",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )

                            Text(
                                text = "-" + Constants.formatPrice(total - discount) + "đ",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "Thành tiền",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )

                            Text(
                                text = Constants.formatPrice(discount) + "đ",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Vận chuyển",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )

                            Text(
                                text = Constants.formatPrice(cost + 0.0) + "đ",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Voucher",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )

                            Text(
                                text = "-0đ",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }
                        Spacer(modifier = Modifier.padding(10.dp))

                        Divider(color = Color.Gray, thickness = 1.dp)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = "Tổng thanh toán",
                                fontSize = 14.sp,
                                color = black,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = Constants.formatPrice(cost + discount),
                                fontSize = 14.sp,
                                color = colorPrimary
                            )
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Button(
                                onClick = {
                                    orderViewModel.createOrder(
                                        shippingCosts = cost,
                                        addressId = addressId,
                                        total = total,
                                        discount = discount,
                                        payment = 1//giao truc tiep
                                    )
                                },
                                colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary),
                                modifier = Modifier
                                    .padding(8.dp)
                                    .fillMaxWidth()
                                    .height(60.dp),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text(
                                    text = "Thanh Toán",
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
                    }
                }

                Spacer(modifier = Modifier.padding(10.dp))
            }


        }
    }
}

@Composable
fun ProductItems(item: CartItemResponse) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(16.dp))
            .background(white)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(0.3f)
                    .height(70.dp)
                    .clip(RoundedCornerShape(12.dp)),
            ) {
                Image(
                    modifier = Modifier
                        .size(70.dp),
                    painter = rememberAsyncImagePainter(model = item.images_file[0]),
                    contentDescription = "",
                )
            }
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(0.9f)
                    .wrapContentHeight()
            ) {
                Text(
                    text = item.productName,
                    fontSize = 16.sp,
                    color = black,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(top = 10.dp, end = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (item.promotionPrice > 0)
                            Constants.formatPrice(item.promotionPrice)
                        else
                            Constants.formatPrice(item.price) + "đ",
                        fontSize = 16.sp,
                        color = colorPrimary,
                    )
                    Text(
                        text = "X",
                        fontSize = 16.sp,
                    )
                    Row() {
                        Text(
                            text = "Số lượng: ",
                            color = Color.Gray,
                            fontSize = 16.sp,
                            modifier = Modifier

                        )
                        Text(
                            text = item.quantity.toString(),
                            color = Color.Gray,
                            fontSize = 16.sp,
                        )
                    }

                }
            }

        }
    }
}

@Preview
@Composable
fun PreviewListProductScreens() {
    val nav = rememberNavController()
    CheckoutScreen(nav)
}