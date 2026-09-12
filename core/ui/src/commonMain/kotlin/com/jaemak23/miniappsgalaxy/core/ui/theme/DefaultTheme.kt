package com.jaemak23.miniappsgalaxy.core.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.jaemak23.miniappsgalaxy.core.ui.theme.palette.BluePalette
import com.jaemak23.miniappsgalaxy.core.ui.theme.palette.DefaultPalette

object DefaultTheme : AppThemeFlavor {
    override val darkColors = darkColorScheme(
        primary = DefaultPalette.primaryDark,
        onPrimary = DefaultPalette.onPrimaryDark,
        primaryContainer = DefaultPalette.primaryContainerDark,
        onPrimaryContainer = DefaultPalette.onPrimaryContainerDark,

        secondary = DefaultPalette.secondaryDark,
        onSecondary = DefaultPalette.onSecondaryDark,
        secondaryContainer = DefaultPalette.secondaryContainerDark,
        onSecondaryContainer = DefaultPalette.onSecondaryContainerDark,

        tertiary = DefaultPalette.tertiaryDark,
        onTertiary = DefaultPalette.onTertiaryDark,
        tertiaryContainer = DefaultPalette.tertiaryContainerDark,
        onTertiaryContainer = DefaultPalette.onTertiaryContainerDark,

        error = DefaultPalette.errorDark,
        onError = DefaultPalette.onErrorDark,
        errorContainer = DefaultPalette.errorContainerDark,
        onErrorContainer = DefaultPalette.onErrorContainerDark,

        outline = DefaultPalette.outline,
//        outlineVariant = DefaultPalette.outlineVariant,

        background = DefaultPalette.backgroundDark,
        onBackground = DefaultPalette.onBackgroundDark,

        surface = DefaultPalette.surfaceDark,
        onSurface = DefaultPalette.onSurfaceDark,
        surfaceVariant = DefaultPalette.surfaceVariantDark,
    )

    override val lightColors = lightColorScheme(
        primary = DefaultPalette.primary,
        onPrimary = DefaultPalette.onPrimary,
        primaryContainer = DefaultPalette.primaryContainer,
        onPrimaryContainer = DefaultPalette.onPrimaryContainer,

        secondary = DefaultPalette.secondary,
        onSecondary = DefaultPalette.onSecondary,
        secondaryContainer = DefaultPalette.secondaryContainer,
        onSecondaryContainer = DefaultPalette.onSecondaryContainer,

        tertiary = DefaultPalette.tertiary,
        onTertiary = DefaultPalette.onTertiary,
        tertiaryContainer = DefaultPalette.tertiaryContainer,
        onTertiaryContainer = DefaultPalette.onTertiaryContainer,

        error = DefaultPalette.error,
        onError = DefaultPalette.onError,
        errorContainer = DefaultPalette.errorContainer,
        onErrorContainer = DefaultPalette.onErrorContainer,

        outline = DefaultPalette.outline,
//        outlineVariant = DefaultPalette.outlineVariant,

        background = DefaultPalette.background,
        onBackground = DefaultPalette.onBackground,

        surface = DefaultPalette.surface,
        onSurface = DefaultPalette.onSurface,
        surfaceVariant = DefaultPalette.surfaceVariant,
    )

    override val darkGradient: List<Color> = BluePalette.meshGradientDark
    override val lightGradient: List<Color> = BluePalette.meshGradientLight

}