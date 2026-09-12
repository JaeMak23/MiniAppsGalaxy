package com.jaemak23.miniappsgalaxy.core.ui.components.composeeditorkit

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.LocalDarkMode
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.PlatformScrollbar
import com.jaemak23.miniappsgalaxy.core.ui.theme.ComponentPreview

@Composable
fun EditorWindowPane(
    content: String,
    onContentChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    placeholderText: String = "Write here…",
    headingContent: @Composable (RowScope.() -> Unit)? = null,
    showLineNumberGutter: Boolean = true
) {
    val scrollState = rememberScrollState()
    val colors = if (LocalDarkMode.current.value) DarkModeDefaults else LightModeDefaults
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    val editorTextStyle = LocalTextStyle.current.copy(
        fontFamily = FontFamily.Monospace,
        fontSize = EDITOR_FONT_SIZE,
        lineHeight = EDITOR_LINE_HEIGHT
    )

    KWindowContainer(modifier, colors, headingContent) {
        if (showLineNumberGutter) {
            Row(modifier = modifier.fillMaxSize()) {
                LineNumberGutter(
                    textLayoutResult = textLayoutResult,
                    scrollState = scrollState,
                    modifier = Modifier.fillMaxHeight().width(GUTTER_WIDTH)
                )

                EditorCom(
                    modifier = Modifier.weight(1f).fillMaxHeight(),
                    content = content,
                    onContentChange = onContentChange,
                    onTextLayout = { textLayoutResult = it },
                    scrollState = scrollState,
                    editorTextStyle = editorTextStyle,
                    visualTransformation = visualTransformation,
                    placeholderText = placeholderText
                )
            }
        } else {
            EditorCom(
                modifier = modifier.fillMaxSize(),
                content = content,
                onContentChange = onContentChange,
                onTextLayout = { textLayoutResult = it },
                scrollState = scrollState,
                editorTextStyle = editorTextStyle,
                visualTransformation = visualTransformation,
                placeholderText = placeholderText
            )
        }
    }
}

@Composable
private fun EditorCom(
    modifier: Modifier = Modifier,
    content: String,
    onContentChange: (String) -> Unit,
    onTextLayout: (TextLayoutResult) -> Unit,
    scrollState: ScrollState,
    editorTextStyle: TextStyle,
    visualTransformation: VisualTransformation,
    placeholderText: String
) {
    Box(modifier) {
        BasicTextField(
            value = content,
            onValueChange = onContentChange,
            onTextLayout = onTextLayout,
            modifier = Modifier.fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = HORIZONTAL_PADDING, vertical = VERTICAL_PADDING),
            textStyle = editorTextStyle.copy(color = MaterialTheme.colorScheme.onSurface),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            visualTransformation = visualTransformation,
            decorationBox = { innerTextField ->
                Box {
                    if (content.isEmpty()) {
                        Text(
                            placeholderText,
                            style = editorTextStyle.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                        )
                    }
                    innerTextField()
                }
            }
        )

        PlatformScrollbar(
            scrollState = scrollState,
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight()
        )
    }
}

@Composable
@Preview
private fun EditorWindowPanePreview() {
    ComponentPreview {
        EditorWindowPane(
            content = "Hello World",
            onContentChange = { },
            headingContent = { Text("Editor") },
            modifier = Modifier.padding(4.dp)
        )
    }
}