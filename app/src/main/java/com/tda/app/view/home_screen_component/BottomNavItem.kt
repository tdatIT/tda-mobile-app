package com.tda.app.view.home_screen_component

import com.tda.app.R
import com.tda.app.navigation.Screen
sealed class BottomNavItem(val tittle: String, val icon: Int, val route: String) {
    object HomeNav : BottomNavItem(
        tittle = "Home",
        icon = R.drawable.shop_icon,
        route = Screen.LoginScreen.route
    )

    object FavouriteNav : BottomNavItem(
        tittle = "Favourite",
        icon = R.drawable.heart_icon,
        route = Screen.LoginScreen.route
    )

    object ChatNav : BottomNavItem(
        tittle = "Chat",
        icon = R.drawable.chat_bubble_icon,
        route = Screen.LoginScreen.route
    )

    object ProfileNav : BottomNavItem(
        tittle = "Profile",
        icon = R.drawable.user_icon,
        route = Screen.LoginScreen.route
    )
}
