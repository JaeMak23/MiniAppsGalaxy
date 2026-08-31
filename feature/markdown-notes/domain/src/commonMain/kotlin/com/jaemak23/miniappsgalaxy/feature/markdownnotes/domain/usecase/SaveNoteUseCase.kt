package com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase

import com.jaemak23.miniappsgalaxy.core.domain.DataError
import com.jaemak23.miniappsgalaxy.core.domain.EmptyResult
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.model.Note
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.model.NoteLocalDataSource
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class SaveNoteUseCase(private val dataSource: NoteLocalDataSource) {
    @OptIn(ExperimentalUuidApi::class)
    suspend operator fun invoke(
        id: String?,
        title: String,
        content: String
    ): EmptyResult<DataError.Local> {
        val now = System.currentTimeMillis()
        val note = Note(
            id = id ?: Uuid.random().toString(),
            title = title.ifBlank { "Untitled" },
            content = content,
            createdAt = now, // overwritten below if editing
            updatedAt = now
        )
        return dataSource.upsertNote(note)
    }
}