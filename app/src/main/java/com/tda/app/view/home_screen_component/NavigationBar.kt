package com.eritlab.jexmon.presentation.dashboard_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tda.app.ui.theme.PrimaryLightColor
import com.tda.app.ui.theme.TextColor
import com.tda.app.view.home_screen_component.BottomNavItem

@Composable
fun NavigationBar(
    navController: NavController,
    isVisible: (Boolean) -> Unit,
) {
    val navItemList = listOf(
        BottomNavItem.HomeNav,
        BottomNavItem.FavouriteNav,
        BottomNavItem.ChatNav,
        BottomNavItem.ProfileNav,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var bottomNavVisibility by remember { mutableStateOf<Boolean>(true) }

    if (bottomNavVisibility) {
        BottomNavigation(
            backgroundColor = Color.White,
            modifier = Modifier
                .background(color = Color.White)
                .shadow(
                    shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                    elevation = 12.dp,
                )
                .clip(RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp))


        ) {
            navItemList.forEach { screen ->
                BottomNavigationItem(
                    selected = navBackStackEntry?.destination?.route == screen.route,
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = null,
                            tint = if (navBackStackEntry?.destination?.route == screen.route) MaterialTheme.colors.PrimaryLightColor else LocalContentColor.current,
                        )
                    },
                    //  label = { Text(text = screen.tittle) },
                    onClick = {
                        navController.navigate(screen.route)
                    },
                    unselectedContentColor = MaterialTheme.colors.TextColor,
                )
            }
        }
    }

//    //hide topBar
//    when (navBackStackEntry?.destination?.route) {
//        ShopHomeScreen.DashboardScreen.route -> {
//            bottomNavVisibility = true
//            isVisible(true)
//        }
//        DetailScreen.ProductDetailScreen.route + "/{${Constrains.PRODUCT_ID_PARAM}}" -> {
//
//            bottomNavVisibility = false
//            isVisible(false)
//        }
//        DetailScreen.CartScreen.route -> {
//            bottomNavVisibility = false
//            isVisible(false)
//        }
//        DetailScreen.NotificationScreen.route -> {
//            bottomNavVisibility = false
//            isVisible(false)
//        }
//        else -> {
//            bottomNavVisibility = true
//            isVisible(false)
//        }
//    }


}