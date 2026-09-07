package com.jaemak23.miniappsgalaxy.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val CodeEditIcon: ImageVector
    get() {
        if (codeIcon != null) return codeIcon!!

        codeIcon = ImageVector.Builder(
            name = "code-block-edit",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(3f, 6.25f)
                curveTo(3f, 4.45507f, 4.45507f, 3f, 6.25f, 3f)
                horizontalLineTo(17.75f)
                curveTo(19.5449f, 3f, 21f, 4.45507f, 21f, 6.25f)
                verticalLineTo(11.0121f)
                curveTo(20.4935f, 10.9686f, 19.9789f, 11.0422f, 19.5f, 11.2328f)
                verticalLineTo(6.25f)
                curveTo(19.5f, 5.2835f, 18.7165f, 4.5f, 17.75f, 4.5f)
                horizontalLineTo(6.25f)
                curveTo(5.2835f, 4.5f, 4.5f, 5.2835f, 4.5f, 6.25f)
                verticalLineTo(17.75f)
                curveTo(4.5f, 18.7165f, 5.2835f, 19.5f, 6.25f, 19.5f)
                horizontalLineTo(11.542f)
                curveTo(11.535f, 19.5255f, 11.5283f, 19.5511f, 11.5219f, 19.5768f)
                lineTo(11.1661f, 21f)
                horizontalLineTo(6.25f)
                curveTo(4.45507f, 21f, 3f, 19.5449f, 3f, 17.75f)
                verticalLineTo(6.25f)
                close()
                moveTo(10.5303f, 8.21967f)
                curveTo(10.8232f, 8.51256f, 10.8232f, 8.98744f, 10.5303f, 9.28033f)
                lineTo(7.81066f, 12f)
                lineTo(10.5303f, 14.7197f)
                curveTo(10.8232f, 15.0126f, 10.8232f, 15.4874f, 10.5303f, 15.7803f)
                curveTo(10.2374f, 16.0732f, 9.76256f, 16.0732f, 9.46967f, 15.7803f)
                lineTo(6.21967f, 12.5303f)
                curveTo(5.92678f, 12.2374f, 5.92678f, 11.7626f, 6.21967f, 11.4697f)
                lineTo(9.46967f, 8.21967f)
                curveTo(9.76256f, 7.92678f, 10.2374f, 7.92678f, 10.5303f, 8.21967f)
                close()
                moveTo(13.4697f, 8.21967f)
                curveTo(13.7626f, 7.92678f, 14.2374f, 7.92678f, 14.5303f, 8.21967f)
                lineTo(17.7803f, 11.4697f)
                curveTo(18.0732f, 11.7626f, 18.0732f, 12.2374f, 17.7803f, 12.5303f)
                lineTo(14.5303f, 15.7803f)
                curveTo(14.2374f, 16.0732f, 13.7626f, 16.0732f, 13.4697f, 15.7803f)
                curveTo(13.1768f, 15.4874f, 13.1768f, 15.0126f, 13.4697f, 14.7197f)
                lineTo(16.1893f, 12f)
                lineTo(13.4697f, 9.28033f)
                curveTo(13.1768f, 8.98744f, 13.1768f, 8.51256f, 13.4697f, 8.21967f)
                close()
                moveTo(19.0999f, 12.6695f)
                lineTo(13.1974f, 18.5719f)
                curveTo(12.8533f, 18.916f, 12.6092f, 19.3472f, 12.4911f, 19.8194f)
                lineTo(12.0334f, 21.6501f)
                curveTo(11.8344f, 22.4462f, 12.5556f, 23.1674f, 13.3517f, 22.9683f)
                lineTo(15.1824f, 22.5106f)
                curveTo(15.6545f, 22.3926f, 16.0857f, 22.1485f, 16.4299f, 21.8043f)
                lineTo(22.3323f, 15.9019f)
                curveTo(23.2249f, 15.0093f, 23.2249f, 13.5621f, 22.3323f, 12.6695f)
                curveTo(21.4397f, 11.7768f, 19.9925f, 11.7768f, 19.0999f, 12.6695f)
                close()
            }
        }.build()

        return codeIcon!!
    }

private var codeIcon: ImageVector? = null