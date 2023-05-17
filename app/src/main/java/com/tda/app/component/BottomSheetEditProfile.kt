package com.tda.app.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.dark_gray
import com.tda.app.ui.theme.white
import com.tda.app.view.MessageDialog
import com.tda.app.viewmodel.UserViewModel


@Composable
fun BottomSheetEditProfile(userViewModel: UserViewModel = hiltViewModel(),onClose: () -> Unit) {
    val user by userViewModel.state.collectAsState()
    userViewModel.getUserFromDB()
    var isBottomSheetOpen by remember { mutableStateOf(true) }
    var isOpenedConfirm by remember {
        mutableStateOf(false)
    }
    if(isOpenedConfirm)
    {
        MessageDialog(
            title = "Cập nhật thông tin",
            msg = "Thông tin đã được thay đổi",
            onChange = {isOpenedConfirm = false}
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        user?.let {
            var firstName by remember { mutableStateOf(it.firstname) }
            var lastName by remember { mutableStateOf(it.lastname) }
            var phone by remember { mutableStateOf(it.phone) }
            Row(
                modifier = Modifier
                    .padding(0.dp, 10.dp, 0.dp, 10.dp)
                    .fillMaxWidth()
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary),
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    shape = RoundedCornerShape(16.dp), onClick = {
                        isBottomSheetOpen = false
                        onClose()}
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
                        .padding(start = 130.dp),
                    shape = RoundedCornerShape(16.dp), onClick = {
                        isOpenedConfirm=true
                        userViewModel.changeInfo(firstName, lastName, phone)
                        isBottomSheetOpen = false
                        onClose()
                    }
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
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.Bottom)
                ) {

                    OutlinedTextField(
                        value = lastName,
                        onValueChange = {
                            lastName = it
                        },
                        label = { Text("Họ") },
                        placeholder = { Text(text = "Nhập họ") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = colorPrimary
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = colorPrimary
                        )
                    )

                }
            }
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.Bottom)
                ) {

                    OutlinedTextField(
                        value = firstName,
                        onValueChange = {
                            firstName = it
                        },
                        label = { Text("Tên") },
                        placeholder = { Text(text = "Nhập tên") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null,
                                tint = colorPrimary
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = colorPrimary
                        )
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier

                        .weight(1f)
                        .align(Alignment.Bottom)
                ) {
                    OutlinedTextField(
                        value = phone,
                        onValueChange = {
                            phone = it
                        },
                        label = { Text("Số điện thoại") },
                        placeholder = { Text(text = "Nhập số điện thoại") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = null,
                                tint = colorPrimary
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = colorPrimary
                        )
                    )
                }
            }
        }


    }
}