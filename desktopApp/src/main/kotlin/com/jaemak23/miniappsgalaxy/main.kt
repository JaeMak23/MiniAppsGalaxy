package com.jaemak23.miniappsgalaxy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.jaemak23.miniappsgalaxy.di.initKoin
import dev.datlag.kcef.KCEF
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import kotlin.math.max

fun main() {
    initKoin()
    application {
        var restartRequired by remember { mutableStateOf(false) }
        var downloading by remember { mutableStateOf(0F) }
        var initialized by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            withContext(Dispatchers.IO) {
                KCEF.init(
                    builder = {
                        installDir(File("kcef-bundle"))
                        progress {
                            onDownloading { downloading = max(it, 0F) }
                            onInitialized { initialized = true }
                        }
                        settings {
                            cachePath = File("cache").absolutePath
                        }
                    },
                    onError = { it?.printStackTrace() },
                    onRestartRequired = { restartRequired = true }
                )
            }
        }

        Window(onCloseRequest = ::exitApplication, title = "MiniAppsGalaxy") {
            if (restartRequired) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Restart required.")
                }
            } else if (initialized) {
                App()
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Downloading Chromium binaries: ${downloading.toInt()}%")
                }
            }
        }

        DisposableEffect(Unit) {
            onDispose {
                KCEF.disposeBlocking()
            }
        }
    }
}