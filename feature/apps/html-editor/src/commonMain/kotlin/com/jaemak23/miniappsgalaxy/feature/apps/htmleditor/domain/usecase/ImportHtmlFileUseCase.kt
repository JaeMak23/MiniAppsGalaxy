package com.jaemak23.miniappsgalaxy.feature.apps.htmleditor.domain.usecase

import com.jaemak23.miniappsgalaxy.core.domain.DataError
import com.jaemak23.miniappsgalaxy.core.domain.FileAccessDataSource
import com.jaemak23.miniappsgalaxy.core.domain.Result
import com.jaemak23.miniappsgalaxy.feature.apps.htmleditor.domain.model.HtmlFile

class ImportHtmlFileUseCase(private val fileAccess: FileAccessDataSource) {
    suspend operator fun invoke(): Result<HtmlFile?, DataError.Local> {
        return when (val picked = fileAccess.pickAndReadFile(extensions = listOf("html", "htm"))) {
            is Result.Error -> Result.Error(picked.error)
            is Result.Success -> {
                val file = picked.data ?: return Result.Success(null)
                Result.Success(
                    HtmlFile(
                        title = file.fileName,
                        content = file.content,
                        filePath = file.filePath
                    )
                )
            }
        }
    }
}