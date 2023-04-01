package com.tda.app.view

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import com.tda.app.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import com.tda.app.navigation.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tda.app.data.model.JwtResponse
import com.tda.app.data.model.LoginBody
import com.tda.app.data.service.ApiService
import com.tda.app.data.service.RetrofitClient
import com.tda.app.ui.theme.*
import dagger.hilt.android.internal.Contexts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun LoginScreen(navController: NavController) {

    var apiService: ApiService?

    val firaSansFamily = FontFamily(
        Font(com.tda.app.R.font.dmsansregular, FontWeight.Normal),
        Font(com.tda.app.R.font.dmsansmedium, FontWeight.Medium),
        Font(com.tda.app.R.font.dmsansbold, FontWeight.Bold),
    )

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
                                .clickable { }
                        )
                    }

                    Button(
                        onClick = {
                            apiService = RetrofitClient.getRetrofitApiForTDA()
                                ?.create(ApiService::class.java)
                            val body = LoginBody(user_email, password)
                            apiService?.loginRestfulApi(body)?.enqueue(object :
                                Callback<JwtResponse> {
                                override fun onResponse(
                                    call: Call<JwtResponse>,
                                    response: Response<JwtResponse>
                                ) {
                                    Log.e("auth", "Call API Auth Success")
                                    val jwt = response.body()
                                    if (jwt != null) {
                                        Log.e("auth", jwt.jwt)
                                    } else {
                                        Log.e("auth", "Login fail")
                                    }
                                }
                                override fun onFailure(call: Call<JwtResponse>, t: Throwable) {
                                    Log.e("auth", "Auth fail")
                                    Log.e("auth-error", "Message: ${t.message}")
                                }
                            })
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    LoginScreen(navController);
}
