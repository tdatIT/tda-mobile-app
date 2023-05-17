package com.tda.app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Details
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.More
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tda.app.R
import com.tda.app.navigation.Screen
import com.tda.app.ui.theme.TdaMobilemobileTheme
import com.tda.app.ui.theme.bgwhitelight
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.gray
import com.tda.app.ui.theme.text_hint_color
import com.tda.app.ui.theme.white
import kotlinx.coroutines.launch

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
        IconButton(onClick = { navController.navigate(Screen.CartScreen.route) }) {
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

            IconButton(onClick = { navController.navigate(Screen.CartScreen.route) }) {
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
fun SearchAppBar(
    navController: NavController
) {
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
                onValueChange = {
                    searchStr = it

                },
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

            IconButton(onClick = { navController.navigate(Screen.CartScreen.route) }) {
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
fun ProductDetailBar(navController: NavController) {
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
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "",
                        tint = gray
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = bgwhitelight,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable { navController.navigate(Screen.SearchScreen.route) }
            )

            IconButton(
                onClick = { navController.navigate(Screen.CartScreen.route) },
            ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = "",
                    tint = colorPrimary
                )
            }
            IconButton(
                onClick = {},
            ) {
                Icon(
                    imageVector = Icons.Outlined.MoreHoriz,
                    contentDescription = "",
                    tint = colorPrimary
                )
            }
        }
    }
}

@Composable
fun BottomProductBar(navController: NavController) {
    Box(Modifier.background(white)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            ) {

                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "",
                    tint = colorPrimary
                )

                Text(text = "Yêu thích", fontSize = 12.sp)
            }
            VerticalDivider()
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(end = 5.dp, start = 5.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.AddShoppingCart,
                    contentDescription = "",
                    tint = colorPrimary
                )

                Text(text = "Thêm vào giỏ hàng", fontSize = 12.sp)
            }
            Column(
                modifier = Modifier.background(colorPrimary),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(colorPrimary),
                    onClick = { /*TODO*/ }) {
                    Text(text = "Mua ngay", color = white)
                }
            }
        }
    }
}
@Composable
fun HeaderAccount(
    navController: NavController,
    name: String,
    email: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(MaterialTheme.colors.surface)
            .padding(24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "profile picture"
            )
            Column(
                modifier = Modifier.padding(start = 20.dp)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = email,
                    style = MaterialTheme.typography.subtitle1
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                modifier = Modifier.size(36.dp),
                onClick = {
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "edit",
                    tint = colorPrimary,
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { }
                )
            }
        }
    }
}

@Composable
fun HeaderAccount(
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(MaterialTheme.colors.surface)
            .padding(24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "profile picture"
            )
            Column(
                modifier = Modifier.padding(start = 20.dp)
            ) {
                Text(
                    text = "Tài khoản khách",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Vui lòng đăng nhập / tạo tài khoản",
                    style = MaterialTheme.typography.subtitle1
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                modifier = Modifier.size(36.dp),
                onClick = {
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "edit",
                    tint = colorPrimary,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}
@Composable
fun HeaderCheckoutItems(navController: NavController) {
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
                text = "Check out",
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically),
                color = Color.White,
            )
        }
    }
}
@Composable
fun ManagerListAddress(navController: NavController) {
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
                text = "Thay đổi địa chỉ",
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
                    painterResource(id =  R.drawable.address),
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
        ManagerListAddress(nav)
    }
}