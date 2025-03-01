package com.example.appwallpaper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appwallpaper.data.Wallpaper
import com.example.appwallpaper.data.WallpaperRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WallpaperViewModel : ViewModel() {
    private val repository = WallpaperRepository()

    private val _wallpapers = MutableStateFlow<List<Wallpaper>>(emptyList())
    val wallpapers: StateFlow<List<Wallpaper>> = _wallpapers

    init {
        fetchWallpapers()
    }

    private fun fetchWallpapers() {
        viewModelScope.launch {
            repository.getWallpapers().collect { wallpaperList ->
                println("DEBUG: Wallpapers Fetched -> $wallpaperList") // ✅ Add Debug Log
                _wallpapers.value = wallpaperList
            }
        }
    }

}
