package com.farhansolih0009.miniproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.farhansolih0009.miniproject.ui.screen.AboutScreen
import com.farhansolih0009.miniproject.ui.screen.DashboardScreen
import com.farhansolih0009.miniproject.ui.screen.DetailScreen
import com.farhansolih0009.miniproject.ui.screen.KEY_ID_MAHASISWA
import com.farhansolih0009.miniproject.ui.screen.LoginScreen
import com.farhansolih0009.miniproject.ui.screen.LoginSuccessSplashScreen
import com.farhansolih0009.miniproject.ui.screen.MainScreen
import com.farhansolih0009.miniproject.ui.screen.ManageDataScreen
import com.farhansolih0009.miniproject.ui.screen.RegisterScreen
import com.farhansolih0009.miniproject.ui.screen.SplashScreen


@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(route = Screen.Register.route) {
            RegisterScreen(navController)
        }
        composable(route = Screen.LoginSuccessSplash.route) {
            LoginSuccessSplashScreen(navController)
        }
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.Dashboard.route) {
            DashboardScreen(navController)
        }
        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }
        composable(route = Screen.ManageData.route) { // Tambahkan ini
            ManageDataScreen(navController)
        }
        composable(route = Screen.FormBaru.route) {
            DetailScreen(navController)
        }
        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_MAHASISWA) { type = NavType.LongType }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_MAHASISWA)
            DetailScreen(navController, id)
        }
    }
}