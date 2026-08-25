package com.jaemak23.miniappsgalaxy.core.ui.theme.palette

import androidx.compose.ui.graphics.Color

internal object OrangePalette {
    val primaryLight = Color(0xFF8E2E14)
    val primaryDark = Color(0xFFEC4B20)

    val onPrimaryLight = Color(0xFFFFFFFF)
    val onPrimaryDark = Color(0xFFFFFFFF)

    val secondaryDark = Color(0xFF38B17B)
    val secondaryLight = Color(0xFF38B17B)

    val backgroundLight = Color(0xFFF8F9FA)
    val backgroundDark = Color(0xFF121212)

    val surfaceLight = Color(0xFFFFFFFF)
    val surfaceDark = Color(0xFF1E1E1E)

    // Mesh Gradient
    val meshGradientLight = listOf(
        Color(0xFFFD435D), // Light Cyan
        Color(0xFFFD464C), // Light Green
        Color(0xFFFD416A), // Light Orange
        Color(0xFFFD416A), // Light Purple
        Color(0xFFFD464C)  // Light Blue
    )

    val meshGradientDark = listOf(
        Color(0xFF892432), // Light Cyan
        Color(0xFF5E1A1C), // Light Green
        Color(0xFF7D2236), // Light Orange
        Color(0xFF721D30), // Light Purple
        Color(0xFF57181A)  // Light Blue
    )
}