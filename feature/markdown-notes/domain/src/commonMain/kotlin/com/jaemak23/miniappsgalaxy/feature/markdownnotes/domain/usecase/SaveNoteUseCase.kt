package com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase

import com.jaemak23.miniappsgalaxy.core.domain.DataError
import com.jaemak23.miniappsgalaxy.core.domain.Result
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.model.Note
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.NoteLocalDataSource
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class SaveNoteUseCase(private val dataSource: NoteLocalDataSource) {
    @OptIn(ExperimentalUuidApi::class)
    suspend operator fun invoke(
        id: String?,
        title: String,
        content: String,
        createdAt: Long?
    ): Result<String, DataError.Local> {
        val now = System.currentTimeMillis()
        val note = Note(
            id = id ?: Uuid.random().toString(),
            title = title.ifBlank { "Untitled" },
            content = content,
            createdAt = createdAt ?: now,
            updatedAt = now
        )
        return dataSource.upsertNote(note).let { result ->
            when (result) {
                is Result.Success -> Result.Success(note.id)
                is Result.Error -> Result.Error(result.error)
            }
        }
    }
}