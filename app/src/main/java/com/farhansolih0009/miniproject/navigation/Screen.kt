package com.farhansolih0009.miniproject.navigation

import com.farhansolih0009.miniproject.ui.screen.KEY_ID_MAHASISWA

sealed class Screen(val route: String) {
    // Tambahkan object baru untuk SplashScreen
    data object Splash : Screen("splashScreen")

    data object Home : Screen("mainScreen")
    data object FormBaru : Screen("detailScreen")
    data object FormUbah : Screen("detailScreen/{$KEY_ID_MAHASISWA}") {
        fun withId(id: Long) = "detailScreen/$id"
    }
}