package com.jaemak23.miniappsgalaxy.core.domain

import com.jaemak23.miniappsgalaxy.core.domain.model.PickedFile

interface FileAccessDataSource {
    suspend fun pickAndReadFile(extensions: List<String>): Result<PickedFile?, DataError.Local>

    /** Returns the saved file's path, or null if the user canceled a Save As dialog. */
    suspend fun saveFile(
        filePath: String?,
        suggestedName: String,
        content: String
    ): Result<String?, DataError.Local>
}