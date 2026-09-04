package com.jaemak23.miniappsgalaxy.core.ui.components.composeeditorkit

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
 fun LineNumberGutter(
    textLayoutResult: TextLayoutResult?,
    scrollState: ScrollState,
    modifier: Modifier = Modifier
) {
    val gutterColor = MaterialTheme.colorScheme.onSurfaceVariant
    val lineCount = textLayoutResult?.lineCount ?: 1

    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .verticalScroll(scrollState)
            .padding(vertical = VERTICAL_PADDING, horizontal = 8.dp)
    ) {
        Layout(
            content = {
                for (lineIndex in 0 until lineCount) {
                    Text(
                        text = (lineIndex + 1).toString(),
                        color = gutterColor,
                        fontFamily = FontFamily.Monospace,
                        fontSize = EDITOR_FONT_SIZE,
                        textAlign = TextAlign.End
                    )
                }
            }
        ) { measurables, constraints ->
            val placeable = measurables.map { it.measure(constraints.copy(minWidth = 0)) }
            val totalHeight = textLayoutResult?.let { it.getLineBottom(it.lineCount - 1) }?.toInt()
                ?: (EDITOR_LINE_HEIGHT.toPx() * lineCount).toInt()

            layout(constraints.maxWidth, totalHeight) {
                placeable.forEachIndexed { index, placeable ->
                    val top = textLayoutResult?.getLineTop(index)?.toInt()
                        ?: (index * EDITOR_LINE_HEIGHT.toPx()).toInt()
                    placeable.placeRelative(
                        x = constraints.maxWidth - placeable.width,
                        y = top
                    )
                }
            }
        }
    }
}