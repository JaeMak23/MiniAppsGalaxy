package com.jaemak23.miniappsgalaxy

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.jaemak23.miniappsgalaxy.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "MiniAppsGalaxy",
    ) {
        App()
    }
}