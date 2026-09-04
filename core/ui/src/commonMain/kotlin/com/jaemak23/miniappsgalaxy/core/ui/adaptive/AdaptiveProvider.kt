package com.jaemak23.miniappsgalaxy.core.ui.adaptive

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import com.jaemak23.miniappsgalaxy.core.ui.theme.ThemeManager

@Composable
fun AdaptiveProvider(
    darkMode: MutableState<Boolean>,
    themeFlavor: MutableState<ThemeManager>,
    gradientTheme: List<Color>,
    snackbarHostState: SnackbarHostState,
    content: @Composable () -> Unit
) {
    val adaptiveInfo = currentWindowAdaptiveInfoV2()
    val deviceWidth = getDevice(adaptiveInfo.windowSizeClass)

    CompositionLocalProvider(
        LocalWindowAdaptiveInfo provides adaptiveInfo,
        LocalDarkMode provides darkMode,
        LocalThemeFlavor provides themeFlavor,
        LocalDeviceWidth provides deviceWidth,
        LocalMeshGradientTheme provides gradientTheme,
        LocalSnackbarHostState provides snackbarHostState,
        content = content
    )
}

