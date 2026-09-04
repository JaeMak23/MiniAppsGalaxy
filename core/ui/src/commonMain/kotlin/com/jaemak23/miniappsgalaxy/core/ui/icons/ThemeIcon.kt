package com.jaemak23.miniappsgalaxy.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val ThemeIcon: ImageVector
    get() {
        if (themeIcon != null) return themeIcon!!

        themeIcon = ImageVector.Builder(
            name = "dark-theme",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(12f, 22f)
                curveTo(17.5228f, 22f, 22f, 17.5228f, 22f, 12f)
                curveTo(22f, 6.47715f, 17.5228f, 2f, 12f, 2f)
                curveTo(6.47715f, 2f, 2f, 6.47715f, 2f, 12f)
                curveTo(2f, 17.5228f, 6.47715f, 22f, 12f, 22f)
                close()
                moveTo(12f, 20.5f)
                verticalLineTo(3.5f)
                curveTo(16.6944f, 3.5f, 20.5f, 7.30558f, 20.5f, 12f)
                curveTo(20.5f, 16.6944f, 16.6944f, 20.5f, 12f, 20.5f)
                close()
            }
        }.build()

        return themeIcon!!
    }

private var themeIcon: ImageVector? = null