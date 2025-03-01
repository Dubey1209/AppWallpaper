package com.example.appwallpaper.navigation  // ✅ Ensure package name is lowercase

sealed class Routes(val route: String) {  // ✅ Fixed typo `routes` → `route`

    object Routes {
        val HomeScreen = Screen("home")
        val WallpaperDetailScreen = Screen("wallpaperDetail/{imageUrl}")
    }

    data class Screen(val route: String)

}
