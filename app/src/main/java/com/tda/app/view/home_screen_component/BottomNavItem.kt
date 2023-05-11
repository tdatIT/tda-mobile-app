package com.tda.app.view.home_screen_component

import com.tda.app.R
import com.tda.app.navigation.Screen

sealed class BottomNavItem(val tittle: String, val icon: Int, val route: String) {
    object HomeNav : BottomNavItem(
        tittle = "Home",
        icon = R.drawable.shop_icon,
        route = Screen.HomeScreen.route
    )

    object CartNav : BottomNavItem(
        tittle = "Favourite",
        icon = R.drawable.cart_icon,
        route = Screen.LoginScreen.route
    )

    object SearchNav : BottomNavItem(
        tittle = "Chat",
        icon = R.drawable.search_icon,
        route = Screen.SearchScreen.route
    )

    object ProfileNav : BottomNavItem(
        tittle = "Profile",
        icon = R.drawable.user_icon,
        route = Screen.LoginScreen.route
    )
}
