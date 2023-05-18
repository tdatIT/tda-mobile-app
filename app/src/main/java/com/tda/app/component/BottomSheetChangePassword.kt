package com.tda.app.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.dark_gray
import com.tda.app.ui.theme.white


@Composable
fun BottomSheetChangePassword() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        var oldPass:String = ""
        var newPass:String = ""
        var confirmpass:String = ""
        var passwordVisibility by remember {
            mutableStateOf(false)
        }
        Row(
            modifier = Modifier
                .padding(0.dp, 10.dp, 0.dp, 10.dp)
                .fillMaxWidth()
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary),
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                ,
                shape = RoundedCornerShape(16.dp), onClick = {}
            ) {
                Text(
                    text = "Cancle",
                    color = white,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = white,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .size(20.dp, 20.dp)
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary),
                modifier = Modifier
                    .padding(start = 130.dp)
                ,
                shape = RoundedCornerShape(16.dp), onClick = {}
            ) {
                Text(
                    text = "Submit",
                    color = white,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = white,
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .size(20.dp, 20.dp)
                )
            }
        }



        Row(
            modifier = Modifier
                .padding(0.dp, 10.dp, 0.dp, 10.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                    .weight(1f)
                    .align(Alignment.Bottom)
            ) {

                Text(
                    text = "Mật khẩu cũ",
                    style = MaterialTheme.typography.subtitle1,
                    color = dark_gray,
                )
                OutlinedTextField(
                    value = oldPass,
                    onValueChange = {
                        oldPass = it
                    },
                    label = { Text("Họ") },
                    placeholder = { Text(text = "Nhập họ") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = colorPrimary
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(0.dp, 10.dp, 0.dp, 10.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                    .weight(1f)
                    .align(Alignment.Bottom)
            ) {
                Text(
                    text = "Mật khẩu mới",
                    style = MaterialTheme.typography.subtitle1,
                    color = dark_gray,
                )
                OutlinedTextField(
                    value = newPass,
                    onValueChange = {
                        newPass = it
                    },
                    label = { Text("Mật khẩu mới") },
                    placeholder = { Text(text = "Nhập mật khẩu mới") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = colorPrimary
                        )
                    }, trailingIcon = {
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
                    }
                    ,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }
        }
//        Row(
//            modifier = Modifier
//                .padding(0.dp, 10.dp, 0.dp, 10.dp)
//                .fillMaxWidth()
//        ) {
//            Column(
//                modifier = Modifier
//                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
//                    .weight(1f)
//                    .align(Alignment.Bottom)
//            ) {
//                Text(
//                    text = "Nhập lại mật khẩu",
//                    style = MaterialTheme.typography.subtitle1,
//                    color = dark_gray,
//                )
//                OutlinedTextField(
//                    value = confirmpass,
//                    onValueChange = {
//                        confirmpass = it
//                    },
//                    label = { Text("Nhập lại mật khẩu") },
//                    placeholder = { Text(text = "Nhập lại mật khẩu") },
//                    leadingIcon = {
//                        Icon(
//                            imageVector = Icons.Default.Password,
//                            contentDescription = null,
//                            tint = colorPrimary
//                        )
//                    },
//                    trailingIcon = {
//                        IconButton(
//                            onClick = {
//                                passwordVisibility = !passwordVisibility
//                            }
//                        ) {
//                            Icon(
//                                imageVector = if (passwordVisibility) Icons.Default.Visibility else Icons.Default.VisibilityOff,
//                                contentDescription = if (passwordVisibility) "Ẩn mật khẩu" else "Hiện mật khẩu"
//                            )
//                        }
//                    }
//                    ,
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
//                )
//            }
//        }
    }
}
