package com.jaemak23.miniappsgalaxy.core.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

interface AppThemeFlavor {
    val darkColors: ColorScheme
    val lightColors: ColorScheme
    val darkGradient: List<Color>?
    val lightGradient: List<Color>?
}

/**
 * @sample : " flavor.colorScheme(darkMode.value) "
 **/
fun AppThemeFlavor.colorScheme(isDark: Boolean): ColorScheme =
    if (isDark) darkColors else lightColors

fun AppThemeFlavor.meshGradient(isDark: Boolean): List<Color> =
    (if (isDark) darkGradient else lightGradient)
        ?: listOf(colorScheme(isDark).background)