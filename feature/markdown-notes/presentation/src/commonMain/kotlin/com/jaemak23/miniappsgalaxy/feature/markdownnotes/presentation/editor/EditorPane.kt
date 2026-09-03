package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.PlatformScrollbar
import com.jaemak23.miniappsgalaxy.core.ui.components.panelBorder

private val EDITOR_FONT_SIZE = 14.sp
private val EDITOR_LINE_HEIGHT = 20.sp
private val GUTTER_WIDTH = 48.dp
private val VERTICAL_PADDING = 8.dp
private val HORIZONTAL_PADDING = 12.dp

@Composable
fun EditorPane(
    content: String, onContentChange: (String) -> Unit, modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    val editorTextStyle = LocalTextStyle.current.copy(
        fontFamily = FontFamily.Monospace,
        fontSize = EDITOR_FONT_SIZE,
        lineHeight = EDITOR_LINE_HEIGHT
    )

    Row(
        modifier = modifier.fillMaxSize()
            .padding(4.dp)
            .panelBorder()
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
    ) {
        LineNumberGutter(
            textLayoutResult = textLayoutResult,
            scrollState = scrollState,
            modifier = Modifier.fillMaxHeight().width(GUTTER_WIDTH)
        )

        Box(Modifier.weight(1f).fillMaxHeight()) {
            BasicTextField(
                value = content,
                onValueChange = onContentChange,
                onTextLayout = { textLayoutResult = it },
                modifier = Modifier.fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = HORIZONTAL_PADDING, vertical = VERTICAL_PADDING),
                textStyle = editorTextStyle.copy(color = MaterialTheme.colorScheme.onSurface),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                visualTransformation = MarkdownSyntaxHighlightTransformation(
                    headerColor = MaterialTheme.colorScheme.primary,
                    markerColor = MaterialTheme.colorScheme.outline,
                    codeColor = MaterialTheme.colorScheme.tertiary
                ),
                decorationBox = { innerTextField ->
                    Box {
                        if (content.isEmpty()) {
                            Text(
                                "Write markdown here…",
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
}

@Composable
private fun LineNumberGutter(
    textLayoutResult: TextLayoutResult?,
    scrollState: androidx.compose.foundation.ScrollState,
    modifier: Modifier = Modifier
) {
    val gutterColor = MaterialTheme.colorScheme.onSurfaceVariant
    val lineCount = textLayoutResult?.lineCount ?: 1

    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .verticalScroll(scrollState)
            .padding(vertical = VERTICAL_PADDING, horizontal = 8.dp)
    ) {
        Layout(
            content = {
                for (lineIndex in 0 until lineCount) {
                    Text(
                        text = (lineIndex + 1).toString(),
                        color = gutterColor,
                        fontFamily = FontFamily.Monospace,
                        fontSize = EDITOR_FONT_SIZE,
                        textAlign = TextAlign.End
                    )
                }
            }
        ) { measurables, constraints ->
            val placeable = measurables.map { it.measure(constraints.copy(minWidth = 0)) }
            val totalHeight = textLayoutResult?.let { it.getLineBottom(it.lineCount - 1) }?.toInt()
                ?: (EDITOR_LINE_HEIGHT.toPx() * lineCount).toInt()

            layout(constraints.maxWidth, totalHeight) {
                placeable.forEachIndexed { index, placeable ->
                    val top = textLayoutResult?.getLineTop(index)?.toInt()
                        ?: (index * EDITOR_LINE_HEIGHT.toPx()).toInt()
                    placeable.placeRelative(
                        x = constraints.maxWidth - placeable.width,
                        y = top
                    )
                }
            }
        }

    }
}