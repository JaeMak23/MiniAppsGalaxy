package com.jaemak23.miniappsgalaxy.core.navigation.routes

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
sealed interface MarkdownNotesRoute : NavKey {

    @Serializable
    data object List : MarkdownNotesRoute

    @Serializable
    data class Editor(
        val instanceId: String = Uuid.random().toString(),
        val noteId: String? = null,
        val importedNoteId: String? = null,
        val filePath: String? = null,
        val isFromOpen: Boolean = false
    ) : MarkdownNotesRoute
}