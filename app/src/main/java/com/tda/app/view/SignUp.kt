package com.tda.app.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tda.app.R
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.dark_gray
import com.tda.app.ui.theme.ghost_white
import com.tda.app.ui.theme.white

@Composable
fun SignUpScreen(navController: NavController) {
    val firaSansFamily = FontFamily(
        Font(R.font.dmsansregular, FontWeight.Normal),
        Font(R.font.dmsansmedium, FontWeight.Medium),
        Font(R.font.dmsansbold, FontWeight.Bold),
    )
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
                        text = "Sign Up",
                        color = white,
                        modifier = Modifier.padding(end = 150.dp),
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
                                    var email by remember {
                                        mutableStateOf("")
                                    }
                                    OutlinedTextField(value = email, onValueChange = {
                                        email = it
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
                                    var email by remember {
                                        mutableStateOf("")
                                    }
                                    OutlinedTextField(value = email, onValueChange = {
                                        email = it
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
                            var password by remember {
                                mutableStateOf("")
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
                            var repassword by remember {
                                mutableStateOf("")
                            }
                            var passwordVisibility by remember {
                                mutableStateOf(false)
                            }
                            OutlinedTextField(
                                value = repassword,
                                onValueChange = {
                                    repassword = it
                                },
                                modifier = Modifier.fillMaxSize(),
                                label = { Text("Nhập lại mật khẩu") },
                                placeholder = { Text(text = "Nhập lại mật khẩu") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = null,
                                        tint = colorPrimary
                                    )
                                },
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
                                )
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                            Text(
                                text = "Email",
                                style = MaterialTheme.typography.subtitle1,
                                color = dark_gray,
                            )
                            var email by remember {
                                mutableStateOf("")
                            }
                            OutlinedTextField(value = email, onValueChange = {
                                email = it
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
                                }
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                            Text(
                                text = "Địa chỉ",
                                style = MaterialTheme.typography.subtitle1,
                                color = dark_gray,
                            )
                            var list1 = listOf("Vũng Tàu", "Vũng Tàu")
                            DropDownMenu(list = list1, "Tỉnh/Thành Phố")
                            Spacer(modifier = Modifier.padding(10.dp))
                            var list2 = listOf("Vũng Tàu", "Vũng Tàu")
                            DropDownMenu(list = list2, "Quận/Huyện")
                            Spacer(modifier = Modifier.padding(10.dp))
                            var list3 = listOf("Vũng Tàu", "Vũng Tàu")
                            DropDownMenu(list = list3, "Xã phường")
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
                                    onClick = {},
                                    colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary),
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth()
                                        .height(60.dp),
                                    shape = RoundedCornerShape(16.dp)
                                ) {
                                    Text(
                                        text = "Đăng ký",
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
fun DropDownMenu(list: List<String>, content: String) {
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
                list.forEach { label ->
                    DropdownMenuItem(onClick = {
                        selectedItem = label
                        expanded = false
                    }) {
                        Text(text = label)
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
    SplashScreen(navController = nav)
}

