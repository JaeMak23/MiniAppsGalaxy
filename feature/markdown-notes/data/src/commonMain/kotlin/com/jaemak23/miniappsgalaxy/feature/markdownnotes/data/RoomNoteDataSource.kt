package com.jaemak23.miniappsgalaxy.feature.markdownnotes.data

import com.jaemak23.miniappsgalaxy.core.domain.DataError
import com.jaemak23.miniappsgalaxy.core.domain.EmptyResult
import com.jaemak23.miniappsgalaxy.core.domain.Result
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.data.local.NoteDao
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.data.mapper.toNote
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.data.mapper.toNoteEntity
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.model.Note
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.model.NoteLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomNoteDataSource(
    private val dao: NoteDao
) : NoteLocalDataSource {

    override fun observeNotes(): Flow<List<Note>> {
        return dao.observeNotes().map { entities -> entities.map { it.toNote() } }
    }

    override suspend fun getNoteById(id: String): Result<Note, DataError.Local> {
        return try {
            val entity = dao.getNoteById(id)
            if (entity != null) Result.Success(entity.toNote())
            else Result.Error(DataError.Local.NOT_FOUND)
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }

    override suspend fun upsertNote(note: Note): EmptyResult<DataError.Local> {
        return try {
            dao.upsertNote(note.toNoteEntity())
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }

    override suspend fun deleteNote(id: String): EmptyResult<DataError.Local> {
        return try {
            dao.deleteNote(id)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }
}