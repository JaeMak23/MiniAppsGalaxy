package com.jaemak23.miniappsgalaxy.core.ui.components.composeeditorkit

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged

@Composable
fun SplitPanes(
    ratio: Float,
    onDragRatio: (Float) -> Unit,
    isCompact: Boolean,
    startOrTop: @Composable () -> Unit,
    endOrBottom: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isCompact) {
        var containerHeightPx by remember { mutableIntStateOf(0) }
        Column(
            modifier = modifier.fillMaxSize().onSizeChanged { containerHeightPx = it.height }
        ) {
            Box(Modifier.weight(ratio.coerceIn(0.01f, 0.99f)).fillMaxHeight()) { startOrTop() }
            SplitDivider(
                orientation = Orientation.Vertical,
                onDrag = { deltaPx ->
                    if (containerHeightPx > 0) onDragRatio(ratio + deltaPx / containerHeightPx)
                }
            )
            Box(Modifier.weight((1f - ratio).coerceIn(0.01f, 0.99f)).fillMaxWidth()) {
                endOrBottom()
            }
        }
    } else {
        var containerWidthPx by remember { mutableIntStateOf(0) }
        Row(
            modifier = Modifier.fillMaxSize().onSizeChanged { containerWidthPx = it.width }
        ) {
            Box(Modifier.weight(ratio.coerceIn(0.01f, 0.99f)).fillMaxHeight()) { startOrTop() }
            SplitDivider(
                orientation = Orientation.Horizontal,
                onDrag = { deltaPx ->
                    if (containerWidthPx > 0) onDragRatio(ratio + deltaPx / containerWidthPx)
                }
            )
            Box(Modifier.weight((1f - ratio).coerceIn(0.01f, 0.99f)).fillMaxHeight()) {
                endOrBottom()
            }
        }
    }
}