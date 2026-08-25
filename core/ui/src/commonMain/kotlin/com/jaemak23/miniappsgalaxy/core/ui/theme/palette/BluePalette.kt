package com.jaemak23.miniappsgalaxy.core.ui.theme.palette

import androidx.compose.ui.graphics.Color

internal object BluePalette {
    // Light Theme Colors
    val primaryLight = Color(0xFF0B57D0)
    val onPrimaryLight = Color(0xFFFFFFFF)
    val primaryContainerLight = Color(0xFFD3E3FD)
    val onPrimaryContainerLight = Color(0xFF041E49)

    val secondaryLight = Color(0xFF535F70) // Neutral secondary for balanced accents
    val onSecondaryLight = Color(0xFFFFFFFF)
    val secondaryContainerLight = Color(0xFFD7E3F8)
    val onSecondaryContainerLight = Color(0xFF101C2B)

    val errorLight = Color(0xFFB3261E)
    val onErrorLight = Color(0xFFFFFFFF)

    val backgroundLight = Color(0xFFFFFFFF)
    val onBackgroundLight = Color(0xFF1F1F1F)
    val surfaceLight = Color(0xFFFFFFFF)
    val onSurfaceLight = Color(0xFF1F1F1F)

    val surfaceVariantLight = Color(0xFFF0F4F9) // The light gray for search/inputs
    val onSurfaceVariantLight = Color(0xFF444746) // Placeholder text
    val outlineLight = Color(0xFFC4C7C5) // Subtle borders
    val outlineVariantLight = Color(0xFFE0E2E0)

    // Dark Theme Colors
    val primaryDark = Color(0xFFA8C7FA)
    val onPrimaryDark = Color(0xFF002D69)
    val primaryContainerDark = Color(0xFF00419C)
    val onPrimaryContainerDark = Color(0xFFD3E3FD)

    val secondaryDark = Color(0xFFBBC7DB)
    val onSecondaryDark = Color(0xFF253140)
    val secondaryContainerDark = Color(0xFF3B4858)
    val onSecondaryContainerDark = Color(0xFFD7E3F8)

    val errorDark = Color(0xFFFFB4AB)
    val onErrorDark = Color(0xFF690005)

    val backgroundDark = Color(0xFF131314) // Standard Google dark surface
    val onBackgroundDark = Color(0xFFE3E3E3)
    val surfaceDark = Color(0xFF131314)
    val onSurfaceDark = Color(0xFFE3E3E3)

    val surfaceVariantDark = Color(0xFF444746) // Dark gray for search/inputs
    val onSurfaceVariantDark = Color(0xFFC4C7C5)
    val outlineDark = Color(0xFF8E918F)
    val outlineVariantDark = Color(0xFF444746)

    // Mesh Gradient
    val meshGradientLight = listOf(
        Color(0xFFFFFFFF),
        Color(0xFFB3D6FF),
        Color(0xFF6E88A7),
        Color(0xFFB3B3B3)
    )

    val meshGradientDark = listOf(
        Color(0xFF00050E),
        Color(0xFF061223),
        Color(0xFF0A2552),
        Color(0xFF04070C)
    )
}


