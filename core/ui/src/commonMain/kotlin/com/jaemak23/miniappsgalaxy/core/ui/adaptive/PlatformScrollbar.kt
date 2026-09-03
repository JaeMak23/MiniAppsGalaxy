package com.jaemak23.miniappsgalaxy.core.ui.adaptive

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun PlatformScrollbar(
    scrollState: ScrollState,
    modifier: Modifier = Modifier
)