package com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase

import com.jaemak23.miniappsgalaxy.core.domain.DataError
import com.jaemak23.miniappsgalaxy.core.domain.EmptyResult

/**
 * Writes title+content out to a device file. If filePath is provided,
 * overwrites that file (Save/Replace); if null, caller's file picker
 * flow supplies a new path (Save As). Actual file I/O lives in
 * FileAccessDataSource — not yet scaffolded, this is the use case shell.
 */
class ExportNoteUseCase(/* private val fileAccess: FileAccessDataSource */) {
    suspend operator fun invoke(filePath: String?, title: String, content: String): EmptyResult<DataError.Local> {
        TODO("Depends on FileAccessDataSource — scaffold when file-picker layer is built")
    }
}