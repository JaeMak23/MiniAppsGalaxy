package com.jaemak23.miniappsgalaxy.feature.htmleditor.domain.usecase

import com.jaemak23.miniappsgalaxy.core.domain.DataError
import com.jaemak23.miniappsgalaxy.core.domain.Result
import com.jaemak23.miniappsgalaxy.core.domain.FileAccessDataSource

class SaveHtmlFileUseCase ( private val fileAccess: FileAccessDataSource
    ) {
        suspend operator fun invoke(
            filePath: String?,
            suggestedName: String,
            content: String
        ): Result<String?, DataError.Local> {
            return fileAccess.saveFile(
                filePath = filePath,
                suggestedName = suggestedName,
                defaultExtension = HTML_EXTENSION,
                content = content
            )
        }

        companion object {
            private const val HTML_EXTENSION = "html"
        }
    }