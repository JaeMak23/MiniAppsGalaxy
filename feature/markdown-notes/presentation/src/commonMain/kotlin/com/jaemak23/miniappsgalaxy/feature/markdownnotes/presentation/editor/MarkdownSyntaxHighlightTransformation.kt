package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp

class MarkdownSyntaxHighlightTransformation(
    private val headerColor: Color,
    private val markerColor: Color,
    private val codeColor: Color
) : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val builder = AnnotatedString.Builder(text.text)
        var lineStart = 0

        text.text.split("\n").forEach { line ->
            val lineEnd = lineStart + line.length
            when {
                line.startsWith("### ") -> builder.addStyle(
                    SpanStyle(color = headerColor, fontWeight = FontWeight.Bold), lineStart, lineEnd
                )

                line.startsWith("## ") -> builder.addStyle(
                    SpanStyle(color = headerColor, fontWeight = FontWeight.Bold, fontSize = 16.sp),
                    lineStart,
                    lineEnd
                )

                line.startsWith("# ") -> builder.addStyle(
                    SpanStyle(color = headerColor, fontWeight = FontWeight.Bold, fontSize = 18.sp),
                    lineStart,
                    lineEnd
                )

                line.trimStart().startsWith("- ") || line.trimStart().startsWith("* ") -> {
                    val markerLen = (line.length - line.trimStart().length) + 2
                    if (lineStart + markerLen <= lineEnd) {
                        builder.addStyle(
                            SpanStyle(color = markerColor),
                            lineStart,
                            lineStart + markerLen
                        )
                    }
                }
            }
            highlightInline(builder, line, lineStart)
            lineStart = lineEnd + 1
        }

        return TransformedText(builder.toAnnotatedString(), OffsetMapping.Identity)
    }

    private fun highlightInline(builder: AnnotatedString.Builder, line: String, lineOffset: Int) {
        var i = 0
        while (i < line.length) {
            when {
                line.startsWith("**", i) -> {
                    val end = line.indexOf("**", i + 2)
                    if (end != -1) {
                        builder.addStyle(
                            SpanStyle(color = markerColor),
                            lineOffset + i,
                            lineOffset + i + 2
                        )
                        builder.addStyle(
                            SpanStyle(fontWeight = FontWeight.Bold),
                            lineOffset + i + 2,
                            lineOffset + end
                        )
                        builder.addStyle(
                            SpanStyle(color = markerColor),
                            lineOffset + end,
                            lineOffset + end + 2
                        )
                        i = end + 2
                        continue
                    }
                }

                line.startsWith("`", i) -> {
                    val end = line.indexOf("`", i + 1)
                    if (end != -1) {
                        builder.addStyle(
                            SpanStyle(color = codeColor, fontFamily = FontFamily.Monospace),
                            lineOffset + i, lineOffset + end + 1
                        )
                        i = end + 1
                        continue
                    }
                }

                line.startsWith("*", i) -> {
                    val end = line.indexOf("*", i + 1)
                    if (end != -1) {
                        builder.addStyle(
                            SpanStyle(color = markerColor),
                            lineOffset + i,
                            lineOffset + i + 1
                        )
                        builder.addStyle(
                            SpanStyle(fontStyle = FontStyle.Italic),
                            lineOffset + i + 1,
                            lineOffset + end
                        )
                        builder.addStyle(
                            SpanStyle(color = markerColor),
                            lineOffset + end,
                            lineOffset + end + 1
                        )
                        i = end + 1
                        continue
                    }
                }
            }
            i++
        }
    }
}