package com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase

import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.model.Note
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.model.NoteLocalDataSource
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(private val dataSource: NoteLocalDataSource) {
    operator fun invoke(): Flow<List<Note>> = dataSource.observeNotes()
}

