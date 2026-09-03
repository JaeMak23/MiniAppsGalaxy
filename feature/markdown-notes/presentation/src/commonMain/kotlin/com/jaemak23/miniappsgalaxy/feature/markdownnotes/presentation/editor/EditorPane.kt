package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditorPane(
    content: String,
    onContentChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = content,
        onValueChange = onContentChange,
        modifier = modifier.fillMaxSize().padding(16.dp),
        placeholder = { Text("Write markdown here…") }
    )
}