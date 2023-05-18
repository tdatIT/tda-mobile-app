package com.tda.app.navigation

import VerificationScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tda.app.view.AccountScreen
import com.tda.app.view.CartScreen
import com.tda.app.view.CategoryScreen
import com.tda.app.view.ChangeAddressScreen
import com.tda.app.view.CheckOutSuccessScreen
import com.tda.app.view.CheckoutScreen
import com.tda.app.view.HomeScreen
import com.tda.app.view.LoginScreen
import com.tda.app.view.NewAddressScreen
import com.tda.app.view.OrderDetailsScreen
import com.tda.app.view.OrderScreen
import com.tda.app.view.ProductDetailScreen
import com.tda.app.view.ProductInCategoryScreen
import com.tda.app.view.ResetPassswordScreen
import com.tda.app.view.SearchResultScreen
import com.tda.app.view.SearchScreen
import com.tda.app.view.SignUpScreen
import com.tda.app.view.SplashScreen
import com.tda.app.view.WishListScreen

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
            VerificationScreen(nav = navController)
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
        composable(Screen.ChangeAddress.route) {
            ChangeAddressScreen(navController = navController)
        }
        composable(Screen.AccountScreen.route) {
            AccountScreen(navController)
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
        composable(
            Screen.OrderDetailScreen.route,
            arguments = listOf(navArgument("orderId") { type = NavType.LongType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getLong("orderId")?.let {
                OrderDetailsScreen(
                    navController = navController,
                    orderId = it
                )
            }
        }
        composable(Screen.CheckoutScreen.route) {
            CheckoutScreen(navController = navController)
        }
        composable(Screen.CheckOutSuccessScreen.route) {
            CheckOutSuccessScreen(navController = navController)
        }
        composable(Screen.WishListScreen.route) {
            WishListScreen(nav = navController)
        }
        composable(Screen.OrderScreen.route) {
            OrderScreen(nav = navController)
        }
        composable(Screen.AddNewAddress.route) {
            NewAddressScreen(navController = navController)
        }
    }
}



