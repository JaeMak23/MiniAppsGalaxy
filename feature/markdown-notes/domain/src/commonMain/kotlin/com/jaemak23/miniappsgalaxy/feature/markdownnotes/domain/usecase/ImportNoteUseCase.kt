package com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase

import com.jaemak23.miniappsgalaxy.core.domain.DataError
import com.jaemak23.miniappsgalaxy.core.domain.FileAccessDataSource
import com.jaemak23.miniappsgalaxy.core.domain.Result

class ImportNoteUseCase(
    private val fileAccess: FileAccessDataSource,
    private val saveNote: SaveNoteUseCase
) {
    suspend operator fun invoke(): Result<String?, DataError.Local> {
        return when (val picked = fileAccess.pickAndReadFile(extensions = listOf("md", "txt"))) {
            is Result.Error -> Result.Error(picked.error)
            is Result.Success -> {
                val file = picked.data ?: return Result.Success(null)
                saveNote(id = null, title = file.fileName, content = file.content, createdAt = null)
            }
        }
    }
}