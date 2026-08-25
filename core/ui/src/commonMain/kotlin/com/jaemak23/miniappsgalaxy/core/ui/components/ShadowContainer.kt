package com.jaemak23.miniappsgalaxy.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Immutable
data class ShadowStyle(
    val innerPadding: PaddingValues = PaddingValues(10.dp),
    val outerPadding: Dp = 10.dp,
    val shape: Shape = RoundedCornerShape(50),
    val innerShape: Shape = shape,
    val shadowColor: Color = Color.Black.copy(alpha = 0.15f),
    val shadow: Shadow = Shadow(
        radius = 8.dp,
        color = shadowColor,
        offset = DpOffset(0.dp, 4.dp)
    ),
    val backgroundColor: Color = Color.Unspecified
)

@Composable
fun defaultShadowStyle(
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceContainerLow,
    shadowColor: Color = MaterialTheme.colorScheme.scrim.copy(alpha = 0.15f),
    shape: Shape = RoundedCornerShape(50),
    innerShape: Shape = shape,
    innerPadding: PaddingValues = PaddingValues(8.dp)
): ShadowStyle {
    return ShadowStyle(
        backgroundColor = backgroundColor,
        shape = shape,
        innerShape = innerShape,
        innerPadding = innerPadding,
        shadowColor = shadowColor
    )
}

fun Modifier.shadowContainerStyle(style: ShadowStyle): Modifier = this
    .padding(style.outerPadding)
    .dropShadow(shape = style.shape, shadow = style.shadow)
    .background(style.backgroundColor, style.shape)
    .clip(style.shape)
    .padding(style.innerPadding)
    .clip(style.innerShape)

@Composable
fun roundRectangleShadowStyle(): ShadowStyle {
    return defaultShadowStyle().copy(
        shape = RoundedCornerShape(10.dp),
        innerShape = RoundedCornerShape(4.dp),
        innerPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp)
    )
}

@Composable
fun ShadowBox(
    modifier: Modifier = Modifier,
    style: ShadowStyle = defaultShadowStyle(),
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    content: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier.shadowContainerStyle(style),
        contentAlignment = contentAlignment,
        propagateMinConstraints = propagateMinConstraints
    ) {
        content()
    }
}

@Composable
fun ShadowColumn(
    modifier: Modifier = Modifier,
    style: ShadowStyle = defaultShadowStyle(),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.shadowContainerStyle(style),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        content = content
    )
}

@Composable
fun ShadowRow(
    modifier: Modifier = Modifier,
    style: ShadowStyle = defaultShadowStyle(),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier.shadowContainerStyle(style),
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
        content = content
    )
}