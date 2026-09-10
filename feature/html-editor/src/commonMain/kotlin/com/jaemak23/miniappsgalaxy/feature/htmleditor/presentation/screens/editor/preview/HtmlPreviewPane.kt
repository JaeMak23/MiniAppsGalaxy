package com.jaemak23.miniappsgalaxy.feature.htmleditor.presentation.screens.editor.preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jaemak23.miniappsgalaxy.core.ui.components.webview.PlatformWebView

@Composable
fun HtmlPreviewPane(
    html: String,
    filePath: String?,
    modifier: Modifier = Modifier
) {
    PlatformWebView(html = html,filePath=filePath, modifier = modifier.fillMaxSize())
}