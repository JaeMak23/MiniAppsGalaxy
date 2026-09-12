package com.jaemak23.miniappsgalaxy.feature.apps.htmleditor.presentation.screens.editor

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.isCompact
import com.jaemak23.miniappsgalaxy.core.ui.components.FileNameTitleTextField
import com.jaemak23.miniappsgalaxy.core.ui.components.NavigationIcon
import com.jaemak23.miniappsgalaxy.core.ui.components.ThemeActionButton
import com.jaemak23.miniappsgalaxy.core.ui.components.TooltipIconButton
import com.jaemak23.miniappsgalaxy.core.ui.components.composeeditorkit.EditorViewMode
import com.jaemak23.miniappsgalaxy.core.ui.components.composeeditorkit.EditorWindowPane
import com.jaemak23.miniappsgalaxy.core.ui.components.composeeditorkit.SplitPanes
import com.jaemak23.miniappsgalaxy.core.ui.components.composeeditorkit.ViewModeToolbar
import com.jaemak23.miniappsgalaxy.core.ui.icons.AppIcons
import com.jaemak23.miniappsgalaxy.feature.apps.htmleditor.presentation.screens.editor.preview.HtmlPreviewPane

@Composable
fun EditorScreen(state: EditorState, onAction: (EditorAction) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    FileNameTitleTextField(state.title, "Html File Name") {
                        onAction(EditorAction.OnTitleChange(it))
                    }
                },
                navigationIcon = {
                    NavigationIcon { onAction(EditorAction.OnBackClick) }
                },
                actions = {
                    TooltipIconButton(
                        "Refresh preview",
                        onClick = { onAction(EditorAction.OnRefreshPreviewClick) }) {
                        Icon(AppIcons.Refresh, contentDescription = "Refresh preview")
                    }
                    TooltipIconButton(
                        "Save",
                        onClick = { onAction(EditorAction.OnSaveClick) },
                        enabled = !state.isSaving
                    ) {
                        if (state.isSaving) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp
                            )
                        } else {
                            Icon(AppIcons.Save, contentDescription = "Save")
                        }
                    }
                    ThemeActionButton()
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            EditorLayout(
                state = state,
                onAction = onAction,
                modifier = Modifier.fillMaxSize()
            ) {
                EditorWindowPane(
                    content = state.content,
                    onContentChange = { onAction(EditorAction.OnContentChange(it)) },
                    headingContent = { Text("HTML Code Editor") },
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun EditorLayout(
    state: EditorState,
    onAction: (EditorAction) -> Unit,
    modifier: Modifier = Modifier,
    editorContent: @Composable () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(Modifier.weight(1f))
            ViewModeToolbar(
                viewMode = state.viewMode,
                onSetViewMode = { mode -> onAction(EditorAction.OnSetViewMode(mode)) }
            )
        }
        Box(Modifier.weight(1f).fillMaxWidth()) {
            when (val mode = state.viewMode) {
                EditorViewMode.EditorOnly -> editorContent()

                EditorViewMode.PreviewOnly ->
                    HtmlPreviewPane(html = state.previewHtml, state.filePath)

                is EditorViewMode.Split -> SplitPanes(
                    ratio = mode.ratio,
                    onDragRatio = { ratio -> onAction(EditorAction.OnDragRatio(ratio)) },
                    isCompact = isCompact,
                    startOrTop = editorContent,
                    endOrBottom = { HtmlPreviewPane(html = state.previewHtml, state.filePath) }
                )
            }
        }
    }
}