package com.tda.app.view

import NavigationBottomBar
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tda.app.model.response.AddressResponse
import com.tda.app.ui.theme.GrayText
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.ghost_white
import com.tda.app.ui.theme.white
import com.tda.app.viewmodel.AddressViewModel


@Composable
fun ChangeAddressScreen(navController: NavController, token: String) {
    val addressViewModel = viewModel(AddressViewModel::class.java)
    val addresses by addressViewModel.state.collectAsState()
    addressViewModel.fetchAll(token)

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
                itemsIndexed(addresses) { index, item ->
                    ListAddress(item, index)
                }
            }
        }
    }
}


@Composable
fun ListAddress(address: AddressResponse, index: Int) {
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
                onClick = {
                    isSelected = !isSelected
                },
                colors = radioColors
            )
            Column(
                Modifier
                    .fillMaxHeight()
                    .width(300.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(25.dp)
                ) {
                    Text(
                        text = "Địa chỉ: ${index + 1} | ",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .height(40.dp),
                        textAlign = TextAlign.Left
                    )
                    Text(
                        text = address.phone,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .height(40.dp),
                        textAlign = TextAlign.Left,
                        color = GrayText
                    )
                }
                Text(
                    text = address.addressDetail,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                        .height(50.dp),
                    textAlign = TextAlign.Left,
                    color = GrayText
                )
                if (isSelected) {
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
    ChangeAddressScreen(nav, "sdasd")
}
