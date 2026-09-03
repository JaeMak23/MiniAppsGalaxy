package com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase

import com.jaemak23.miniappsgalaxy.core.domain.DataError
import com.jaemak23.miniappsgalaxy.core.domain.FileAccessDataSource
import com.jaemak23.miniappsgalaxy.core.domain.Result

data class OpenedDraft(
    val filePath: String?,
    val title: String,
    val content: String
)

class OpenFileAsDraftUseCase(
    private val fileAccess: FileAccessDataSource,
    private val saveDraft: SaveDraftUseCase
) {
    /** Returns the opened file's content, or null if the user canceled the picker.
     *  Overwrites whatever was previously in the single draft slot, per spec. */
    suspend operator fun invoke(): Result<OpenedDraft?, DataError.Local> {
        return when (val picked = fileAccess.pickAndReadFile(extensions = listOf("md", "txt"))) {
            is Result.Error -> Result.Error(picked.error)
            is Result.Success -> {
                val file = picked.data ?: return Result.Success(null) // cancelled
                saveDraft(filePath = file.filePath, title = file.fileName, content = file.content)
                    .let {
                        Result.Success(OpenedDraft(filePath = file.filePath, title = file.fileName, content = file.content))
                    }
            }
        }
    }
}