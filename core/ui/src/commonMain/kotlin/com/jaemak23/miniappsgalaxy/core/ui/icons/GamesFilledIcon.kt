package com.jaemak23.miniappsgalaxy.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val GamesFilledIcon: ImageVector
    get() {
        if (gamesIcon != null) return gamesIcon!!

        gamesIcon = ImageVector.Builder(
            name = "games",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(14.9979f, 5f)
                curveTo(18.8639f, 5f, 21.9979f, 8.13401f, 21.9979f, 12f)
                curveTo(21.9979f, 15.7855f, 18.9931f, 18.8691f, 15.2385f, 18.9959f)
                lineTo(14.9979f, 19f)
                horizontalLineTo(9.00211f)
                curveTo(5.13611f, 19f, 2.00211f, 15.866f, 2.00211f, 12f)
                curveTo(2.00211f, 8.21455f, 5.00689f, 5.1309f, 8.76146f, 5.00406f)
                lineTo(9.00211f, 5f)
                horizontalLineTo(14.9979f)
                close()
                moveTo(14.75f, 12.5f)
                curveTo(14.0596f, 12.5f, 13.5f, 13.0596f, 13.5f, 13.75f)
                curveTo(13.5f, 14.4404f, 14.0596f, 15f, 14.75f, 15f)
                curveTo(15.4403f, 15f, 16f, 14.4404f, 16f, 13.75f)
                curveTo(16f, 13.0596f, 15.4403f, 12.5f, 14.75f, 12.5f)
                close()
                moveTo(7.99999f, 9f)
                curveTo(7.62029f, 9f, 7.3065f, 9.28215f, 7.25684f, 9.64823f)
                lineTo(7.24999f, 9.75f)
                verticalLineTo(11.248f)
                lineTo(5.74999f, 11.2487f)
                curveTo(5.33578f, 11.2487f, 4.99999f, 11.5845f, 4.99999f, 11.9987f)
                curveTo(4.99999f, 12.3784f, 5.28214f, 12.6922f, 5.64822f, 12.7419f)
                lineTo(5.74999f, 12.7487f)
                lineTo(7.24999f, 12.748f)
                verticalLineTo(14.25f)
                curveTo(7.24999f, 14.6642f, 7.58578f, 15f, 7.99999f, 15f)
                curveTo(8.37969f, 15f, 8.69348f, 14.7178f, 8.74314f, 14.3518f)
                lineTo(8.74999f, 14.25f)
                verticalLineTo(12.748f)
                lineTo(10.25f, 12.7487f)
                curveTo(10.6642f, 12.7487f, 11f, 12.413f, 11f, 11.9987f)
                curveTo(11f, 11.6191f, 10.7178f, 11.3053f, 10.3518f, 11.2556f)
                lineTo(10.25f, 11.2487f)
                lineTo(8.74999f, 11.248f)
                verticalLineTo(9.75f)
                curveTo(8.74999f, 9.33579f, 8.4142f, 9f, 7.99999f, 9f)
                close()
                moveTo(16.75f, 9f)
                curveTo(16.0596f, 9f, 15.5f, 9.55964f, 15.5f, 10.25f)
                curveTo(15.5f, 10.9404f, 16.0596f, 11.5f, 16.75f, 11.5f)
                curveTo(17.4403f, 11.5f, 18f, 10.9404f, 18f, 10.25f)
                curveTo(18f, 9.55964f, 17.4403f, 9f, 16.75f, 9f)
                close()
            }
        }.build()

        return gamesIcon!!
    }

private var gamesIcon: ImageVector? = null