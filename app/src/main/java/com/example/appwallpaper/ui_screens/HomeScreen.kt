package com.example.appwallpaper.ui_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.appwallpaper.viewmodel.WallpaperViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: androidx.navigation.NavController) {  // ✅ Fix parameter type
    val viewModel: WallpaperViewModel = viewModel()
    val wallpapers by viewModel.wallpapers.collectAsStateWithLifecycle()  // ✅ Corrected data collection

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Wallpapers") })
        }
    ) { padding ->
        if (wallpapers.isEmpty()) {  // ✅ Show loading state
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                modifier = Modifier.padding(padding)
            ) {
                items(wallpapers) { wallpaper ->  // ✅ Corrected items usage
                    Image(
                        painter = rememberAsyncImagePainter(wallpaper.imageUrl),
                        contentDescription = "Wallpaper",
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                            .clickable { navController.navigate("wallpaperDetail/${wallpaper.imageUrl}") },
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}
