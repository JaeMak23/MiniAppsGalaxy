package com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase

import com.jaemak23.miniappsgalaxy.core.domain.DataError
import com.jaemak23.miniappsgalaxy.core.domain.Result

/**
 * title/content are read from the picked file by the platform file picker
 * (FileAccessDataSource — not yet scaffolded). This use case persists
 * what was read as a normal list note and returns its id so the caller
 * can navigate straight into the editor.
 */
class ImportNoteUseCase(private val saveNote: SaveNoteUseCase) {
    suspend operator fun invoke(title: String, content: String): Result<String, DataError.Local> {
        return saveNote(id = null, title = title, content = content, createdAt = null)
    }
}