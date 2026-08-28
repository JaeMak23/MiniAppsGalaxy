package com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.model

import androidx.compose.runtime.Composable

data class TabItem(
    val selected: Boolean,
    val icon: @Composable (() -> Unit),
    val label: @Composable (() -> Unit)
)