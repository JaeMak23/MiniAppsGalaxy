package com.jaemak23.miniappsgalaxy.feature.htmleditor.presentation.screens.editor

import com.jaemak23.miniappsgalaxy.core.ui.components.composeeditorkit.EditorViewMode

data class EditorState(
    val title: String = "",
    val content: String = "",
    val previewHtml: String = "",
    val filePath: String? = null,
    val viewMode: EditorViewMode = EditorViewMode.Split(),
    val isLoading: Boolean = false,
    val isSaving: Boolean = false
)