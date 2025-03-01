package com.example.appwallpaper.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WallpaperRepository {
    fun getWallpapers(): Flow<List<Wallpaper>> = flow {
        val wallpapers = listOf(
            Wallpaper("1", "https://picsum.photos/300/500?random=1"),
            Wallpaper("2", "https://picsum.photos/300/500?random=2"),
            Wallpaper("3", "https://picsum.photos/300/500?random=3"),
            Wallpaper("4", "https://picsum.photos/300/500?random=4")
        )
        delay(1000) //
        emit(wallpapers)
    }
}
