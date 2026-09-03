package com.jaemak23.miniappsgalaxy.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val PhosphorSplitHorizontal: ImageVector
    get() {
        if (phosphorSplitHorizontal != null) return phosphorSplitHorizontal!!

        phosphorSplitHorizontal = ImageVector.Builder(
            name = "split-horizontal",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 256f,
            viewportHeight = 256f
        ).apply {
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(112f, 48f)
                verticalLineTo(208f)
                arcToRelative(8f, 8f, 0f, false, true, -16f, 0f)
                verticalLineTo(136f)
                horizontalLineTo(43.31f)
                lineToRelative(18.35f, 18.34f)
                arcToRelative(8f, 8f, 0f, false, true, -11.32f, 11.32f)
                lineToRelative(-32f, -32f)
                arcToRelative(8f, 8f, 0f, false, true, 0f, -11.32f)
                lineToRelative(32f, -32f)
                arcToRelative(8f, 8f, 0f, false, true, 11.32f, 11.32f)
                lineTo(43.31f, 120f)
                horizontalLineTo(96f)
                verticalLineTo(48f)
                arcToRelative(8f, 8f, 0f, false, true, 16f, 0f)
                close()
                moveToRelative(125.66f, 74.34f)
                lineToRelative(-32f, -32f)
                arcToRelative(8f, 8f, 0f, false, false, -11.32f, 11.32f)
                lineTo(212.69f, 120f)
                horizontalLineTo(160f)
                verticalLineTo(48f)
                arcToRelative(8f, 8f, 0f, false, false, -16f, 0f)
                verticalLineTo(208f)
                arcToRelative(8f, 8f, 0f, false, false, 16f, 0f)
                verticalLineTo(136f)
                horizontalLineToRelative(52.69f)
                lineToRelative(-18.35f, 18.34f)
                arcToRelative(8f, 8f, 0f, false, false, 11.32f, 11.32f)
                lineToRelative(32f, -32f)
                arcTo(8f, 8f, 0f, false, false, 237.66f, 122.34f)
                close()
            }
        }.build()

        return phosphorSplitHorizontal!!
    }

private var phosphorSplitHorizontal: ImageVector? = null