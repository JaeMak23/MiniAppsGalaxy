package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.isCompact

/**
 * Orchestrates Editor/Preview/Split modes. Delegates rendering to
 * EditorPane, PreviewPane, ViewModeToolbar, and SplitDivider —
 * this composable only decides layout shape and wires state <-> actions.
 */
@Composable
fun NoteEditorLayout(
    state: NoteEditorState,
    onAction: (NoteEditorAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(modifier= Modifier.fillMaxWidth()) {
            Spacer(Modifier.weight(1f))
            ViewModeToolbar(
                viewMode = state.viewMode,
                onSetViewMode = { mode -> onAction(NoteEditorAction.OnSetViewMode(mode)) }
            )
        }

        Box(Modifier.weight(1f).fillMaxWidth()) {
            when (val mode = state.viewMode) {
                EditorViewMode.EditorOnly -> EditorPane(
                    content = state.content,
                    onContentChange = { onAction(NoteEditorAction.OnContentChange(it)) }
                )
                EditorViewMode.PreviewOnly -> PreviewPane(markdown = state.content)
                is EditorViewMode.Split -> SplitPanes(
                    ratio = mode.ratio,
                    content = state.content,
                    onContentChange = { onAction(NoteEditorAction.OnContentChange(it)) },
                    onDragRatio = { ratio -> onAction(NoteEditorAction.OnDragRatio(ratio)) }
                )
            }
        }
    }
}

@Composable
private fun SplitPanes(
    ratio: Float,
    content: String,
    onContentChange: (String) -> Unit,
    onDragRatio: (Float) -> Unit
) {
    if (isCompact) {
        var containerHeightPx by remember { mutableIntStateOf(0) }
        Column(
            modifier = Modifier.fillMaxSize().onSizeChanged { containerHeightPx = it.height }
        ) {
            Box(Modifier.weight(ratio.coerceIn(0.01f, 0.99f)).fillMaxWidth()) {
                EditorPane(content = content, onContentChange = onContentChange)
            }
            SplitDivider(
                orientation = Orientation.Vertical,
                onDrag = { deltaPx ->
                    if (containerHeightPx > 0) onDragRatio(ratio + deltaPx / containerHeightPx)
                }
            )
            Box(Modifier.weight((1f - ratio).coerceIn(0.01f, 0.99f)).fillMaxWidth()) {
                PreviewPane(markdown = content)
            }
        }
    } else {
        var containerWidthPx by remember { mutableIntStateOf(0) }
        Row(
            modifier = Modifier.fillMaxSize().onSizeChanged { containerWidthPx = it.width }
        ) {
            Box(Modifier.weight(ratio.coerceIn(0.01f, 0.99f)).fillMaxHeight()) {
                EditorPane(content = content, onContentChange = onContentChange)
            }
            SplitDivider(
                orientation = Orientation.Horizontal,
                onDrag = { deltaPx ->
                    if (containerWidthPx > 0) onDragRatio(ratio + deltaPx / containerWidthPx)
                }
            )
            Box(Modifier.weight((1f - ratio).coerceIn(0.01f, 0.99f)).fillMaxHeight()) {
                PreviewPane(markdown = content)
            }
        }
    }
}