package com.example.appwallpaper.ui_screens

import androidx.core.graphics.drawable.toBitmap
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallpaperDetailScreen(imageUrl: String) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Wallpaper") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = "Wallpaper",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { setWallpaper(context, imageUrl) }) {
                    Text("Set Wallpaper")
                }
                Button(onClick = { shareImage(context, imageUrl) }) {
                    Text("Share")
                }
            }
        }
    }
}

fun setWallpaper(context: Context, imageUrl: String) {
    val imageLoader = ImageLoader(context)
    val request = ImageRequest.Builder(context)
        .data(imageUrl)
        .allowHardware(false) // Ensure we get a software bitmap
        .build()

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val result = (imageLoader.execute(request) as? SuccessResult)?.drawable
            withContext(Dispatchers.Main) {
                if (result != null) {
                    val bitmap = result.toBitmap()
                    val wallpaperManager = WallpaperManager.getInstance(context)
                    wallpaperManager.setBitmap(bitmap)
                    Toast.makeText(context, "Wallpaper Set Successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed to load wallpaper!", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Error: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

fun shareImage(context: Context, imageUrl: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, imageUrl)
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share via"))
}
