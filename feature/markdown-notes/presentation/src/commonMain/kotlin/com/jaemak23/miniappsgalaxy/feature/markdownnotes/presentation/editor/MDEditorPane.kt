package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jaemak23.miniappsgalaxy.core.ui.components.composeeditorkit.EditorPane

@Composable
fun MDEditorPane(
    content: String,
    onContentChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    EditorPane(
        content, onContentChange, modifier,
        MarkdownSyntaxHighlightTransformation(
            headerColor = MaterialTheme.colorScheme.primary,
            markerColor = MaterialTheme.colorScheme.outline,
            codeColor = MaterialTheme.colorScheme.tertiary
        ),
        "Write markdown here…",
    )
}