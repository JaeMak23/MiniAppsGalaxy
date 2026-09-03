package com.jaemak23.miniappsgalaxy.core.ui.adaptive

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun PlatformScrollbar(
    scrollState: ScrollState,
    modifier: Modifier
) {
    // no-op — Android doesn't use a persistent visual scrollbar by convention
}