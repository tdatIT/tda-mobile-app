package com.tda.app.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.tda.app.R
import com.tda.app.model.response.CategoryResp
import com.tda.app.model.response.ProductResponse
import com.tda.app.navigation.Screen
import com.tda.app.ui.theme.TdaMobilemobileTheme
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.favourite
import com.tda.app.utils.Constants
import com.tda.app.viewmodel.AllProductViewModel
import com.tda.app.viewmodel.CategoryViewModel
import com.tda.app.viewmodel.PopularProductViewModel


@Preview(showBackground = true)
@Composable
fun DefaultPreviewHome() {
    val nav = rememberNavController()
    TdaMobilemobileTheme {
        HomeScreen(nav)
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    //product viewModel
    val allProductViewModel = viewModel(modelClass = AllProductViewModel::class.java)
    val products by allProductViewModel.state.collectAsState()
    //category viewModel
    val categoryViewModel = viewModel(modelClass = CategoryViewModel::class.java)
    val categories by categoryViewModel.state.collectAsState()
    //popular Product
    val popularProductViewModel = viewModel(modelClass = PopularProductViewModel::class.java)
    val populars by popularProductViewModel.state.collectAsState()

    Box() {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .offset(0.dp, (-30).dp),
            painter = painterResource(id = R.drawable.bg_main),
            contentDescription = "Header Background",
            contentScale = ContentScale.FillWidth
        )
        Content(navController, products, populars, categories)
    }
}

@Composable
fun Content(
    navController: NavController,
    products: List<ProductResponse>,
    populars: List<ProductResponse>,
    categories: List<CategoryResp>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            AppBarPrimary(navController)
        }
        item(span = { GridItemSpan(2) }) {
            HeaderBar(navController)
        }
        item(span = { GridItemSpan(2) }) {
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .padding(10.dp)
            )
            Promotions()
        }
        item(span = { GridItemSpan(2) }) {
            Spacer(modifier = Modifier.height(16.dp))
            CategorySection(categories, navController)
        }
        item(span = { GridItemSpan(2) }) {
            TopProduct(populars, navController)
        }
        item(span = { GridItemSpan(2) }) {
            Spacer(modifier = Modifier.height(16.dp))
            BestSellerSection()
        }
        itemsIndexed(products) { index, product ->
            ProductCard(
                navController,
                product
            )
        }
    }
}


@Composable
fun HeaderBar(navController: NavController) {
    Card(
        Modifier
            .height(64.dp)
            .padding(horizontal = 16.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            QrButton()
            VerticalDivider()
            Row(Modifier
                .fillMaxHeight()
                .weight(1f)
                .clickable { navController.navigate(Screen.AccountScreen.route) }
                .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = "",
                    tint = Color(0xFF6FCF97)
                )
                Column(Modifier.padding(8.dp)) {
                    Text(text = "Truy cập", fontSize = 12.sp)
                    Text(
                        text = "Tài khoản",
                        fontWeight = FontWeight.SemiBold,
                        color = colorPrimary,
                        fontSize = 12.sp,
                    )
                }
            }

            VerticalDivider()
            Row(Modifier
                .fillMaxHeight()
                .weight(1f)
                .clickable { navController.navigate(Screen.WishListScreen.route) }
                .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.FavoriteBorder,
                    contentDescription = "",
                    tint = favourite
                )
                Column(Modifier.padding(8.dp)) {
                    Text(text = "Danh sách", fontSize = 12.sp)
                    Text(
                        text = "Wistlist",
                        fontWeight = FontWeight.SemiBold,
                        color = favourite,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
fun QrButton() {
    IconButton(
        onClick = {},
        modifier = Modifier
            .fillMaxHeight()
            .aspectRatio(1f)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_scan),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        )
    }
}

@Composable
fun VerticalDivider() {
    Divider(
        color = Color(0xFFF1F1F1),
        modifier = Modifier
            .width(1.dp)
            .height(32.dp)
    )
}

@Composable
fun Promotions() {
    LazyRow(
        Modifier
            .height(160.dp)
            .padding(top = 20.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            PromotionItem(
                imagePainter = rememberAsyncImagePainter("https://theme.hstatic.net/200000420363/1001015796/14/banner_right_3.jpg"),
            )
        }
        item {
            PromotionItem(
                imagePainter = rememberAsyncImagePainter("https://theme.hstatic.net/200000420363/1001015796/14/banner_right_4.jpg"),
            )
        }
    }
}

@Composable
fun PromotionItem(
    backgroundColor: Color = Color.Transparent,
    imagePainter: Painter
) {
    Card(
        Modifier.width(300.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = backgroundColor,
        elevation = 0.dp
    ) {
        Row {
            Image(
                painter = imagePainter, contentDescription = "",
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                alignment = Alignment.CenterEnd,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun CategorySection(categories: List<CategoryResp>, navController: NavController) {
    Column(Modifier.padding(horizontal = 16.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Danh mục sản phẩm", style = MaterialTheme.typography.h6)
            TextButton(onClick = {
                navController.navigate("category_screen")
            }) {
                Text(text = "Xem thêm", color = colorPrimary)
            }
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(categories) { index, cate ->
                CategoryButton(cate, navController)
            }
        }
    }
}

@Composable
fun CategoryButton(
    c: CategoryResp,
    navController: NavController
) {
    Column(
        Modifier
            .width(72.dp)
            .clickable { navController.navigate("product_categoryCode/${c.code}") }
    ) {
        Box(
            Modifier
                .size(72.dp)
                .background(
                    color = Color(0xffEAFAF1),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(18.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(c.images),
                contentDescription = "",
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = c.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(25.dp),
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            maxLines = 2
        )
    }
}

@Composable
fun BestSellerSection() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Sản phẩm gợi ý", style = MaterialTheme.typography.h6)
        TextButton(onClick = {}) {
            Text(text = "Xem thêm", color = colorPrimary)
        }
    }

}

@Composable
fun PopularProduct() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Phổ biến", style = MaterialTheme.typography.h6)
        TextButton(onClick = {}) {
            Text(text = "Xem thêm", color = colorPrimary)
        }
    }

}

@Composable
fun ProductCard(
    navController: NavController,
    product: ProductResponse
) {
    Card(
        Modifier
            .width(160.dp)
            .clickable { navController.navigate("product_detail/${product.productCode}") }
    ) {
        Column(
            Modifier
                .padding(bottom = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(160.dp),
                painter = rememberAsyncImagePainter(product.images_file[0]),
                contentDescription = "",
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Text(text = product.productName, fontWeight = FontWeight.Bold, maxLines = 2)
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "đ${
                            if (product.promotionPrice > 0)
                                Constants.formatPrice(product.promotionPrice)
                            else
                                Constants.formatPrice(product.price)
                        }",
                        color = colorPrimary
                    )
                    Text(text = "SL: ${product.quantity}", color = Color.Red)
                }
            }
        }
    }
}

