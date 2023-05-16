package com.tda.app.view

import NavigationBottomBar
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import com.tda.app.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tda.app.component.CheckoutDetail
import com.tda.app.ui.theme.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(navController: NavController) {
    Scaffold(topBar = { HeaderCheckoutItems(navController) },
        bottomBar = { NavigationBottomBar(navController = navController) })
    { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            item {
                ProductItems()
                Spacer(modifier = Modifier.padding(10.dp))
            }
            item {
                ProductItems()
                Spacer(modifier = Modifier.padding(20.dp))
            }
            item {
                AddressCheckOut()
                Spacer(modifier = Modifier.padding(10.dp))
            }
            item {
                CheckoutDetail()
                Spacer(modifier = Modifier.padding(10.dp))
            }

        }
    }
}




@Composable
fun ProductItems() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clip(RoundedCornerShape(16.dp))
        .background(white)) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)) {
            Box(
                modifier = Modifier
                    .weight(0.3f)
                    .height(70.dp)
                    .clip(RoundedCornerShape(12.dp)),
            ) {
                Image(
                    modifier = Modifier
                        .size(70.dp),
                    painter = painterResource(R.drawable.ic_pink_rose_bouquet),
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
                    text = "Laptop",
                    fontSize = 16.sp,
                    color = black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "999.999",
                    fontSize = 16.sp,
                    color = colorPrimary,
                )
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                   ) {
                    Text(
                        text = "Quantity:",
                        color = Color.Gray,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 10.dp).padding(end = 10.dp)
                    )
                    Text(
                        text = "6",
                        color = Color.Gray,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )

                }
            }

        }
    }
}
@Composable
fun AddressCheckOut()
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(16.dp))
            .background(white)
            .clickable {
            }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = colorPrimary,
                modifier = Modifier.padding(end = 10.dp)
            )
            Column(
                Modifier
                    .fillMaxHeight()
                    .width(300.dp)
            ) {
                Row(  Modifier
                    .fillMaxWidth()
                    .height(25.dp)
                ) {
                    Text(
                        text = "Lê Trương Ngọc Hải | ",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .height(40.dp),
                        textAlign = TextAlign.Left
                    )
                    Text(
                        text = "0814069391 ",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .padding(end = 30.dp)
                            .height(40.dp),
                        textAlign = TextAlign.Left,
                        color = GrayText
                    )
                    Image(
                        painter = painterResource(id = R.drawable.arrow_forward),
                        contentDescription = "forward",
                    )
                }
             Text(
                    text = "59/6/1 Đường 48 Hiệp Bình Chánh Thủ Đức, Thành phố hồ chí minh",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .height(40.dp),
                    textAlign = TextAlign.Left,
                    color = GrayText
                )
                }
        }
    }
}

@Preview
@Composable
fun PreviewListProductScreens() {
    val nav = rememberNavController()
    ProductItems()
}