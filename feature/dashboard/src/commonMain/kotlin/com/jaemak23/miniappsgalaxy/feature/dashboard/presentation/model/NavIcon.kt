package com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector

@Immutable
data class NavIcon(
    val selected: ImageVector,
    val unselected: ImageVector
)