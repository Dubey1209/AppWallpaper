package com.example.appwallpaper.ui_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.appwallpaper.viewmodel.WallpaperViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onWallpaperClick: (String) -> Unit) {
    val viewModel: WallpaperViewModel = viewModel()
    val wallpapers by viewModel.wallpapers.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Wallpapers") }
            )
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(120.dp),
            modifier = Modifier.padding(padding)
        ) {
            items(wallpapers.size) { index ->
                val wallpaper = wallpapers[index]
                Image(
                    painter = rememberAsyncImagePainter(wallpaper.imageUrl),
                    contentDescription = "Wallpaper",
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .clickable { onWallpaperClick(wallpaper.imageUrl) },
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
