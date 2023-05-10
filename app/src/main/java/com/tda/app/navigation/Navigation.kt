package com.tda.app


import VerificationScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tda.app.view.*
import com.tda.app.navigation.Screen

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
//        composable(Screen.PopularListScreen.route) {
//            PopularListScreen(navController = navController)
//        }
//        composable(Screen.DetailsScreen.route) {
//            FlowerDetailsScreen(navController = navController)
//        }
//        composable(Screen.AddToCartScreen.route) {
//            CheckoutScreen()
//        }


    }
}