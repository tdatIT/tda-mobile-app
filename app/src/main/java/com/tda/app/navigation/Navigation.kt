package com.tda.app.navigation

import VerificationScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tda.app.view.*

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(navController = navController)
        }
        composable(Screen.Verification.route) {
            VerificationScreen(navController = navController)
        }
        composable(Screen.ResetPassword.route) {
            ResetPassswordScreen(navController = navController)
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.CategoryScreen.route) {
            CategoryScreen(navController = navController)
        }
        composable(Screen.SearchScreen.route) {
            SearchScreen(nav = navController)
        }
        composable(Screen.AccountScreen.route) {
            AccountScreen(nav = navController)
        }
        composable(Screen.CartScreen.route) {
            CartScreen(nav = navController)
        }
        composable(
            Screen.ProductInCategoryCode.route,
            arguments = listOf(navArgument("code") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("code")?.let {
                ProductInCategoryScreen(
                    navController = navController,
                    code = it
                )
            }
        }
        composable(
            Screen.ProductDetail.route,
            arguments = listOf(navArgument("code") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("code")?.let {
                ProductDetailScreen(
                    nav = navController,
                    productCode = it
                )
            }
        }
        composable(
            Screen.SearchResultScreen.route,
            arguments = listOf(navArgument("keyword") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("keyword")?.let {
                SearchResultScreen(
                    nav = navController,
                    reqKw = it
                )
            }
        }
    }
}
//        composable(Screen.PopularListScreen.route) {
//            PopularListScreen(navController = navController)
//        }
//        composable(Screen.DetailsScreen.route) {
//            FlowerDetailsScreen(navController = navController)
//        }
//        composable(Screen.AddToCartScreen.route) {
//            CheckoutScreen()
//        }



