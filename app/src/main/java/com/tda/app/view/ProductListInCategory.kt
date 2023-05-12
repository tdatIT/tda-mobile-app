package com.tda.app.view

import NavigationBottomBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.tda.app.R
import com.tda.app.model.response.ProductResponse
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.ghost_white
import com.tda.app.ui.theme.white
import com.tda.app.utils.Constants
import com.tda.app.viewmodel.ProductListByCateViewModel
import java.time.format.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductInCategoryScreen(navController: NavController, code: String) {
    val productListByCateViewModel = viewModel(modelClass = ProductListByCateViewModel::class.java)
    val products by productListByCateViewModel.state.collectAsState()
    //fetch product data
    productListByCateViewModel.getProducts(code)
    Scaffold(topBar = { ProductInCategoryBar(navController = navController, name = code) },
        bottomBar = { NavigationBottomBar(navController = navController) }) { padding ->
        Column(
            Modifier
                .background(ghost_white)
                .padding(padding)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.padding(top = 8.dp)
            ) {
                itemsIndexed(products) { index, p ->
                    ProductRowCard(p, navController)
                }
            }
        }
    }

}

@Composable
fun ProductRowCard(product: ProductResponse, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(white)
            .clickable {
                navController.navigate("product_detail/${product.productCode}")
            }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = product.images_file[0]),
                contentDescription = "productImage",
                Modifier
                    .size(140.dp)
                    .padding(5.dp)
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Text(
                    text = product.productName,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .height(40.dp),
                    textAlign = TextAlign.Left
                )
                Row(Modifier.padding(bottom = 5.dp)) {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "",
                        tint = Color.Yellow
                    )
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "",
                        tint = Color.Yellow
                    )
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "",
                        tint = Color.Yellow
                    )
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "",
                        tint = Color.Yellow
                    )
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "",
                        tint = Color.Yellow
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column() {
                        Text(
                            text = Constants.formatPrice(product.price),
                            textDecoration = TextDecoration.LineThrough
                        )
                        Text(
                            text = if (product.promotionPrice > 0)
                                Constants.formatPrice(product.promotionPrice) + "đ"
                            else
                                Constants.formatPrice(product.price) + "đ",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = colorPrimary
                        )
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(colorPrimary)
                    ) {
                        Text(text = "Mua ngay")
                    }
                }

            }
        }
    }

}

@Preview
@Composable
fun PreviewScreen() {
    val nav = rememberNavController()
    ProductInCategoryScreen(navController = nav, code = "macbook")
}
