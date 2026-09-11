package com.jaemak23.miniappsgalaxy

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.jaemak23.miniappsgalaxy.di.initKoin
import io.github.vinceglb.filekit.FileKit

fun main() {
    initKoin()
    FileKit.init(appId = "com.jaemak23.miniappsgalaxy")

    application {
        Window(onCloseRequest = ::exitApplication, title = "MiniAppsGalaxy") {
            App()
        }
    }
}