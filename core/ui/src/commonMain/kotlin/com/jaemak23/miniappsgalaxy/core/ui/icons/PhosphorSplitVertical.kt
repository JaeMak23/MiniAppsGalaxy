package com.jaemak23.miniappsgalaxy.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val PhosphorSplitVertical: ImageVector
get() {
    if (phosphorSplitVertical != null) return phosphorSplitVertical!!

    phosphorSplitVertical = ImageVector.Builder(
        name = "split-vertical",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 256f,
        viewportHeight = 256f
    ).apply {
        path(
            fill = SolidColor(Color.Black)
        ) {
            moveTo(216f, 152f)
            arcToRelative(8f, 8f, 0f, false, true, -8f, 8f)
            horizontalLineTo(136f)
            verticalLineToRelative(52.69f)
            lineToRelative(18.34f, -18.35f)
            arcToRelative(8f, 8f, 0f, false, true, 11.32f, 11.32f)
            lineToRelative(-32f, 32f)
            arcToRelative(8f, 8f, 0f, false, true, -11.32f, 0f)
            lineToRelative(-32f, -32f)
            arcToRelative(8f, 8f, 0f, false, true, 11.32f, -11.32f)
            lineTo(120f, 212.69f)
            verticalLineTo(160f)
            horizontalLineTo(48f)
            arcToRelative(8f, 8f, 0f, false, true, 0f, -16f)
            horizontalLineTo(208f)
            arcTo(8f, 8f, 0f, false, true, 216f, 152f)
            close()
            moveTo(48f, 112f)
            horizontalLineTo(208f)
            arcToRelative(8f, 8f, 0f, false, false, 0f, -16f)
            horizontalLineTo(136f)
            verticalLineTo(43.31f)
            lineToRelative(18.34f, 18.35f)
            arcToRelative(8f, 8f, 0f, false, false, 11.32f, -11.32f)
            lineToRelative(-32f, -32f)
            arcToRelative(8f, 8f, 0f, false, false, -11.32f, 0f)
            lineToRelative(-32f, 32f)
            arcToRelative(8f, 8f, 0f, false, false, 11.32f, 11.32f)
            lineTo(120f, 43.31f)
            verticalLineTo(96f)
            horizontalLineTo(48f)
            arcToRelative(8f, 8f, 0f, false, false, 0f, 16f)
            close()
        }
    }.build()

    return phosphorSplitVertical!!
}

private var phosphorSplitVertical: ImageVector? = null