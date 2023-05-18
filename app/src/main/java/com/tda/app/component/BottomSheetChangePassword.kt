package com.tda.app.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.favourite


@Composable
fun ChangePasswordDialog(onSubmit: (String, String) -> Unit, onDismiss: () -> Unit) {

    var oldPass by remember {
        mutableStateOf("")
    }
    var newPass by remember {
        mutableStateOf("")
    }
    var confirmpass by remember {
        mutableStateOf("")
    }
    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    AlertDialog(
        modifier = Modifier.width(530.dp),
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = { onSubmit(newPass, oldPass) }) {
                Text(text = "Thay đổi", color = colorPrimary)
            }
        }, dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = "Hủy", color = favourite)
            }
        }, text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(260.dp)
            ) {
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
                            .fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = oldPass,
                            onValueChange = {
                                oldPass = it
                            },
                            placeholder = { Text(text = "Mật khẩu cũ") },
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
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            modifier = Modifier.fillMaxWidth()
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
                        OutlinedTextField(
                            value = newPass,
                            onValueChange = {
                                newPass = it
                            },
                            placeholder = { Text(text = "Mật khẩu mới") },
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
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            modifier = Modifier.fillMaxWidth()
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
                        OutlinedTextField(
                            value = confirmpass,
                            onValueChange = {
                                confirmpass = it
                            },
                            placeholder = { Text(text = "Xác nhận MK") },
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
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        })
}

