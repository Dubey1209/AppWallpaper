package com.example.appwallpaper.navigation  // ✅ Ensure package name is lowercase

sealed class Routes(val route: String) {  // ✅ Fixed typo `routes` → `route`

    object HomeScreen : Routes("home")
    object WallpaperDetailScreen : Routes("detail/{imageUrl}")  // ✅ Added missing screen

    fun withArgs(imageUrl: String): String {
        return "detail/$imageUrl"  // ✅ Function to pass image URL
    }
}
