package com.tda.app.view.home_screen_component

import android.widget.GridView
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.graphics.toColor
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.tda.app.R
import com.tda.app.model.response.ProductResponse
import com.tda.app.ui.theme.*
import com.tda.app.viewmodel.PopularViewModel
import kotlinx.coroutines.delay

@Composable
fun DashboardScreen(
    navController: NavController,
) {
    val popularViewModel = viewModel(modelClass = PopularViewModel::class.java)
    val products by popularViewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp)
    ) {
        SlidingBannerView()
        Spacer(modifier = Modifier.height(15.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.flash_icon),
                    contentDescription = "Flash Deal",
                    modifier = Modifier
                        .background(
                            MaterialTheme.colors.colorprimarywhite,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .size(50.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            navController.navigate("category_screen")
                        }
                        .padding(10.dp)
                )
                Text(text = "Flash\nDeal", fontSize = 14.sp, textAlign = TextAlign.Center)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bill_icon),
                    contentDescription = "Bill",
                    modifier = Modifier
                        .background(
                            MaterialTheme.colors.colorprimarywhite,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .size(50.dp)

                        .clip(RoundedCornerShape(10.dp))
                        .clickable {

                        }
                        .padding(10.dp)
                )
                Text(text = "Bill", fontSize = 14.sp, textAlign = TextAlign.Center)
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.game_icon),
                    contentDescription = "Game",
                    modifier = Modifier
                        .background(
                            MaterialTheme.colors.colorprimarywhite,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .size(50.dp)

                        .clip(RoundedCornerShape(10.dp))
                        .clickable {

                        }
                        .padding(10.dp)
                )
                Text(text = "Game", fontSize = 14.sp, textAlign = TextAlign.Center)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.gift_icon),
                    contentDescription = "Daily Gift",
                    modifier = Modifier
                        .background(
                            MaterialTheme.colors.colorprimarywhite,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .size(50.dp)

                        .clip(RoundedCornerShape(10.dp))
                        .clickable {

                        }
                        .padding(10.dp)
                )
                Text(text = "Daily\nGift", fontSize = 14.sp, textAlign = TextAlign.Center)
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.discover),
                    contentDescription = "More",
                    modifier = Modifier
                        .background(
                            MaterialTheme.colors.colorprimarywhite,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .size(50.dp)

                        .clip(RoundedCornerShape(10.dp))
                        .clickable {

                        }
                        .padding(10.dp)
                )
                Text(text = "More", fontSize = 14.sp, textAlign = TextAlign.Center)
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Doanh mục sản phẩm", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "Xem thêm", color = MaterialTheme.colors.TextColor)
        }

        Spacer(modifier = Modifier.height(15.dp))
        LazyBanner()
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Sản phẩm nổi bật", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "Xêm thêm", color = MaterialTheme.colors.TextColor)
        }
        Spacer(modifier = Modifier.height(8.dp))
        PopularItems(products, navController);

    }
}

@Composable
fun LazyBanner() {
    //special offer cart
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ) {
        item {
            ConstraintLayout(
                modifier = Modifier
                    .width(280.dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                //constrains
                val (bannerText, bannerImage) = createRefs()
                Image(
                    painter = painterResource(id = R.drawable.image_banner_3),
                    contentDescription = "",
                    modifier = Modifier.constrainAs(bannerImage) {}
                )
                Column(
                    modifier = Modifier
                        .background(Color(0x8DB3B0B0))
                        .padding(15.dp)
                        .constrainAs(bannerText) {
                            top.linkTo(bannerImage.top)
                            bottom.linkTo(bannerImage.bottom)
                            start.linkTo(bannerImage.start)
                            end.linkTo(bannerImage.end)
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        }
                ) {
                    Text(
                        text = "Fashion",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.heightIn(15.dp))
                    Text(text = "85 Brands", color = Color.White)
                }
            }
        }
        item {
            //second item
            ConstraintLayout(
                modifier = Modifier
                    .width(280.dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                //constrains
                val (bannerText2, bannerImage2) = createRefs()
                Image(
                    painter = painterResource(id = R.drawable.image_banner_2),
                    contentDescription = "",
                    modifier = Modifier.constrainAs(bannerImage2) {}
                )
                Column(
                    modifier = Modifier
                        .background(Color(0x8DB3B0B0))
                        .padding(15.dp)
                        .constrainAs(bannerText2) {
                            top.linkTo(bannerImage2.top)
                            bottom.linkTo(bannerImage2.bottom)
                            start.linkTo(bannerImage2.start)
                            end.linkTo(bannerImage2.end)
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        }
                ) {
                    Text(
                        text = "Mobile Phone & Gadget",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.heightIn(15.dp))
                    Text(text = "15 Brands", color = Color.White)
                }
            }
        }

    }
}

@Composable
fun PopularItems(
    products: List<ProductResponse>,
    navController: NavController,
) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(8.dp),
        columns = GridCells.Fixed(2)
    ) {
        itemsIndexed(products) { index, product ->
            ProductCard(product = product, navController)
        }
    }
}


@Composable
fun ProductCard(product: ProductResponse, navController: NavController) {
    Card(Modifier.clickable { }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(100.dp),
                    painter = rememberAsyncImagePainter(product.images_file[0]),
                    contentDescription = "",
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                ) {
                    Text(
                        text = product.productName,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = black,
                    )
                    Text(
                        text = product.price.toString(),
                        fontSize = 12.sp,
                        color = colorPrimary,
                    )
                }
                //Wistlist
                Button(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .padding(4.dp)
                        .shadow(elevation = 8.dp),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Favorite,
                        contentDescription = "",
                        tint = favourite,
                        modifier = Modifier
                            .size(32.dp, 32.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(8.dp))
                //Shopping Cart
                Button(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(colorPrimary)
                        .padding(4.dp)
                        .shadow(elevation = 8.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ShoppingCart,
                        contentDescription = "",
                        tint = white,
                        modifier = Modifier
                            .size(32.dp, 32.dp)
                    )
                }

            }

        }
    }
}

@Composable
fun SlidingBannerView() {
    val bannerList = listOf(
        com.tda.app.R.drawable.ic_sale_banner,
        com.tda.app.R.drawable.tda_logo,
        com.tda.app.R.drawable.ic_giftbox,
        com.tda.app.R.drawable.ic_home
    )

    var currentPosition by remember { mutableStateOf(0) }
    val pageCount = bannerList.size

    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = bannerList[currentPosition]),
            contentDescription = "Banner Image",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            DotsIndicator(
                dotsCount = pageCount,
                currentIndex = currentPosition,
                modifier = Modifier.padding(8.dp)
            )
        }
        LaunchedEffect(currentPosition) {
            delay(3000)
            currentPosition = (currentPosition + 1) % pageCount
        }
    }
}

@Composable
fun DotsIndicator(
    dotsCount: Int,
    currentIndex: Int,
    selectedColor: Color = Color.DarkGray,
    unselectedColor: Color = Color.Gray,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.padding(start = 120.dp)) {
        for (i in 0 until dotsCount) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(
                        color = if (i == currentIndex) selectedColor else unselectedColor
                    )
            )
        }
    }
}
