package com.jaemak23.miniappsgalaxy.core.ui.adaptive

import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.window.core.layout.WindowSizeClass
import com.jaemak23.miniappsgalaxy.core.ui.theme.ThemeManager

val LocalWindowAdaptiveInfo = compositionLocalOf<WindowAdaptiveInfo> {
    error("No LocalWindowAdaptiveInfo provided")
}

val LocalDarkMode = compositionLocalOf<MutableState<Boolean>> {
    error("No LocalDarkMode provided")
}

val LocalThemeFlavor = compositionLocalOf<MutableState<ThemeManager>> {
    error("No LocalThemeFlavor provided")
}

val LocalDeviceWidth = compositionLocalOf { DeviceSize.PHONE }

val LocalMeshGradientTheme = compositionLocalOf<List<Color>> {
    error("No LocalMeshGradientTheme provided")
}

enum class DeviceSize {
    DESKTOP,
    TABLET,
    FOLDABLE,
    PHONE
}

fun getDevice(wsc: WindowSizeClass): DeviceSize {
    return when {
        wsc.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_LARGE_LOWER_BOUND) -> DeviceSize.DESKTOP
        wsc.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> DeviceSize.TABLET
        wsc.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> DeviceSize.FOLDABLE
        else -> DeviceSize.PHONE
    }
}

val isCompact: Boolean
    @Composable get() = LocalDeviceWidth.current == DeviceSize.PHONE