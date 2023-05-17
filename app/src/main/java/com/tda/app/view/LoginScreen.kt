package com.tda.app.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tda.app.R
import com.tda.app.navigation.Screen
import com.tda.app.ui.theme.*
import com.tda.app.viewmodel.DialogViewModel
import com.tda.app.viewmodel.LoginViewModel
import com.tda.app.viewmodel.UserViewModel

@Composable

fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val login by loginViewModel.state.collectAsState()
    var isOpened by remember {
        mutableStateOf(false)
    }
    var displayed by remember {
        mutableStateOf(false)
    }
    if (isOpened) {
        MessageDialog(
            title = "Đăng nhập thất bại",
            msg = "Vui lòng kiểm tra email và mật khẩu",
            onChange = {
                isOpened = false
                loginViewModel.continueLogin()
            }
        )
    }
    if (displayed) {
        ProgressIndicator()
    }
    when (login) {
        0 -> {}
        1 -> {
            displayed = false
            LaunchedEffect(key1 = ""){
                navController.navigate(Screen.HomeScreen.route)
            }
        }

        2 -> {
            displayed = false
            isOpened = true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ConstraintLayout {

            val (logoimageref, loginformref) = createRefs()

            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(280.dp)
                    .constrainAs(logoimageref) {
                        top.linkTo(loginformref.top)
                        bottom.linkTo(loginformref.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                Header()
            }

            Surface(
                color = ghost_white,
                shape = RoundedCornerShape(40.dp).copy(
                    bottomStart = ZeroCornerSize,
                    bottomEnd = ZeroCornerSize
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 100.dp)
                    .constrainAs(loginformref) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        colorPrimary,
                                        fontFamily = firaSansFamily,
                                        fontWeight = FontWeight.Bold
                                    )
                                ) {
                                    append("Đăng nhập ")
                                }

                                withStyle(
                                    style = SpanStyle(
                                        dark_gray,
                                        fontFamily = firaSansFamily,
                                        fontWeight = FontWeight.Normal
                                    )
                                ) {
                                    append("với tài khoản của bạn.")
                                }

                            },
                            style = MaterialTheme.typography.subtitle1,
                            modifier = Modifier,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }


                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = "Địa chỉ Email",
                        style = MaterialTheme.typography.subtitle1,
                        color = dark_gray,
                        modifier = Modifier.padding(
                            top = 10.dp,
                            bottom = 20.dp
                        )
                    )

                    var user_email by remember { mutableStateOf("") }

                    TextField(
                        value = user_email,
                        leadingIcon = {
                            Row(
                                modifier = Modifier.wrapContentWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                content = {
                                    Icon(
                                        imageVector = Icons.Default.Email,
                                        contentDescription = null,
                                        tint = colorPrimary
                                    )
                                    Canvas(
                                        modifier = Modifier
                                            .height(24.dp)
                                            .padding(start = 10.dp)
                                    ) {
                                        drawLine(
                                            color = light_gray,
                                            start = Offset(0f, 0f),
                                            end = Offset(0f, size.height),
                                            strokeWidth = 2.0F
                                        )
                                    }
                                }
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = white,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        label = { Text(text = "Địa chỉ Email") },
                        shape = RoundedCornerShape(8.dp),
                        onValueChange = {
                            user_email = it
                        }
                    )

                    Text(
                        text = "Mật khẩu",
                        style = MaterialTheme.typography.subtitle1,
                        color = dark_gray,
                        modifier = Modifier.padding(
                            top = 10.dp,
                            bottom = 20.dp
                        )
                    )

                    var password by remember { mutableStateOf("") }

                    TextField(
                        value = password,
                        leadingIcon = {
                            Row(
                                modifier = Modifier.wrapContentWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                content = {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = null,
                                        tint = colorPrimary
                                    )
                                    Canvas(
                                        modifier = Modifier
                                            .height(24.dp)
                                            .padding(start = 10.dp)
                                    ) {
                                        drawLine(
                                            color = light_gray,
                                            start = Offset(0f, 0f),
                                            end = Offset(0f, size.height),
                                            strokeWidth = 2.0F
                                        )
                                    }
                                }
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = white,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        label = { Text(text = "Mật khẩu") },
                        shape = RoundedCornerShape(8.dp),
                        onValueChange = {
                            password = it
                        }
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "Quên mật khẩu",
                            style = MaterialTheme.typography.subtitle2,
                            color = colorPrimary,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .clickable {
                                    navController.navigate("verification_screen")
                                }
                        )
                    }

                    Button(
                        onClick = {
                            displayed = true
                            loginViewModel.loginAccount(user_email, password)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 30.dp,
                                bottom = 34.dp
                            )
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Đăng nhập",
                            color = white,
                            style = MaterialTheme.typography.button,
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                append("Chưa có tài khoản? Đăng ký")
                                addStyle(
                                    SpanStyle(color = colorPrimary),
                                    19,
                                    this.length
                                )
                            },
                            style = MaterialTheme.typography.subtitle1,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.clickable {
                                navController.navigate("signup_screen")
                            }
                        )
                    }

                }

            }

        }
    }
}

@Composable
fun MessageDialog(
    title: String,
    msg: String,
    onChange: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onChange() },
        title = {
            Text(
                text = title,
                color = colorPrimary,
                fontSize = 16.sp
            )
        },
        text = { Text(text = msg) },
        confirmButton = {
            TextButton(onClick = {
                onChange()
            }) {
                Text(text = "Xác nhận", color = colorPrimary)
            }
        }
    )
}

@Composable
fun Header() {
    Image(
        ColorPainter(colorPrimary),
        contentDescription = "login_bg",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.fillMaxSize()
    )
    Column(
        modifier = Modifier.padding(bottom = 40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.tda_logo),
            contentDescription = "login_bg",
            modifier = Modifier.wrapContentWidth()
        )

        Text(
            text = "TDA Laptop",
            color = white,
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 2.sp
        )
    }
}

@Composable
fun ProgressIndicator() {
    AlertDialog(
        onDismissRequest = { },
        title = {
            Text(text = "Đang xử lý")
        },
        text = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    color = colorPrimary
                )

            }
        },
        confirmButton = {
            TextButton(onClick = { }) {
                Text(text = "Hủy", color = colorPrimary)
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    LoginScreen(navController);
}
