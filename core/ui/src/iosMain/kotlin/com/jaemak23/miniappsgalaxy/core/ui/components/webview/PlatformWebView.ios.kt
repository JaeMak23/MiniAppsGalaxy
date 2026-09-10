package com.jaemak23.miniappsgalaxy.core.ui.components.webview

// NOTE: written without Mac/simulator access — verify on first iOS test run.
// WKWebView + UIKitView interop pattern is standard for CMP, but untested here.

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import platform.WebKit.WKWebView
import platform.Foundation.NSURL

@Composable
actual fun PlatformWebView(
    html: String,
    filePath: String?,
    modifier: Modifier
) {
    val webView = remember { WKWebView() }

    UIKitView(
        factory = { webView },
        modifier = modifier.fillMaxSize(),
        update = { view ->
            view.loadHTMLString(html, baseURL = null)
        }
    )
}