package com.jaemak23.miniappsgalaxy.core.ui.components.composeeditorkit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.panelBorder(
    cornerRadius: Dp = 8.dp,
    borderColor: Color? = null,
    borderWidth: Dp = 1.dp
): Modifier = composed {
    val color = borderColor ?: MaterialTheme.colorScheme.outlineVariant
    this
        .clip(RoundedCornerShape(cornerRadius))
        .border(BorderStroke(borderWidth, color), RoundedCornerShape(cornerRadius))
}