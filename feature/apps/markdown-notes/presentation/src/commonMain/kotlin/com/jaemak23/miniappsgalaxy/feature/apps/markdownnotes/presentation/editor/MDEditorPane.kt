package com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.presentation.editor

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.components.composeeditorkit.EditorWindowPane

@Composable
fun MDEditorPane(
    content: String,
    onContentChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    EditorWindowPane(
        content = content,
        onContentChange = onContentChange,
        modifier = modifier.padding(4.dp),
        visualTransformation = MarkdownSyntaxHighlightTransformation(
            headerColor = MaterialTheme.colorScheme.primary,
            markerColor = MaterialTheme.colorScheme.outline,
            codeColor = MaterialTheme.colorScheme.tertiary
        ),
        headingContent = { Text("Markdown Code Editor") },
    )
}