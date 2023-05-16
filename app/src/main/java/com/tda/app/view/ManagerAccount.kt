package com.tda.app.view

import NavigationBottomBar
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tda.app.R
import com.tda.app.component.BottomSheetEditProfile
import com.tda.app.navigation.Screen
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AccountScreen(
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val userLogged by userViewModel.state.collectAsState()
    userViewModel.getUserFromDB()

    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetContent = {
            BottomSheetEditProfile()
        },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetBackgroundColor = Color.White
    ) {
        Scaffold(
            topBar = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(MaterialTheme.colors.surface)
                        .padding(24.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .size(54.dp)
                                .clip(CircleShape),
                            painter = painterResource(id = R.drawable.ic_profile),
                            contentDescription = "profile picture"
                        )
                        Column(
                            modifier = Modifier.padding(start = 20.dp)
                        ) {
                            if (userLogged != null) {
                                Text(
                                    text = "${userLogged!!.firstname}",
                                    style = MaterialTheme.typography.h6,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "${userLogged!!.email}",
                                    style = MaterialTheme.typography.subtitle1
                                )
                            } else {
                                Text(
                                    text = "Chưa có tài khoản",
                                    style = MaterialTheme.typography.h6,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Vui lòng đăng nhập",
                                    style = MaterialTheme.typography.subtitle1
                                )
                            }
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(
                            modifier = Modifier.size(36.dp),
                            onClick = {
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = "edit",
                                tint = colorPrimary,
                                modifier = Modifier
                                    .size(28.dp)
                                    .clickable {
                                        scope.launch {
                                            modalBottomSheetState.show()
                                        }
                                    }
                            )
                        }
                    }
                }
            }, bottomBar = {
                NavigationBottomBar(navController)
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(
                        color = Color(0xFFB3B3B3),
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp
                    )
                    AccountNavItems(
                        icon = R.drawable.orders,
                        name = "Thông tin giỏ hàng",
                        onClick = {
                            scope.launch {

                            }
                        })
                    AccountNavItems(icon = R.drawable.my_details, name = "Đơn hàng", onClick = {
                        scope.launch {

                        }
                    })
                    AccountNavItems(
                        icon = R.drawable.address,
                        name = "Địa chỉ nhận hàng",
                        onClick = {
                            navController.navigate("change_address_screen")
                        })
                    AccountNavItems(
                        icon = R.drawable.help,
                        name = "Help",
                        onClick = { })
                    AccountNavItems(
                        icon = R.drawable.notifications,
                        name = "Thông báo",
                        onClick = { })
                    AccountNavItems(
                        icon = R.drawable.about,
                        name = "About",
                        onClick = { })
                    Spacer(modifier = Modifier.height(26.dp))
                    if (userLogged != null) {
                        LogoutBtn(nav = navController)
                    } else
                        LoginBtn(nav = navController)
                    Spacer(modifier = Modifier.height(26.dp))
                }
            }

        )
    }


}

@Composable
fun LogoutBtn(nav: NavController) {
    Button(
        onClick = {},
        modifier = Modifier
            .height(57.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFF2F3F2),
            contentColor = Color(0xFF53B175)
        ),
        shape = RoundedCornerShape(19),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Outlined.Logout, contentDescription = "")
            Text("Đăng xuất")
        }
    }
}

@Composable
fun LoginBtn(nav: NavController) {
    Button(
        onClick = { nav.navigate(Screen.LoginScreen.route) },
        modifier = Modifier
            .height(57.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFF2F3F2),
            contentColor = Color(0xFF53B175)
        ),
        shape = RoundedCornerShape(19),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Outlined.Logout, contentDescription = "")
            Text("Đăng nhập/Đăng ký")
        }
    }
}

@Composable
private fun AccountNavItems(@DrawableRes icon: Int, name: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp)
                .clickable(indication = rememberRipple(bounded = true),
                    interactionSource = remember { MutableInteractionSource() }) {
                    onClick.invoke()
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                Image(
                    modifier = Modifier.height(20.dp),
                    painter = painterResource(id = icon),
                    contentDescription = "icon",
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = name)
            }
            Image(
                painter = painterResource(id = R.drawable.arrow_forward),
                contentDescription = "forward",
            )
        }
    }
    Divider(color = Color(0xFFB3B3B3), modifier = Modifier.fillMaxWidth(), thickness = 1.dp)
}

@Preview(showBackground = true)
@Composable
fun previewAccountScreens() {
    val nav = rememberNavController()
    AccountScreen(navController = nav)
}