package com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.model

import com.jaemak23.miniappsgalaxy.core.domain.Result
import com.jaemak23.miniappsgalaxy.core.domain.EmptyResult
import com.jaemak23.miniappsgalaxy.core.domain.DataError
import kotlinx.coroutines.flow.Flow

interface NoteLocalDataSource {
    fun observeNotes(): Flow<List<Note>>
    suspend fun getNoteById(id: String): Result<Note, DataError.Local>
    suspend fun upsertNote(note: Note): EmptyResult<DataError.Local>
    suspend fun deleteNote(id: String): EmptyResult<DataError.Local>
}