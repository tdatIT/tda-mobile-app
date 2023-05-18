package com.tda.app.view

import NavigationBottomBar
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tda.app.model.response.AddressResponse
import com.tda.app.ui.theme.GrayText
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.ghost_white
import com.tda.app.ui.theme.white
import com.tda.app.viewmodel.AddressViewModel


@Composable
fun ChangeAddressScreen(
    navController: NavController,
    addressViewModel: AddressViewModel = hiltViewModel()
) {
    val addresses by addressViewModel.state.collectAsState()
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
                if (addresses.isNotEmpty())
                    itemsIndexed(addresses) { index, item ->
                        ListAddress(item, index)
                    }
               if(addresses.isEmpty()) {
                   item {
                       Column(
                           modifier = Modifier
                               .fillMaxWidth()
                               .fillMaxHeight(1f),
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center
                       ) {
                           Text(
                               text = "Chưa có địa chỉ.",
                               modifier = Modifier.fillMaxWidth(),
                               textAlign = TextAlign.Center
                           )
                       }
                   }
               }
            }
        }
    }
}


@Composable
fun ListAddress(address: AddressResponse, index: Int) {
    var isSelected by remember { mutableStateOf(false) }
    var isfirst by remember {
        mutableStateOf(true)
    }
    if (isfirst == true && index == 0 ) {
            isSelected = true
        }
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
                    isfirst = false
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
                if (isSelected == true && index != 0) {
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
                if (isfirst == true && index == 0 ) {
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
                        isSelected = true
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

