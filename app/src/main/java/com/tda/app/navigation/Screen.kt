package com.tda.app.navigation


sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object SignUp : Screen("signup_screen")
    object Verification : Screen("verification_screen")
    object ResetPassword : Screen("resetpassword_screen")
    object HomeScreen : Screen("home_screen")
    object CategoryScreen : Screen("category_screen")
    object ProductInCategoryCode : Screen("product_categoryCode/{code}")
    object SearchScreen : Screen("search_screen")
    object ProductDetail : Screen("product_detail/{code}")
    object AccountScreen : Screen("Account_screen")
    object PopularListScreen : Screen("popular_list_screen")
    object DetailsScreen : Screen("details_screen")
    object AddToCartScreen : Screen("add_to_cart_screen")
}
