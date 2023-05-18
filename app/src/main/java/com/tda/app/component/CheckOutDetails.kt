package com.tda.app.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tda.app.ui.theme.black
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.white


@Composable
fun CheckoutDetail() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .clip(RoundedCornerShape(16.dp))
        .background(white)) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)) {

            Text(text = "Price Details",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold)

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                Text(text = "Jannien Flower Bouquet",
                    fontSize = 14.sp,
                    color = Color.Gray)

                Text(text = "1 x $567.00",
                    fontSize = 14.sp,
                    color = Color.Gray)
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                Text(text = "Jannien Flower Bouquet",
                    fontSize = 14.sp,
                    color = Color.Gray)

                Text(text = "1 x $567.00",
                    fontSize = 14.sp,
                    color = Color.Gray)
            }


            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                Text(text = "Delivery Charges",
                    fontSize = 14.sp,
                    color = Color.Gray)

                Text(text = "$50.00",
                    fontSize = 14.sp,
                    color = Color.Gray)
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                Text(text = "Coupon Discount",
                    fontSize = 14.sp,
                    color = Color.Gray)

                Text(text = "$184.00",
                    fontSize = 14.sp,
                    color = Color.Gray)
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Divider(color = Color.Gray, thickness = 1.dp)
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                Text(text = "Total Amount Payable",
                    fontSize = 14.sp,
                    color = black,
                    fontWeight = FontWeight.Bold)

                Text(text = "$1000.00",
                    fontSize = 14.sp,
                    color = colorPrimary
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom) {
                Button(onClick = { },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(text = "Checkout",
                        color = white,
                        fontWeight = FontWeight.Bold)
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


