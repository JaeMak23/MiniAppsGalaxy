package com.jaemak23.miniappsgalaxy.core.ui.components.webview

import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
actual fun PlatformWebView(
    html: String,
    filePath: String?,
    modifier: Modifier
) {
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = false
            }
        },
        update = { webView ->
            webView.loadDataWithBaseURL(
                /* baseUrl = */ null,
                /* data = */ html,
                /* mimeType = */ "text/html",
                /* encoding = */ "UTF-8",
                /* historyUrl = */ null
            )
        }
    )
}