package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.jaemak23.miniappsgalaxy.core.common.util.debugPrint
import com.jaemak23.miniappsgalaxy.core.navigation.MarkdownNotesRoute
import com.jaemak23.miniappsgalaxy.core.navigation.NavConfig
import com.jaemak23.miniappsgalaxy.core.navigation.goBack
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor.NoteEditorOrigin
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor.NoteEditorRoot
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.list.NoteListRoot

@Composable
fun MarkdownNotesNavigation(onExit: () -> Unit) {
    val backStack = rememberNavBackStack(NavConfig, MarkdownNotesRoute.List)

    debugPrint("Markdown notes Navigation")
    NavDisplay(
        modifier = Modifier.fillMaxSize(),
        backStack = backStack,
        onBack = { backStack.goBack() }
    ) { key ->
        when (key) {
            is MarkdownNotesRoute.List -> NavEntry(key) {
                NoteListRoot(
                    onNavigateToNewNote = {
                        backStack.add(MarkdownNotesRoute.Editor())
                    },
                    onNavigateToEditor = { noteId ->
                        backStack.add(MarkdownNotesRoute.Editor(noteId = noteId))
                    },
                    onNavigateToImportedNote = { importedNoteId ->
                        backStack.add(MarkdownNotesRoute.Editor(importedNoteId = importedNoteId))
                    },
                    onNavigateToOpenedDraft = { filePath ->
                        backStack.add(
                            MarkdownNotesRoute.Editor(
                                filePath = filePath,
                                isFromOpen = true
                            )
                        )
                    }
                )
            }

            is MarkdownNotesRoute.Editor -> NavEntry(key) {
                NoteEditorRoot(
                    origin = resolveOrigin(key),
                    instantKey = key.instanceId,
                    onNavigateBack = { backStack.goBack() },
                )
            }

            else -> NavEntry(key) {}
        }
    }
}

private fun resolveOrigin(route: MarkdownNotesRoute.Editor): NoteEditorOrigin {
    val importedNoteId = route.importedNoteId
    val noteId = route.noteId
    return when {
        importedNoteId != null -> NoteEditorOrigin.FromImport(importedNoteId)
        noteId != null -> NoteEditorOrigin.FromList(noteId)
        route.isFromOpen -> NoteEditorOrigin.FromOpen(route.filePath)
        else -> NoteEditorOrigin.New
    }
}