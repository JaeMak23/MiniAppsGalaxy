package com.jaemak23.miniappsgalaxy.core.domain

import com.jaemak23.miniappsgalaxy.core.domain.model.PickedFile
import com.jaemak23.miniappsgalaxy.core.domain.model.PickedFilePath

interface FileAccessDataSource {
    suspend fun pickAndReadFile(extensions: List<String>): Result<PickedFile?, DataError.Local>
    suspend fun pickFilePath(extensions: List<String>): Result<PickedFilePath?, DataError.Local>
    suspend fun readFile(filePath: String): Result<String, DataError.Local>

    /** Returns the saved file's path, or null if the user canceled a Save As dialog. */
    suspend fun saveFile(
        filePath: String?,
        suggestedName: String,
        defaultExtension: String,
        content: String
    ): Result<String?, DataError.Local>

    suspend fun saveAsFile(
        suggestedName: String,
        defaultExtension: String,
        content: String
    ): Result<String?, DataError.Local>
}