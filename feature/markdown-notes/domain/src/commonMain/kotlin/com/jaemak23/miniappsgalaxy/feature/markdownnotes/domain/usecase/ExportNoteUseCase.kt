package com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase

import com.jaemak23.miniappsgalaxy.core.domain.DataError
import com.jaemak23.miniappsgalaxy.core.domain.Result
import com.jaemak23.miniappsgalaxy.core.domain.FileAccessDataSource

class ExportNoteUseCase(
    private val fileAccess: FileAccessDataSource
) {
    /** Returns the file's path on success, or null if the user canceled a Save As dialog. */
    suspend operator fun invoke(
        filePath: String?,
        suggestedName: String,
        content: String
    ): Result<String?, DataError.Local> {
        return fileAccess.saveFile(filePath = filePath, suggestedName = suggestedName, content = content)
    }
}