package com.tda.app.view

import NavigationBottomBar
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tda.app.R
import com.tda.app.ui.theme.TdaMobilemobileTheme
import com.tda.app.ui.theme.colorPrimary


@Composable
fun AccountScreen(nav: NavController) {

    Scaffold(topBar = {
        HeaderAccount(nav, "Đạt", "tdat.it2k2@gmail.com")
    }, bottomBar = {
        NavigationBottomBar(navController = nav)
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Spacer(modifier = Modifier.height(16.dp))

            Divider(color = Color(0xFFB3B3B3), modifier = Modifier.fillMaxWidth(), thickness = 1.dp)
            AccountNavItem(icon = R.drawable.orders, name = "Thông tin giỏ hàng")
            AccountNavItem(icon = R.drawable.my_details, name = "Đơn hàng")
            AccountNavItem(icon = R.drawable.help, name = "Help")
            AccountNavItem(icon = R.drawable.notifications, name = "Thông báo")
            AccountNavItem(icon = R.drawable.about, name = "About")
            Spacer(modifier = Modifier.height(26.dp))
            Button(
                modifier = Modifier
                    .height(57.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = {
                },
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
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.log_out),
                        contentDescription = "log out",
                        contentScale = ContentScale.Fit,
                    )
                    Text(
                        modifier = Modifier.padding(start = 100.dp),
                        text = "Log Out",
                        color = colorPrimary
                    )
                }
            }
            Spacer(modifier = Modifier.height(26.dp))

        }
    }
}

@Composable
fun AccountNavItem(@DrawableRes icon: Int, name: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
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
                contentDescription = "forward"
            )
        }
    }
    Divider(color = Color(0xFFB3B3B3), modifier = Modifier.fillMaxWidth(), thickness = 1.dp)
}

@Preview(showBackground = true)
@Composable
fun AccountScreenPreviewHome() {
    val nav = rememberNavController()
    TdaMobilemobileTheme {
        AccountScreen(nav)
    }
}