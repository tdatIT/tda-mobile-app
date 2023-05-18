package com.tda.app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tda.app.R
import com.tda.app.model.DivisionGroup
import com.tda.app.navigation.Screen
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.dark_gray
import com.tda.app.ui.theme.ghost_white
import com.tda.app.ui.theme.white
import com.tda.app.utils.Constants.EMAIL_REGEX
import com.tda.app.viewmodel.DistrictViewModel
import com.tda.app.viewmodel.ProvinceViewModel
import com.tda.app.viewmodel.RegisterViewModel
import com.tda.app.viewmodel.WardViewModel

@Composable
fun SignUpScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel(),
    provinceViewModel: ProvinceViewModel = hiltViewModel(),
    districtViewModel: DistrictViewModel = hiltViewModel(),
    wardViewModel: WardViewModel = hiltViewModel()
) {

    val provinces by provinceViewModel.provinces.collectAsState()
    val districts by districtViewModel.district.collectAsState()
    val wards by wardViewModel.wards.collectAsState()

    val registerStatus by registerViewModel.state.collectAsState()

    var isOpened by remember {
        mutableStateOf(false)
    }
    var displayed by remember {
        mutableStateOf(false)
    }
    if (isOpened) {
        MessageDialog(
            title = "Đăng ký thất bại",
            msg = "Vui lòng kiểm tra dữ liệu đầu vào",
            onChange = {
                isOpened = false
                registerViewModel.continueRegister()
            }
        )
    }

    if (displayed) {
        ProgressIndicator()
    }

    when (registerStatus) {
        0 -> {}
        1 -> {
            displayed = false
            LaunchedEffect(key1 = "") {
                navController.navigate(Screen.Verification.route)
            }
        }

        2 -> {
            displayed = false
            isOpened = true
        }
    }

    var passwordMatch by remember {
        mutableStateOf(true)
    }
    var firstname by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var lastname by remember {
        mutableStateOf("")
    }
    var repassword by remember {
        mutableStateOf("")
    }
    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    var email by remember {
        mutableStateOf("")
    }
    var emailMatch by remember {
        mutableStateOf(true)
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ConstraintLayout {
            val (cartitemsbgref, checkoutref) = createRefs()
            Box(modifier = Modifier
                .height(100.dp)
                .constrainAs(cartitemsbgref) {
                    top.linkTo(cartitemsbgref.top)
                    bottom.linkTo(cartitemsbgref.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                Image(
                    painter = painterResource(id = R.drawable.login_bg),
                    contentDescription = "login bg",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxSize()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navController.navigate("login_screen")
                    }) {
                        Icon(
                            modifier = Modifier.size(32.dp, 32.dp),
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "",
                            tint = white
                        )
                    }
                    Text(
                        text = "Đăng ký",
                        color = white,
                        modifier = Modifier.padding(end = 180.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                    )

                }
            }

            Surface(color = ghost_white,
                shape = RoundedCornerShape(40.dp).copy(
                    bottomStart = ZeroCornerSize,
                    bottomEnd = ZeroCornerSize
                ), modifier = Modifier
                    .padding(top = 70.dp)
                    .constrainAs(checkoutref) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
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
                            Row() {
                                Column(
                                    modifier = Modifier
                                        .weight(5f)
                                        .padding(end = 10.dp)
                                ) {
                                    Text(
                                        text = "Họ",
                                        style = MaterialTheme.typography.subtitle1,
                                        color = dark_gray,
                                    )

                                    OutlinedTextField(value = lastname, onValueChange = {
                                        lastname = it
                                    },
                                        modifier = Modifier.fillMaxSize(),
                                        label = { Text("Họ") },
                                        placeholder = { Text(text = "Nhập Họ") },
                                        leadingIcon = {
                                            Icon(
                                                imageVector = Icons.Default.Person,
                                                contentDescription = null,
                                                tint = colorPrimary
                                            )
                                        }
                                    )
                                }
                                Column(modifier = Modifier.weight(5f)) {
                                    Text(
                                        text = "Tên",
                                        style = MaterialTheme.typography.subtitle1,
                                        color = dark_gray,
                                    )

                                    OutlinedTextField(value = firstname, onValueChange = {
                                        firstname = it
                                    },
                                        modifier = Modifier.fillMaxSize(),
                                        label = { Text("Tên") },
                                        placeholder = { Text(text = "Nhập tên") },
                                        leadingIcon = {
                                            Icon(
                                                imageVector = Icons.Default.Person,
                                                contentDescription = null,
                                                tint = colorPrimary
                                            )
                                        }
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.padding(10.dp))
                            Text(
                                text = "Mật khẩu",
                                style = MaterialTheme.typography.subtitle1,
                                color = dark_gray,
                            )
                            var passwordVisible by remember {
                                mutableStateOf(false)
                            }

                            OutlinedTextField(
                                value = password,
                                onValueChange = {
                                    password = it
                                },
                                modifier = Modifier.fillMaxSize(),
                                label = { Text("Mật khẩu") },
                                placeholder = { Text(text = "Nhập mật khẩu") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = null,
                                        tint = colorPrimary
                                    )
                                },
                                trailingIcon = {
                                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                        Icon(
                                            imageVector = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Default.Visibility,
                                            contentDescription = if (passwordVisible) "Hide Password" else "Show password"
                                        )
                                    }
                                },
                                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                            Text(
                                text = "Nhập lại mật khẩu",
                                style = MaterialTheme.typography.subtitle1,
                                color = dark_gray,
                            )

                            OutlinedTextField(
                                value = repassword,
                                onValueChange = {
                                    repassword = it
                                    passwordMatch = password.equals(repassword)
                                },
                                modifier = Modifier.fillMaxSize(),
                                label = { Text("Nhập lại mật khẩu") },
                                placeholder = { Text(text = "Nhập lại mật khẩu") },
                                trailingIcon = {
                                    IconButton(
                                        onClick = {
                                            passwordVisibility = !passwordVisibility
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                            contentDescription = if (passwordVisibility) "Ẩn mật khẩu" else "Hiện mật khẩu"
                                        )
                                    }
                                },
                                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Password
                                ),
                                isError = !passwordMatch,
                                leadingIcon = {
                                    if (repassword.isNotEmpty()) {
                                        Icon(
                                            imageVector = if (passwordMatch) Icons.Default.Check else Icons.Default.Clear,
                                            contentDescription = null,
                                            tint = if (passwordMatch) colorPrimary else Color.Red
                                        )
                                    }
                                },
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                            Text(
                                text = "Email",
                                style = MaterialTheme.typography.subtitle1,
                                color = dark_gray,
                            )

                            OutlinedTextField(value = email, onValueChange = {
                                email = it
                                emailMatch = EMAIL_REGEX.matches(email)
                            },
                                modifier = Modifier.fillMaxSize(),
                                label = { Text("Email") },
                                placeholder = { Text(text = "Nhập Email") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Email,
                                        contentDescription = null,
                                        tint = colorPrimary
                                    )
                                },
                                isError = !emailMatch,
                                trailingIcon = {
                                    Icon(
                                        imageVector = if (emailMatch) Icons.Default.Check else Icons.Default.Clear,
                                        contentDescription = null,
                                        tint = if (emailMatch) colorPrimary else Color.Red
                                    )
                                }

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
                                        displayed = true
                                        registerViewModel.registerAccount(
                                            firstName = firstname,
                                            lastName = lastname,
                                            email = email,
                                            password = password,
                                            confirmPassword = repassword,
                                            phone = phone,
                                            address_detail = details,
                                            provinceCode = provinceCode,
                                            districtCode = districtCode,
                                            wardCode = wardsCode
                                        )
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
    }
}

@Composable
fun DropDownMenuProvince(
    list: List<DivisionGroup>,
    content: String,
    onItemSelected: (Int) -> Unit,
    onUpdate: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = selectedItem,
                onValueChange = {
                    selectedItem = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded = true }),
                label = {
                    Text(text = content)
                },
                readOnly = true,
                trailingIcon = {
                    Icon(
                        if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier.clickable(onClick = { expanded = !expanded })
                    )
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                list?.let {
                    it.forEach { t ->
                        DropdownMenuItem(onClick = {
                            selectedItem = t.name
                            expanded = false
                            onItemSelected(t.code)
                            onUpdate(t.code)
                        }) {
                            Text(text = t.name)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewScreens() {
    val nav = rememberNavController()
    SignUpScreen(navController = nav)
}

