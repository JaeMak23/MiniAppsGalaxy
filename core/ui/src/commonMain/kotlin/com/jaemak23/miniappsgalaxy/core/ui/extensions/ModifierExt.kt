package com.jaemak23.miniappsgalaxy.core.ui.extensions

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.maxReadableWidth(maxWidth: Dp = 500.dp): Modifier = this
    .widthIn(max = maxWidth)
    .fillMaxWidth()