package com.tda.app.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tda.app.navigation.Screen
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.dark_gray
import com.tda.app.ui.theme.white
import com.tda.app.viewmodel.DistrictViewModel
import com.tda.app.viewmodel.ProvinceViewModel
import com.tda.app.viewmodel.UserViewModel
import com.tda.app.viewmodel.WardViewModel

@Composable
fun NewAddressScreen(
    navController: NavController,
    provinceViewModel: ProvinceViewModel = hiltViewModel(),
    districtViewModel: DistrictViewModel = hiltViewModel(),
    wardViewModel: WardViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    val provinces by provinceViewModel.provinces.collectAsState()
    val districts by districtViewModel.district.collectAsState()
    val wards by wardViewModel.wards.collectAsState()
    val user by userViewModel.state.collectAsState()
    var isOpened by remember {
        mutableStateOf(false)
    }
    var displayed by remember {
        mutableStateOf(false)
    }
    var provinceCode by remember {
        mutableStateOf(0)
    }
    var districtCode by remember {
        mutableStateOf(0)
    }
    var wardsCode by remember {
        mutableStateOf(0)
    }
    if (isOpened) {
        MessageDialog(
            title = "Đăng ký thất bại",
            msg = "Vui lòng kiểm tra dữ liệu đầu vào",
            onChange = {
                isOpened = false

            }
        )
    }

    if (displayed) {
        ProgressIndicator()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(16.dp))
                    .background(white)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Thông tin",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(10.dp))

                    Text(
                        text = "Địa chỉ",
                        style = MaterialTheme.typography.subtitle1,
                        color = dark_gray,
                    )

                    DropDownMenuProvince(
                        provinces,
                        "Tỉnh thành",
                        { code -> provinceCode = code },
                        { code -> districtViewModel.fetchData(code) }
                    )
                    Spacer(modifier = Modifier.padding(10.dp))

                    DropDownMenuProvince(
                        districts,
                        "Quận/Huyện",
                        { code -> districtCode = code },
                        { code -> wardViewModel.fecthData(code) }
                    )
                    Spacer(modifier = Modifier.padding(10.dp))

                    DropDownMenuProvince(
                        wards,
                        "Xã/Phường",
                        { code -> wardsCode = code },
                        {})
                    Spacer(modifier = Modifier.padding(10.dp))
                    var details by remember {
                        mutableStateOf("")
                    }
                    OutlinedTextField(value = details, onValueChange = {
                        details = it
                    },
                        modifier = Modifier.fillMaxSize(),
                        label = { Text("Chi tiết địa chỉ") },
                        placeholder = { Text(text = "Nhập chi tiết địa chỉ") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = null,
                                tint = colorPrimary
                            )
                        }
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = "Số điện thoại",
                        style = MaterialTheme.typography.subtitle1,
                        color = dark_gray,
                    )
                    var phone by remember {
                        mutableStateOf("")
                    }
                    OutlinedTextField(value = phone, onValueChange = {
                        phone = it
                    },
                        modifier = Modifier.fillMaxSize(),
                        label = { Text("Số điện thoại") },
                        placeholder = { Text(text = "Nhập Email") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = null,
                                tint = colorPrimary
                            )
                        }
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Button(
                            onClick = {
                                userViewModel.addNewAddress(
                                    provine = provinceCode,
                                    district = districtCode,
                                    ward = wardsCode,
                                    phone = phone,
                                    detail = details
                                )
                                navController.navigate(Screen.AccountScreen.route)
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary),
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                                .height(60.dp),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                text = "Đăng ký tài khoản",
                                color = white,
                                fontWeight = FontWeight.Bold
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = null,
                                tint = white,
                                modifier = Modifier
                                    .padding(start = 4.dp)
                                    .size(20.dp, 20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSc() {
    val nav = rememberNavController()
    NewAddressScreen(navController = nav)
}
