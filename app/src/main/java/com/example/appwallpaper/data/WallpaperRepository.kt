package com.example.appwallpaper.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WallpaperRepository {
    // Simulated API call (1 sec delay)
    fun getWallpapers(): Flow<List<Wallpaper>> = flow {
        val wallpapers = listOf(
            Wallpaper("1", "https://source.unsplash.com/random/300x500/?wallpaper"),
            Wallpaper("2", "https://source.unsplash.com/random/300x500/?nature"),
            Wallpaper("3", "https://source.unsplash.com/random/300x500/?abstract"),
            Wallpaper("4", "https://source.unsplash.com/random/300x500/?technology")
        )
        delay(1000) // Simulating network delay
        emit(wallpapers)
    }
}
