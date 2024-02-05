package com.example.diaru.Navbar

import com.example.diaru.R

sealed class Screen(val route: String, val label: String, val icon: Int) {
    object Home : Screen("Home", "Home", R.drawable.ic_home)
    object Settings : Screen("Settings", "Settings", R.drawable.ic_settings)
}