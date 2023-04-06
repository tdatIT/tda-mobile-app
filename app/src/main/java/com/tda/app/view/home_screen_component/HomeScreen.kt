package com.tda.app.view.home_screen_component

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tda.app.ui.theme.ghost_white
import com.tda.app.ui.theme.white
import com.tda.app.view.*


@SuppressLint("RememberReturnType")
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    boxScrollState: ScrollState = rememberScrollState(),
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ConstraintLayout {

            val (logoimageref, loginformref) = createRefs()

            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(100.dp)
                    .constrainAs(logoimageref) {
                        top.linkTo(loginformref.top)
                        bottom.linkTo(loginformref.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                HeaderHome("TDA STORE", modifier = Modifier.padding(start = 120.dp))
            }

            Surface(
                color = ghost_white,
                shape = RoundedCornerShape(40.dp).copy(
                    bottomStart = ZeroCornerSize,
                    bottomEnd = ZeroCornerSize
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 30.dp)
                    .constrainAs(loginformref) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                 DashboardScreen(navController = navController)
                }

            }

        }
    }

}
@Composable
fun HeaderHome(string: String, modifier: Modifier) {
    var isSearchVisible by remember { mutableStateOf(false) }
    if(isSearchVisible == false)
    {
        Image(
            painter = painterResource(id = com.tda.app.R.drawable.login_bg),
            contentDescription = string,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = string,
                color = white,
                fontSize = 18.sp,
                letterSpacing = 2.sp,
                modifier = Modifier.padding(horizontal = 110.dp)
            )

            IconButton(onClick = { isSearchVisible = true }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = white,
                    modifier = Modifier.size(35.dp, 35.dp)
                        .padding(end = 10.dp)
                )
            }
        }
    }
    else{
        var searchText by remember { mutableStateOf("") }
        Image(
            painter = painterResource(id = com.tda.app.R.drawable.login_bg),
            contentDescription = "login_bg",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = searchText,
                onValueChange = {searchText = it },
                modifier = Modifier
                    .fillMaxWidth()
//                    .height(70.dp)
                    .padding(top = 0.dp),
//                .background(Color.White, RoundedCornerShape(16.dp)),
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text(text = "Search", color = Color.White) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp, 18.dp)
                    )
                }
            )
        }
    }
    }
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun defaultPreview() {
    val navController = rememberNavController()
    HomeScreen()
}
