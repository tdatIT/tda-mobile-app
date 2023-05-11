package com.tda.app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tda.app.R
import com.tda.app.ui.theme.TdaMobilemobileTheme
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.white

@Composable
fun ImageHeader() {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .offset(0.dp, (-30).dp),
        painter = painterResource(id = R.drawable.bg_main),
        contentDescription = "Header Background",
        contentScale = ContentScale.FillWidth
    )
}


@Composable
fun AppBarPrimary(navController: NavController) {
    Row(
        Modifier
            .padding(16.dp)
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        TextField(
            value = "",
            onValueChange = {},
            label = { Text(text = "Tìm kiếm sản phẩm", fontSize = 12.sp) },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search"
                )
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
                .clickable { navController.navigate("search_screen") },
            enabled = false
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "",
                tint = Color.White
            )
        }
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Outlined.ShoppingCart,
                contentDescription = "",
                tint = Color.White
            )
        }
    }
}

@Composable
fun SecondAppBar(navController: NavController) {
    Box(Modifier.background(colorPrimary)) {
        Row(
            Modifier
                .padding(16.dp)
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,

            ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = "",
                onValueChange = {},
                label = { Text(text = "Tìm kiếm sản phẩm", fontSize = 12.sp) },
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Search,
                        contentDescription = "Search"
                    )
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
                    .clickable { navController.navigate("search_screen") },
                enabled = false
            )
            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun SearchAppBar(navController: NavController) {
    var searchStr by remember { mutableStateOf("") }
    Box(Modifier.background(white)) {
        Row(
            Modifier
                .padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 4.dp)
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,

            ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "",
                    tint = colorPrimary
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            TextField(
                value = searchStr,
                onValueChange = { searchStr = it },
                placeholder = {
                    Text(
                        text = "Tìm kiếm sản phẩm",
                        fontSize = 14.sp,
                        color = colorPrimary
                    )
                },
                singleLine = true,
                trailingIcon = {
                    Button(
                        onClick = {},
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
}

@Composable
fun ProductInCategoryBar(navController: NavController, name: String) {
    Box(Modifier.background(colorPrimary)) {
        Row(
            Modifier
                .padding(16.dp)
                .height(30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,

            ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Danh mục ${name.uppercase()}",
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically),
                color = Color.White,
            )
            Spacer(modifier = Modifier.width(8.dp))

            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewAppBar() {
    val nav = rememberNavController()
    TdaMobilemobileTheme {
        SearchAppBar(navController = nav)
    }
}