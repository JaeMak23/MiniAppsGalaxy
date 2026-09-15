package com.jaemak23.miniappsgalaxy.core.ui.components.webview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.HtmlElementView
import kotlinx.browser.document
import org.w3c.dom.HTMLIFrameElement

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun PlatformWebView(
    html: String,
    filePath: String?,
    modifier: Modifier
) {
    HtmlElementView(
        factory = {
            (document.createElement("iframe") as HTMLIFrameElement).apply {
                srcdoc = html
            }
        },
        modifier = modifier.fillMaxSize(),
        update = { iframe ->
            iframe.srcdoc = html
        }
    )
}