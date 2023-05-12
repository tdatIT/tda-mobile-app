package com.tda.app.view

import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.outlined.Inventory
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter

import com.tda.app.model.response.ProductResponse
import com.tda.app.ui.theme.bgwhitelight
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.colorSecond
import com.tda.app.ui.theme.light_gray
import com.tda.app.utils.Constants
import com.tda.app.viewmodel.BestSellerViewModel
import com.tda.app.viewmodel.ProductByCodeViewModel


@Composable
fun ProductDetailScreen(nav: NavController, productCode: String) {

    val productViewModel = viewModel(modelClass = ProductByCodeViewModel::class.java)
    val product by productViewModel.state.collectAsState()
    productViewModel.fecthData(productCode)

    val bestSellerViewModel = viewModel(modelClass = BestSellerViewModel::class.java)
    val bestSellers by bestSellerViewModel.state.collectAsState()

    var currentImagePosition by remember { mutableStateOf(0) }
    Scaffold(
        topBar = { ProductDetailBar(nav) },
        bottomBar = { BottomProductBar(nav) },
        backgroundColor = bgwhitelight,
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            item(span = { GridItemSpan(2) }) {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                ) {
                    product?.let {
                        Image(
                            painter = rememberAsyncImagePainter(model = it.images_file[currentImagePosition]),
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp)
                        )
                        LazyRow() {

                            itemsIndexed(it.images_file) { index, img ->
                                Image(
                                    modifier = Modifier
                                        .background(Color.White)
                                        .size(80.dp)
                                        .clickable { currentImagePosition = index }
                                        .padding(10.dp),
                                    painter = rememberAsyncImagePainter(model = img),
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                }
            }
            item(span = { GridItemSpan(2) }) { ProductInfo(product, nav) }
            item(span = { GridItemSpan(2) }) {
                ShippingInfo()
            }
            item(span = { GridItemSpan(2) }) {
                TopProduct(bestSellers, nav)
            }
            item(span = { GridItemSpan(2) }) {
                Column(
                    Modifier
                        .background(Color.White)
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Cấu hình chi tiết")
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Xem thêm")
                            Icon(imageVector = Icons.Outlined.ChevronRight, contentDescription = "")

                        }
                    }
                    product?.let { HtmlEncoder(it.description) }
                }
            }
        }
    }


}

@Composable
fun ShippingInfo() {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            Modifier
                .padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.LocalShipping,
                contentDescription = "",
            )
            Text(
                text = "Phí vận chuyển: 18.500đ",
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        Icon(
            modifier = Modifier.padding(10.dp),
            imageVector = Icons.Outlined.ExpandMore,
            contentDescription = "",
        )
    }
}

@Composable
fun ProductInfo(product: ProductResponse?, nav: NavController) {
    product?.let {
        Column(
            Modifier
                .background(Color.White)
                .padding(10.dp)
                .clickable {
                    nav.navigate("product_detail/${product.productCode}")
                }
        ) {
            Text(
                text = product.productName,
                fontSize = 16.sp,
                maxLines = 3
            )
            Text(
                text = "CODE: ${product.productCode.uppercase()}",
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                color = colorSecond
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(
                        text = if (product.promotionPrice > 0)
                            Constants.formatPrice(product.promotionPrice) + "đ"
                        else
                            Constants.formatPrice(product.price) + "đ",
                        color = colorPrimary,
                        fontSize = 18.sp
                    )
                    Text(
                        text = Constants.formatPrice(product.price) + "đ",
                        color = light_gray,
                        fontSize = 12.sp,
                        textDecoration = TextDecoration.LineThrough
                    )
                }
                Row() {
                    Icon(
                        imageVector = Icons.Outlined.StarBorder,
                        contentDescription = "",
                        tint = Color.Yellow
                    )
                    Icon(
                        imageVector = Icons.Outlined.StarBorder,
                        contentDescription = "",
                        tint = Color.Yellow
                    )
                    Icon(
                        imageVector = Icons.Outlined.StarBorder,
                        contentDescription = "",
                        tint = Color.Yellow
                    )
                    Icon(
                        imageVector = Icons.Outlined.StarBorder,
                        contentDescription = "",
                        tint = Color.Yellow
                    )
                    Icon(
                        imageVector = Icons.Outlined.StarBorder,
                        contentDescription = "",
                        tint = Color.Yellow
                    )
                }
            }
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Inventory,
                    contentDescription = "",
                    tint = colorPrimary
                )
                Text(text = "Giao miễn phí", fontSize = 12.sp)
                Icon(
                    imageVector = Icons.Outlined.WorkspacePremium,
                    contentDescription = "",
                    tint = colorPrimary
                )
                Text(text = "Chính hãng", fontSize = 12.sp)
                Icon(
                    imageVector = Icons.Outlined.SupportAgent,
                    contentDescription = "",
                    tint = colorPrimary
                )
                Text(text = "Bảo hành", fontSize = 12.sp)

            }


        }
    }
}


@Composable
fun TopProduct(products: List<ProductResponse>, nav: NavController) {
    Column(
        Modifier
            .background(Color.White)
            .padding(start = 10.dp, end = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Sản phẩm nổi bật")
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Xem thêm")
                Icon(imageVector = Icons.Outlined.ChevronRight, contentDescription = "")

            }
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            itemsIndexed(products) { index, p ->
                ProductItem(p, nav)
            }
        }
    }

}

@Composable
fun ProductItem(i: ProductResponse, nav: NavController) {
    Box(
        modifier = Modifier
            .border(1.dp, light_gray, RectangleShape)
            .padding(10.dp)
            .width(80.dp)
            .clickable {
                nav.navigate("product_detail/${i.productCode}")
            }
    ) {
        Column(
        ) {
            Image(
                painter = rememberAsyncImagePainter(i.images_file[0]),
                contentDescription = "product", Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.padding(3.dp))
            Text(
                text = i.productName,
                maxLines = 2,
                fontSize = 10.sp
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = if (i.promotionPrice > 0)
                    Constants.formatPrice(i.promotionPrice) + "đ"
                else
                    Constants.formatPrice(i.price) + "đ",
                maxLines = 2,
                fontSize = 12.sp,
                color = colorPrimary
            )
        }

    }
}

@Composable
fun HtmlEncoder(text: String) {
    AndroidView(factory = { context ->
        TextView(context).apply {
            setText(HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY))
        }
    }, modifier = Modifier.padding(start = 10.dp, end = 10.dp))
}

@Preview
@Composable
fun Preview() {
    val nav = rememberNavController()
    ProductDetailScreen(nav = nav, productCode = "e096i4")
}
