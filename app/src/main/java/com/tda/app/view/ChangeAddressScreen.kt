package com.tda.app.view
import NavigationBottomBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
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
import com.tda.app.ui.theme.GrayText
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.ghost_white
import com.tda.app.ui.theme.white
import com.tda.app.utils.Constants
import com.tda.app.viewmodel.ProductListByCateViewModel
import java.time.format.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeAddressScreen(navController: NavController) {

    //fetch product data

    Scaffold(topBar = { ManagerListAddress(navController) },
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
                item {    ListAddress() }
                item {    ListAddress() }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListAddress() {
    var isSelected by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(white)
            .clickable {
            }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            val radioColors = RadioButtonDefaults.colors(
                selectedColor = colorPrimary, // Màu khi RadioButton được chọn
                unselectedColor = Color.LightGray, // Màu khi RadioButton không được chọn
            )
            RadioButton(
                selected = isSelected,
                onClick = { if(isSelected)
                {
                    isSelected=false
                }

                   else
                {
                    isSelected = true
                }
                          },

                colors = radioColors
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
                           .height(40.dp),
                       textAlign = TextAlign.Left,
                       color =GrayText
                   )
               }
                Text(
                    text = "59/6/1 Đường 48 Hiệp Bình Chánh Thủ Đức, Thành phố hồ chí minh",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .height(50.dp),
                    textAlign = TextAlign.Left,
                    color =GrayText
                )
                if (isSelected == true) {
                Box(Modifier.border(1.dp, color = Red))
                {
                    Text(
                        text = "Mặc định",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(5.dp)
                            .height(25.dp),
                        textAlign = TextAlign.Left,
                        color = Color.Red
                    )
                }

            }
            }
            Text(
                text = "Sửa ",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .padding(top = 5.dp)
                    .height(40.dp),
                textAlign = TextAlign.Left,
                color = colorPrimary
            )
            }
        }
    }

@Preview
@Composable
fun PreviewListProductScreen() {
    val nav = rememberNavController()
    ChangeAddressScreen(nav)
}
