package com.tda.app.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text

import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack

import androidx.compose.material.icons.outlined.Search


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tda.app.ui.theme.bgwhitelight
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.light_gray
import com.tda.app.ui.theme.text_hint_color
import com.tda.app.ui.theme.white
import com.tda.app.viewmodel.SearchViewModel

@Composable
fun SearchResultScreen(nav: NavController, reqKw: String) {
    val searchViewModel = viewModel(modelClass = SearchViewModel::class.java)
    val products by searchViewModel.state.collectAsState()
    searchViewModel.getProductByKeyword(reqKw)
    var keyword by remember { mutableStateOf(reqKw) }
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
                            "Tìm kiếm sản phẩm"
                        },
                        singleLine = true,
                        trailingIcon = {
                            Button(
                                onClick = { nav.navigate("search_result/${keyword}") },
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(padding),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(10.dp)
        ) {
            itemsIndexed(products) { index, product ->
                ProductCard(
                    nav,
                    product
                )
            }
            item {
                Text(
                    text = "Có tất cả ${products.size} sản phẩm",
                    color = light_gray
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewResult() {
    val nav = rememberNavController()
    SearchResultScreen(nav, "dfghj")

}