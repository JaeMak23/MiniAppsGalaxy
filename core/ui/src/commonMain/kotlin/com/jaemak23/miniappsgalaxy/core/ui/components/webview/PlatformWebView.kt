package com.jaemak23.miniappsgalaxy.core.ui.components.webview

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun PlatformWebView(
    html: String,
    filePath: String?,
    modifier: Modifier = Modifier
)