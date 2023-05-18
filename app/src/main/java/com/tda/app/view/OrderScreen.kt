package com.tda.app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.CreditScore
import androidx.compose.material.icons.outlined.Inventory
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.tda.app.model.response.OrderResponse
import com.tda.app.ui.theme.bgwhitelight
import com.tda.app.ui.theme.black
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.favourite
import com.tda.app.ui.theme.white
import com.tda.app.utils.Constants
import com.tda.app.viewmodel.OrderListViewModel
import com.tda.app.viewmodel.UserViewModel

@Composable
fun OrderScreen(
    nav: NavController,
    orderListViewModel: OrderListViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    val user by userViewModel.state.collectAsState()
    val items by orderListViewModel.orders.collectAsState()

    Scaffold(
        topBar = { HeaderOrderItems(nav) },
        backgroundColor = bgwhitelight
    ) { padding ->
        Box(Modifier.padding(padding)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                if (items.isNotEmpty() && user != null) {
                    itemsIndexed(items) { _, item ->
                        OrderItems(
                            nav,
                            item
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                    }
                }
                if (items.isEmpty()) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Chưa có đơn hàng !",
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
fun OrderItems(
    nav: NavController,
    item: OrderResponse
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(white)
            .clickable { nav.navigate("order_detail/${item.orderId}") }
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
                painter = rememberAsyncImagePainter(model = item.order_items[0].productImg),
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
                            text = item.order_items[0].productName,
                            fontSize = 16.sp,
                            color = black,
                            fontWeight = FontWeight.Bold,
                            maxLines = 2,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                        Text(
                            text = "Ngày tạo: ${Constants.formateDate(item.createDate.time)}",
                            fontSize = 16.sp,
                            color = black,
                            maxLines = 2,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = Constants.formatPrice(item.discount) + "đ",
                                fontSize = 16.sp,
                                color = colorPrimary,
                            )
                            when (item.orderStatus) {
                                0 -> {
                                    Icon(
                                        imageVector = Icons.Outlined.Inventory,
                                        contentDescription = "",
                                        tint = favourite
                                    )
                                }

                                1 -> {
                                    Icon(
                                        imageVector = Icons.Outlined.CreditScore,
                                        contentDescription = "",
                                        tint = favourite
                                    )
                                }

                                2 -> {
                                    Icon(
                                        imageVector = Icons.Outlined.Sync,
                                        contentDescription = "",
                                        tint = favourite
                                    )
                                }

                                3 -> {
                                    Icon(
                                        imageVector = Icons.Outlined.CheckCircle,
                                        contentDescription = "",
                                        tint = favourite
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


@Preview
@Composable
fun PreviewOrder() {
    val nav = rememberNavController()
    OrderScreen(nav = nav)
}




