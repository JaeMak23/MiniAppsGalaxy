package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.components.composeeditorkit.panelBorder

/**
 * Lightweight pure-Kotlin markdown renderer — no platform-specific library,
 * works identically on androidApp and desktopApp.
 * Supports: # ## ### headers, **bold**, *italic*, `code`, - / * bullet lists.
 */
@Composable
fun PreviewPane(
    markdown: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()
        .padding(4.dp)
        .panelBorder()
        .verticalScroll(rememberScrollState()).padding(16.dp)) {
        markdown.lines().forEach { line ->
            when {
                line.isBlank() -> Spacer(Modifier.height(8.dp))
                line.startsWith("### ") -> Text(line.removePrefix("### "), style = MaterialTheme.typography.titleMedium)
                line.startsWith("## ") -> Text(line.removePrefix("## "), style = MaterialTheme.typography.titleLarge)
                line.startsWith("# ") -> Text(line.removePrefix("# "), style = MaterialTheme.typography.headlineSmall)
                line.trimStart().startsWith("- ") || line.trimStart().startsWith("* ") -> {
                    Row {
                        Text("•  ")
                        Text(text = inlineMarkdown(line.trimStart().removePrefix("- ").removePrefix("* ")))
                    }
                }
                else -> Text(text = inlineMarkdown(line))
            }
        }
    }
}

private fun inlineMarkdown(text: String) = buildAnnotatedString {
    var i = 0
    while (i < text.length) {
        when {
            text.startsWith("**", i) -> {
                val end = text.indexOf("**", i + 2)
                if (end == -1) { append(text[i]); i++ } else {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append(text.substring(i + 2, end)) }
                    i = end + 2
                }
            }
            text.startsWith("`", i) -> {
                val end = text.indexOf("`", i + 1)
                if (end == -1) { append(text[i]); i++ } else {
                    withStyle(SpanStyle(fontFamily = FontFamily.Monospace)) { append(text.substring(i + 1, end)) }
                    i = end + 1
                }
            }
            text.startsWith("*", i) -> {
                val end = text.indexOf("*", i + 1)
                if (end == -1) { append(text[i]); i++ } else {
                    withStyle(SpanStyle(fontStyle = FontStyle.Italic)) { append(text.substring(i + 1, end)) }
                    i = end + 1
                }
            }
            else -> { append(text[i]); i++ }
        }
    }
}