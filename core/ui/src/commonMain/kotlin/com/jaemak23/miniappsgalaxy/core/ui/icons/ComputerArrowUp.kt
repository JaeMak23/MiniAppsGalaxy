package com.jaemak23.miniappsgalaxy.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val ComputerArrowUp: ImageVector
    get() {
        if (computerArrowUp != null) {
            return computerArrowUp!!
        }
        computerArrowUp =
            ImageVector.Builder(
                name = "computer_arrow_up",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color.Black),
                        fillAlpha = 1f,
                        stroke = null,
                        strokeAlpha = 1f,
                        strokeLineWidth = 1f,
                        strokeLineCap = StrokeCap.Butt,
                        strokeLineJoin = StrokeJoin.Bevel,
                        strokeLineMiter = 1f,
                        pathFillType = PathFillType.NonZero,
                    ) {
                        moveTo(1f, 21f)
                        verticalLineTo(19f)
                        horizontalLineTo(23f)
                        verticalLineToRelative(2f)
                        horizontalLineTo(1f)
                        close()
                        moveTo(4f, 18f)
                        quadTo(3.18f, 18f, 2.59f, 17.41f)
                        reflectiveQuadTo(2f, 16f)
                        verticalLineTo(5f)
                        quadTo(2f, 4.17f, 2.59f, 3.59f)
                        reflectiveQuadTo(4f, 3f)
                        horizontalLineTo(20f)
                        quadToRelative(0.83f, 0f, 1.41f, 0.59f)
                        reflectiveQuadTo(22f, 5f)
                        verticalLineTo(16f)
                        quadToRelative(0f, 0.82f, -0.59f, 1.41f)
                        reflectiveQuadTo(20f, 18f)
                        horizontalLineTo(4f)
                        close()
                        moveTo(4f, 16f)
                        horizontalLineTo(20f)
                        verticalLineTo(5f)
                        horizontalLineTo(4f)
                        verticalLineTo(16f)
                        close()
                        moveToRelative(0f, 0f)
                        verticalLineTo(5f)
                        verticalLineTo(16f)
                        close()
                        moveToRelative(7f, -1.5f)
                        horizontalLineToRelative(2f)
                        verticalLineTo(10.33f)
                        lineToRelative(1.6f, 1.57f)
                        lineTo(16f, 10.5f)
                        lineToRelative(-4f, -4f)
                        lineToRelative(-4f, 4f)
                        lineToRelative(1.4f, 1.42f)
                        lineTo(11f, 10.33f)
                        verticalLineTo(14.5f)
                        close()
                    }
                }
                .build()
        return computerArrowUp!!
    }

private var computerArrowUp: ImageVector? = null