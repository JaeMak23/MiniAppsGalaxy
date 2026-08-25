package com.jaemak23.miniappsgalaxy.core.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.jaemak23.miniappsgalaxy.core.ui.theme.palette.BluePalette

object BlueTheme : AppThemeFlavor {
    override val darkColors = darkColorScheme(
        primary = BluePalette.primaryDark,
        onPrimary = BluePalette.onPrimaryDark,
        primaryContainer = BluePalette.primaryContainerDark,
        onPrimaryContainer = BluePalette.onPrimaryContainerDark,
        secondary = BluePalette.secondaryDark,
        onSecondary = BluePalette.onSecondaryDark,
        secondaryContainer = BluePalette.secondaryContainerDark,
        onSecondaryContainer = BluePalette.onSecondaryContainerDark,
        error = BluePalette.errorDark,
        onError = BluePalette.onErrorDark,
        background = BluePalette.backgroundDark,
        onBackground = BluePalette.onBackgroundDark,
        surface = BluePalette.surfaceDark,
        onSurface = BluePalette.onSurfaceDark,
        surfaceVariant = BluePalette.surfaceVariantDark,
        onSurfaceVariant = BluePalette.onSurfaceVariantDark,
        outline = BluePalette.outlineDark,
        outlineVariant = BluePalette.outlineVariantDark,
    )

    override val lightColors = lightColorScheme(
        primary = BluePalette.primaryLight,
        onPrimary = BluePalette.onPrimaryLight,
        primaryContainer = BluePalette.primaryContainerLight,
        onPrimaryContainer = BluePalette.onPrimaryContainerLight,
        secondary = BluePalette.secondaryLight,
        onSecondary = BluePalette.onSecondaryLight,
        secondaryContainer = BluePalette.secondaryContainerLight,
        onSecondaryContainer = BluePalette.onSecondaryContainerLight,
        error = BluePalette.errorLight,
        onError = BluePalette.onErrorLight,
        background = BluePalette.backgroundLight,
        onBackground = BluePalette.onBackgroundLight,
        surface = BluePalette.surfaceLight,
        onSurface = BluePalette.onSurfaceLight,
        surfaceVariant = BluePalette.surfaceVariantLight,
        onSurfaceVariant = BluePalette.onSurfaceVariantLight,
        outline = BluePalette.outlineLight,
        outlineVariant = BluePalette.outlineVariantLight,
    )

    override val darkGradient: List<Color> = BluePalette.meshGradientDark
    override val lightGradient: List<Color> = BluePalette.meshGradientLight
}

