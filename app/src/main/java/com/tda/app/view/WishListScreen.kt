package com.tda.app.view

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.tda.app.model.response.WishlistResponse
import com.tda.app.ui.theme.bgwhitelight
import com.tda.app.ui.theme.black
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.white
import com.tda.app.utils.Constants
import com.tda.app.viewmodel.UserViewModel
import com.tda.app.viewmodel.WishlistViewModel

@Composable
fun WishListScreen(
    nav: NavController,
    wishlistViewModel: WishlistViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    val user by userViewModel.state.collectAsState()
    val items by wishlistViewModel.items.collectAsState()

    Scaffold(
        topBar = { HeaderWishlistItems(nav) },
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
                        Items(
                            nav,
                            item,
                            user!!.jwt,
                            onClick = { jwt, itemId ->
                                wishlistViewModel.delete(jwt, itemId)
                            }
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
                                text = "Chưa có sản phẩm yêu thích.",
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
    nav: NavController,
    item: WishlistResponse,
    jwt: String?,
    onClick: (String, Long) -> Unit
) {
    jwt?.let {
        var display by remember {
            mutableStateOf(true)
        }
        AnimatedVisibility(visible = display) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(white)
                    .clickable { nav.navigate("product_detail/${item.product.productCode}") }
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
                        painter = rememberAsyncImagePainter(model = item.product.images_file[0]),
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
                                    text = item.product.productName.uppercase(),
                                    fontSize = 16.sp,
                                    color = black,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 2,
                                    modifier = Modifier.padding(bottom = 34.dp)
                                )
                                Text(
                                    text = if (item.product.promotionPrice > 0)
                                        Constants.formatPrice(item.product.promotionPrice) + "đ"
                                    else
                                        Constants.formatPrice(item.product.price) + "đ",
                                    fontSize = 16.sp,
                                    color = colorPrimary,
                                )
                            }
                            Column() {
                                IconButton(onClick = {
                                    display = false
                                    onClick(jwt, item.wishId)
                                }) {
                                    Icon(
                                        imageVector = Icons.Outlined.Close,
                                        contentDescription = "Delete item"
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

@Composable
fun HeaderWishlistItems(nav: NavController) {
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
            text = "Sản phẩm yêu thích",
            modifier = Modifier.padding(end = 130.dp, bottom = 20.dp, top = 20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
        )
    }
}

@Composable
fun HeaderOrderItems(nav: NavController) {
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
            text = "Danh sách đơn hàng",
            modifier = Modifier.padding(end = 130.dp, bottom = 20.dp, top = 20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
        )
    }
}

@Composable
fun HeaderOrderDetails(nav: NavController) {
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
            text = "Chi tiết đơn hàng",
            modifier = Modifier.padding(end = 130.dp, bottom = 20.dp, top = 20.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun previewScraeensas() {
    val nav = rememberNavController()
    CartScreen(nav = nav)
}


