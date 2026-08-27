package com.jaemak23.miniappsgalaxy.core.ui.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter


val defaultMeshGradientPainterColors = listOf(
    Color(0xFF6200EE), // Deep Purple
    Color(0xFF03DAC6), // Teal
    Color(0xFFBB86FC), // Light Purple
    Color(0xFF3700B3), // Dark Purple
    Color(0xFF018786)  // Dark Teal
)

/**
 * A custom Painter that draws a mesh-like gradient using multiple overlapping radial gradients.
 * This provides a smooth, fluid background effect similar to those found in modern iOS/macOS UIs.
 *
 * @param colors The list of colors to use for the gradient. At least 4-5 colors recommended.
 * @author Jaison Macklin Menezes.
 */
class MeshGradientPainter(private val colors: List<Color> = defaultMeshGradientPainterColors) :
    Painter() {
    override val intrinsicSize: Size = Size.Unspecified

    override fun DrawScope.onDraw() {
        if (colors.isEmpty()) return

        // Fill background with the first color
        drawRect(color = colors[0])

        // Draw overlapping radial gradients to create the mesh effect
        if (colors.size > 1) {
            // Top Left
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(colors[1], Color.Transparent),
                    center = Offset(0f, 0f),
                    radius = size.minDimension * 0.8f
                ),
                radius = size.minDimension * 0.8f,
                center = Offset(0f, 0f)
            )
        }

        if (colors.size > 2) {
            // Top Right
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(colors[2], Color.Transparent),
                    center = Offset(size.width, 0f),
                    radius = size.minDimension * 0.9f
                ),
                radius = size.minDimension * 0.9f,
                center = Offset(size.width, 0f)
            )
        }

        if (colors.size > 3) {
            // Bottom Right
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(colors[3], Color.Transparent),
                    center = Offset(size.width, size.height),
                    radius = size.minDimension * 1.0f
                ),
                radius = size.minDimension * 1.0f,
                center = Offset(size.width, size.height)
            )
        }

        if (colors.size > 4) {
            // Bottom Left
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(colors[4], Color.Transparent),
                    center = Offset(0f, size.height),
                    radius = size.minDimension * 0.7f
                ),
                radius = size.minDimension * 0.7f,
                center = Offset(0f, size.height)
            )
        }

        if (colors.size > 5) {
            // Center
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(colors[5], Color.Transparent),
                    center = Offset(size.width * 0.5f, size.height * 0.5f),
                    radius = size.minDimension * 0.6f
                ),
                radius = size.minDimension * 0.6f,
                center = Offset(size.width * 0.5f, size.height * 0.5f)
            )
        }
    }
}
