package com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.data

import com.jaemak23.miniappsgalaxy.core.domain.DataError
import com.jaemak23.miniappsgalaxy.core.domain.EmptyResult
import com.jaemak23.miniappsgalaxy.core.domain.Result
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.domain.NoteLocalDataSource
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first

class InMemoryNoteDataSource : NoteLocalDataSource {
    private val notes = MutableStateFlow<List<Note>>(emptyList())

    override fun observeNotes(): Flow<List<Note>> = notes.asStateFlow()

    override suspend fun getNoteById(id: String): Result<Note, DataError.Local> {
        val note = notes.first().find { it.id == id }
        return if (note != null) Result.Success(note) else Result.Error(DataError.Local.NOT_FOUND)
    }

    override suspend fun upsertNote(note: Note): EmptyResult<DataError.Local> {
        notes.value = notes.value.filterNot { it.id == note.id } + note
        return Result.Success(Unit)
    }

    override suspend fun deleteNote(id: String): EmptyResult<DataError.Local> {
        notes.value = notes.value.filterNot { it.id == id }
        return Result.Success(Unit)
    }
}