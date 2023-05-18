package com.tda.app.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tda.app.ui.theme.colorPrimary


@Composable
fun AddToCartDialog(
    onChange: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onChange() },
        title = {
            Text(
                text = "Thành công",
                color = colorPrimary,
                fontSize = 16.sp
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "Đã thêm sản phẩm", color = colorPrimary)
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = "Success",
                    tint = colorPrimary,
                    modifier = Modifier.size(50.dp)
                )

            }
        },
        confirmButton = {
            TextButton(onClick = {
                onChange()
            }) {
                Text(text = "Xác nhận", color = colorPrimary)
            }
        }
    )
}
