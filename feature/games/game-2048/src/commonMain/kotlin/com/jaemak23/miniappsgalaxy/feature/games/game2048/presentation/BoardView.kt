package com.jaemak23.miniappsgalaxy.feature.games.game2048.presentation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.feature.games.game2048.domain.model.Board
import com.jaemak23.miniappsgalaxy.feature.games.game2048.domain.model.Direction
import com.jaemak23.miniappsgalaxy.feature.games.game2048.domain.model.Tile
import kotlin.math.abs

private const val SWIPE_THRESHOLD = 40f

@Composable
fun BoardView(
    board: Board,
    onSwipe: (Direction) -> Unit,
    modifier: Modifier = Modifier,
) {
    var dragOffsetX by remember { mutableFloatStateOf(0f) }
    var dragOffsetY by remember { mutableFloatStateOf(0f) }

    BoxWithConstraints(
        modifier = modifier
            .widthIn(max=400.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = {
                        dragOffsetX = 0f
                        dragOffsetY = 0f
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        dragOffsetX += dragAmount.x
                        dragOffsetY += dragAmount.y
                    },
                    onDragEnd = {
                        val absX = abs(dragOffsetX)
                        val absY = abs(dragOffsetY)
                        if (maxOf(absX, absY) > SWIPE_THRESHOLD) {
                            val direction = if (absX > absY) {
                                if (dragOffsetX > 0) Direction.RIGHT else Direction.LEFT
                            } else {
                                if (dragOffsetY > 0) Direction.DOWN else Direction.UP
                            }
                            onSwipe(direction)
                        }
                        dragOffsetX = 0f
                        dragOffsetY = 0f
                    },
                )
            }
            .padding(8.dp),
    ) {
        val cellSize = maxWidth / board.size

        // Empty grid background cells
        for (row in 0 until board.size) {
            for (col in 0 until board.size) {
                Box(
                    modifier = Modifier
                        .offset(x = cellSize * col, y = cellSize * row)
                        .size(cellSize)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(MaterialTheme.colorScheme.surface),
                )
            }
        }

        // Tiles, keyed by id so Compose animates position/value changes per-tile
        for (tile in board.tiles) {
            key(tile.id) {
                TileView(
                    tile = tile,
                    cellSize = cellSize,
                )
            }
        }
    }
}

@Composable
private fun TileView(tile: Tile, cellSize: Dp) {
    val animatedOffsetX by animateDpAsState(
        targetValue = cellSize * tile.col,
        label = "tileOffsetX",
    )
    val animatedOffsetY by animateDpAsState(
        targetValue = cellSize * tile.row,
        label = "tileOffsetY",
    )
    val scale by animateFloatAsState(
        targetValue = if (tile.isNew) 1f else 1f,
        label = "tileScale",
    )

    Box(
        modifier = Modifier
            .offset(x = animatedOffsetX, y = animatedOffsetY)
            .size(cellSize)
            .padding(4.dp)
            .graphicsLayer { scaleX = scale; scaleY = scale }
            .clip(RoundedCornerShape(6.dp))
            .background(tileColor(tile.value)),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = tile.value.toString(),
            style = MaterialTheme.typography.titleLarge,
            color = tileTextColor(tile.value),
        )
    }
}

private fun tileColor(value: Int): Color = when (value) {
    2 -> Color(0xFFEEE4DA)
    4 -> Color(0xFFEDE0C8)
    8 -> Color(0xFFF2B179)
    16 -> Color(0xFFF59563)
    32 -> Color(0xFFF67C5F)
    64 -> Color(0xFFF65E3B)
    128 -> Color(0xFFEDCF72)
    256 -> Color(0xFFEDCC61)
    512 -> Color(0xFFEDC850)
    1024 -> Color(0xFFEDC53F)
    2048 -> Color(0xFFEDC22E)
    else -> Color(0xFF3C3A32)
}

private fun tileTextColor(value: Int): Color =
    if (value <= 4) Color(0xFF776E65) else Color.White