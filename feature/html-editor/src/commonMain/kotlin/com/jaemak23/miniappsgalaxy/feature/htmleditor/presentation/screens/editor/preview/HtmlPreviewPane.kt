package com.jaemak23.miniappsgalaxy.feature.htmleditor.presentation.screens.editor.preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewNavigator
import com.multiplatform.webview.web.rememberWebViewState
import kotlin.io.encoding.Base64

@Composable
fun HtmlPreviewPane(
    html: String,
    modifier: Modifier = Modifier
) {
    val encoded = remember(html) { Base64.encode(html.encodeToByteArray()) }
    key(encoded) {
        val state = rememberWebViewState(url = "data:text/html;base64,$encoded")
        val navigator = rememberWebViewNavigator()

        WebView(
            state = state,
            navigator = navigator,
            modifier = modifier.fillMaxSize()
        )
    }
}