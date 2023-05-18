package com.tda.app.view

import NavigationBottomBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.tda.app.model.response.OrderItemResp
import com.tda.app.ui.theme.bgwhitelight
import com.tda.app.ui.theme.black
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.white
import com.tda.app.utils.Constants
import com.tda.app.viewmodel.OrderDetailViewModel
import com.tda.app.viewmodel.UserViewModel


@Composable
fun OrderDetailsScreen(
    navController: NavController,
    orderId: Long,
    orderDetailViewModel: OrderDetailViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
) {
    val user by userViewModel.state.collectAsState()
    orderDetailViewModel.fetchData(orderId)
    val order by orderDetailViewModel.order.collectAsState()

    Scaffold(
        topBar = { HeaderOrderItems(navController) },
        bottomBar = { NavigationBottomBar(navController = navController) },
        backgroundColor = bgwhitelight
    )
    { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .padding(padding)
        ) {

            order?.let {
                itemsIndexed(it.order_items) { _, item ->
                    OrderItems(item)
                    Spacer(modifier = Modifier.padding(20.dp))
                }
            }
            order?.let {
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
                                text = "Thông tin đơn hàng - ID:${it.orderId}",
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
                                    text = "Ngày tạo",
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )

                                Text(
                                    text = Constants.formateDate(it.createDate.time),
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
                                    text = "Người tạo",
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                                user?.let {
                                    Text(
                                        text = it.lastname + " " + it.firstname,
                                        fontSize = 14.sp,
                                        color = colorPrimary
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Text(
                                    text = "Trạng thái",
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )
                                when (it.orderStatus) {
                                    0 -> {
                                        Text(
                                            text = "Đang xác nhận",
                                            fontSize = 14.sp,
                                            color = colorPrimary
                                        )
                                    }

                                    1 -> {
                                        Text(
                                            text = "Đang xử lý",
                                            fontSize = 14.sp,
                                            color = colorPrimary
                                        )
                                    }

                                    2 -> {
                                        Text(
                                            text = "Vận chuyển",
                                            fontSize = 14.sp,
                                            color = colorPrimary
                                        )
                                    }

                                    3 -> {
                                        Text(
                                            text = "Thành công",
                                            fontSize = 14.sp,
                                            color = colorPrimary
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
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
                                    text = Constants.formatPrice(it.total) + "đ",
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
                                    text = "-" + Constants.formatPrice(it.total - it.discount) + "đ",
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
                                    text = Constants.formatPrice(it.discount) + "đ",
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
                                    text = Constants.formatPrice(it.shippingCosts + 0.0) + "đ",
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
                                    text = Constants.formatPrice(it.discount),
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
                                        navController.popBackStack()
                                    },
                                    colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary),
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth()
                                        .height(60.dp),
                                    shape = RoundedCornerShape(16.dp)
                                ) {
                                    Text(
                                        text = "Quay lại",
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
}

@Composable
fun OrderItems(item: OrderItemResp) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(16.dp))
            .background(white)
            .padding(top = 10.dp, bottom = 10.dp)
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
                    painter = rememberAsyncImagePainter(model = item.productImg),
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
                        text = if (item.price > 0)
                            Constants.formatPrice(item.price)
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
