package com.tda.app.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tda.app.view.HeaderBar
import com.tda.app.R
import com.tda.app.ui.theme.black
import com.tda.app.ui.theme.colorPrimary
import com.tda.app.ui.theme.ghost_white
import com.tda.app.ui.theme.white

@Composable
fun CategoryScreen(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        ConstraintLayout {
            val (topappbarbgref, popularitemsref) = createRefs()

            Box(modifier = Modifier
                .height(100.dp)
                .constrainAs(topappbarbgref) {
                    top.linkTo(topappbarbgref.top)
                    bottom.linkTo(topappbarbgref.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                HeaderBar()
            }

            Surface(
                color = ghost_white,
                shape = RoundedCornerShape(40.dp)
                    .copy(bottomStart = ZeroCornerSize,
                        bottomEnd = ZeroCornerSize), modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 70.dp)
                    .constrainAs(popularitemsref) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)) {

                    PopularItemsSewction(navController)
                    Spacer(modifier = Modifier.padding(10.dp))
                    PopularItemsSewction(navController)
                    Spacer(modifier = Modifier.padding(10.dp))
                    PopularItemsSewction(navController)
                }
            }


        }
    }
}

@Composable
fun PopularItemsSewction(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .wrapContentHeight()
                .clip(RoundedCornerShape(16.dp))
                .background(white)
                .clickable {
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .size(100.dp),
                        painter = painterResource(R.drawable.ic_red_rose_bouquet),
                        contentDescription = "",
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                    ) {
                        Text(
                            text = "Angle",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = black,
                        )
                        Text(
                            text = "$567.00",
                            fontSize = 12.sp,
                            color = colorPrimary,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(colorPrimary)
                            .padding(4.dp)
                            .shadow(elevation = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ShoppingCart,
                            contentDescription = "",
                            tint = white,
                            modifier = Modifier
                                .size(20.dp, 20.dp)
                        )
                    }

                }

            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .wrapContentHeight()
                .clip(RoundedCornerShape(16.dp))
                .background(white)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .size(100.dp),
                        painter = painterResource(R.drawable.ic_pink_rose_bouquet),
                        contentDescription = "",
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                    ) {
                        Text(
                            text = "Jannien",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = black,
                        )
                        Text(
                            text = "$567.00",
                            fontSize = 12.sp,
                            color = colorPrimary,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(colorPrimary)
                            .padding(4.dp)
                            .shadow(elevation = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ShoppingCart,
                            contentDescription = "",
                            tint = white,
                            modifier = Modifier
                                .size(20.dp, 20.dp)
                        )
                    }

                }

            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPrevieawa() {
    val navController = rememberNavController()
    CategoryScreen(navController)
}

