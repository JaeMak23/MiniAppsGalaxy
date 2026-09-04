package com.jaemak23.miniappsgalaxy.core.ui.components.composeeditorkit

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * orientation = Vertical  -> handle is a horizontal bar, drags up/down (Column split)
 * orientation = Horizontal -> handle is a vertical bar, drags left/right (Row split)
 */
@Composable
fun SplitDivider(
    orientation: Orientation,
    onDrag: (deltaPx: Float) -> Unit,
    modifier: Modifier = Modifier
) {
    val dragState = rememberDraggableState { delta -> onDrag(delta) }
    val handleModifier = if (orientation == Orientation.Vertical) {
        modifier.fillMaxWidth().height(8.dp)
    } else {
        modifier.fillMaxHeight().width(8.dp)
    }

    Box(
        modifier = handleModifier.draggable(state = dragState, orientation = orientation),
        contentAlignment = Alignment.Center
    ) {
        if (orientation == Orientation.Vertical) {
            HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp, color = MaterialTheme.colorScheme.outline)
        } else {
            VerticalDivider(modifier = Modifier.fillMaxHeight(), thickness = 2.dp, color = MaterialTheme.colorScheme.outline)
        }
    }
}