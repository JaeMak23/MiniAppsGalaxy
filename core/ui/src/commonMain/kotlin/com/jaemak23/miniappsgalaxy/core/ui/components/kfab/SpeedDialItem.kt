package com.jaemak23.miniappsgalaxy.core.ui.components.kfab

import androidx.compose.ui.graphics.vector.ImageVector

data class SpeedDialItem(
    val label: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)