package com.jaemak23.miniappsgalaxy.core.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.jaemak23.miniappsgalaxy.core.ui.theme.palette.OrangePalette

object OrangeTheme : AppThemeFlavor {
    override val darkColors =
        darkColorScheme(
            primary = OrangePalette.primaryDark,
            onPrimary = OrangePalette.onPrimaryDark,
            secondary = OrangePalette.secondaryDark,
            background = OrangePalette.backgroundDark,
            surface = OrangePalette.surfaceDark
        )

    override val lightColors =
        lightColorScheme(
            primary = OrangePalette.primaryLight,
            onPrimary = OrangePalette.onPrimaryLight,
            secondary = OrangePalette.secondaryLight,
            background = OrangePalette.backgroundLight,
            surface = OrangePalette.surfaceLight,
        )

    override val darkGradient: List<Color> = OrangePalette.meshGradientDark
    override val lightGradient: List<Color> = OrangePalette.meshGradientLight
}