package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.isCompact
import com.jaemak23.miniappsgalaxy.core.ui.components.composeeditorkit.SplitPanes

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
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(Modifier.weight(1f))
            ViewModeToolbar(
                viewMode = state.viewMode,
                onSetViewMode = { mode -> onAction(NoteEditorAction.OnSetViewMode(mode)) }
            )
        }

        Box(Modifier.weight(1f).fillMaxWidth()) {
            when (val mode = state.viewMode) {
                EditorViewMode.EditorOnly -> MDEditorPane(
                    content = state.content,
                    onContentChange = { onAction(NoteEditorAction.OnContentChange(it)) }
                )

                EditorViewMode.PreviewOnly -> PreviewPane(markdown = state.content)
                is EditorViewMode.Split -> SplitPanes(
                    ratio = mode.ratio,
                    onDragRatio = { ratio -> onAction(NoteEditorAction.OnDragRatio(ratio)) },
                    isCompact = isCompact,
                    startOrTop = {
                        MDEditorPane(
                            content = state.content,
                            onContentChange = { onAction(NoteEditorAction.OnContentChange(it)) })
                    },
                    endOrBottom = { PreviewPane(markdown = state.content) }
                )
            }
        }
    }
}