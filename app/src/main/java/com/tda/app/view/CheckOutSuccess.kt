package com.tda.app.view

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tda.app.R
import com.tda.app.ui.theme.GrayText
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.white

@Composable
fun CheckOutSuccessScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.ic__order_success),
            contentDescription = "Your image description",
            modifier = Modifier.size(400.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Đặt hàng thành công",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(vertical = 16.dp)
                    .wrapContentHeight(),
                textAlign = TextAlign.Center,
                color = GrayText,
            )
            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary),
                modifier = Modifier
                    .width(200.dp)
                    .padding(
                        top = 30.dp,
                    )
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Trang chủ",
                    color = white,
                    style = MaterialTheme.typography.button,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
            }
            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary),
                modifier = Modifier
                    .width(200.dp)
                    .padding(
                        top = 30.dp,
                        bottom = 34.dp
                    )
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Tiếp tục mua hàng",
                    color = white,
                    style = MaterialTheme.typography.button,
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
            }
        }
    }
}
@Preview
@Composable
fun PreviewListProductScr() {
    val nav = rememberNavController()
    CheckOutSuccessScreen(nav)
}