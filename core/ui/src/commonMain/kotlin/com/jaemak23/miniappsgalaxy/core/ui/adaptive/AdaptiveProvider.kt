package com.jaemak23.miniappsgalaxy.core.ui.adaptive

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color

@Composable
fun AdaptiveProvider(
    darkMode: MutableState<Boolean>,
    gradientTheme: List<Color>,
    content: @Composable () -> Unit
) {
    val adaptiveInfo = currentWindowAdaptiveInfoV2()
    val deviceWidth = getDevice(adaptiveInfo.windowSizeClass)

    CompositionLocalProvider(
        LocalWindowAdaptiveInfo provides adaptiveInfo,
        LocalDarkMode provides darkMode,
        LocalDeviceWidth provides deviceWidth,
        LocalMeshGradientTheme provides gradientTheme,
        content = content
    )
}

