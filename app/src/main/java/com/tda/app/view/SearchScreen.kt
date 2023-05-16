package com.tda.app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.tda.app.R
import com.tda.app.model.response.ProductResponse
import com.tda.app.ui.theme.bgwhitelight
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.gray
import com.tda.app.ui.theme.light_gray
import com.tda.app.ui.theme.text_hint_color
import com.tda.app.ui.theme.white
import com.tda.app.utils.Constants
import com.tda.app.viewmodel.KeywordViewModel
import com.tda.app.viewmodel.ProductListByCateViewModel
import com.tda.app.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    nav: NavController,
    keywordViewModel: KeywordViewModel = hiltViewModel()
) {

    val searchViewModel = viewModel(modelClass = SearchViewModel::class.java)
    val products by searchViewModel.state.collectAsState()
    val local_keyword by keywordViewModel.keywords.collectAsState()
    var keyword by remember { mutableStateOf("") }

    Scaffold(
        Modifier
            .fillMaxWidth()
            .background(bgwhitelight),
        topBar = {
            Box(Modifier.background(white)) {
                Row(
                    Modifier
                        .padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 4.dp)
                        .height(48.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,

                    ) {
                    IconButton(onClick = { nav.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = "",
                            tint = colorPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    TextField(
                        value = keyword,
                        onValueChange = {
                            keyword = it
                            searchViewModel.getProductByKeyword(keyword)
                        },
                        placeholder = {
                            androidx.compose.material.Text(
                                text = "Tìm kiếm sản phẩm",
                                fontSize = 14.sp,
                                color = colorPrimary
                            )
                        },
                        singleLine = true,
                        trailingIcon = {
                            Button(
                                onClick = {
                                    keywordViewModel.insert(keyword)
                                    nav.navigate("search_result/${keyword}")
                                },
                                shape = RoundedCornerShape(10),
                                colors = ButtonDefaults.buttonColors(colorPrimary)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Search,
                                    contentDescription = "",
                                    tint = white
                                )
                            }
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    )

                }
            }
        }, backgroundColor = bgwhitelight
    ) { padding ->
        LazyColumn(
            Modifier.padding(padding)
        ) {
            item {
                Text(
                    text = "Tìm kiếm gần đây",
                    Modifier
                        .padding(start = 12.dp, top = 5.dp, bottom = 5.dp),
                    fontStyle = FontStyle.Italic
                )
            }
            if (local_keyword.isNotEmpty()) {
                itemsIndexed(local_keyword) { index, key ->
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .background(white)
                    ) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = key.keyword,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(14.dp)
                            )
                            IconButton(onClick = {
                                keywordViewModel.delete(key.id)
                                keywordViewModel.getAll()
                            }) {
                                Icon(imageVector = Icons.Outlined.Close, contentDescription = "")
                            }
                        }
                        Divider(startIndent = 0.dp, thickness = 1.dp, color = text_hint_color)
                    }

                }
            }

            item {
                Text(
                    text = "Sản phẩm",
                    Modifier
                        .padding(start = 12.dp, top = 10.dp, bottom = 10.dp),
                    fontStyle = FontStyle.Italic
                )
            }
            itemsIndexed(products) { index, p ->
                ProductRow(product = p, nav = nav)
            }
        }
    }
}

@Composable
fun ProductRow(product: ProductResponse, nav: NavController) {
    Spacer(modifier = Modifier.padding(5.dp))
    Box(modifier = Modifier
        .wrapContentHeight()
        .background(white)
        .padding(10.dp)
        .clickable {

            nav.navigate("product_detail/${product.productCode}")
        }) {
        Row() {
            Image(
                painter = rememberAsyncImagePainter(model = product.images_file[0]),
                contentDescription = "",
                modifier = Modifier
                    .size(80.dp)
                    .padding(10.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = product.productName,
                    fontSize = 18.sp,
                    maxLines = 2,
                    modifier = Modifier.height(42.dp)
                )
                Text(
                    text = if (product.promotionPrice > 0)
                        Constants.formatPrice(product.promotionPrice) + "đ"
                    else
                        Constants.formatPrice(product.price) + "đ",
                    color = colorPrimary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 5.dp),
                    textAlign = TextAlign.Right,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewSearchScreen() {
    val nav = rememberNavController()
    SearchScreen(nav = nav)
}